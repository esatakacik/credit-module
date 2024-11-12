package com.example.creditmodule.service;

import com.example.creditmodule.constant.CreditModuleException;
import com.example.creditmodule.data.dto.LoanDTO;
import com.example.creditmodule.data.dto.LoanInstallmentDTO;
import com.example.creditmodule.data.entity.Customer;
import com.example.creditmodule.data.entity.Loan;
import com.example.creditmodule.data.entity.LoanInstallment;
import com.example.creditmodule.data.mapper.LoanInstallmentMapper;
import com.example.creditmodule.data.mapper.LoanMapper;
import com.example.creditmodule.data.service.CustomerDataService;
import com.example.creditmodule.data.service.LoanDataService;
import com.example.creditmodule.data.service.LoanInstallmentDataService;
import com.example.creditmodule.service.Request.RequestCreateLoan;
import com.example.creditmodule.service.Request.RequestListInstallments;
import com.example.creditmodule.service.Request.RequestListLoan;
import com.example.creditmodule.service.Request.RequestPayLoan;
import com.example.creditmodule.service.response.ResponsePayLoan;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final CustomerDataService customerDataService;
    private final LoanDataService loanDataService;
    private final LoanInstallmentDataService loanInstallmentDataService;

    @Transactional
    public LoanDTO createLoan(RequestCreateLoan request){
        Customer customer =  customerDataService.findById(request.getCustomerId()).orElseThrow(() -> CreditModuleException.CUSTOMER_NOT_FOUND);

        if (request.getLoanAmount().compareTo(customer.getCreditLimit().subtract(customer.getUsedCreditLimit())) > 0){
            throw CreditModuleException.OVERDRAFT_LOAN_AMOUNT;
        }
        Loan loanEntity = loanDataService.save(LoanMapper.INSTANCE.toEntity(request));

        BigDecimal totalAmount = request.getLoanAmount().multiply(request.getInterestRate().add(BigDecimal.ONE));
        customer.setUsedCreditLimit(totalAmount);
        customerDataService.save(customer);

        BigDecimal divededAmount = totalAmount.divide(new BigDecimal(request.getNumberOfInstallment()));
        List<LoanInstallment> loanInstallmentList = new ArrayList<>();
        for (int i = 0; i < request.getNumberOfInstallment(); i++) {
            LoanInstallment loanInstallmentEntity = new LoanInstallment();
            loanInstallmentEntity.setLoanId(loanEntity.getId());
            loanInstallmentEntity.setAmount(divededAmount);
            loanInstallmentEntity.setDueDate(Date.from(YearMonth.from(LocalDate.now()).plusMonths(i+1).atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            loanInstallmentEntity.setIsPaid(Boolean.FALSE);
            loanInstallmentList.add(loanInstallmentEntity);
        }
        loanInstallmentDataService.saveAllLoanInstallments(loanInstallmentList);
        return LoanMapper.INSTANCE.toDTO(loanEntity);
    }

    public List<LoanDTO> listLoan(RequestListLoan request) {
        customerDataService.findById(request.getCustomerId()).orElseThrow(() -> CreditModuleException.CUSTOMER_NOT_FOUND);
        return LoanMapper.INSTANCE.toDTO(loanDataService.findAll(request));
    }

    public List<LoanInstallmentDTO> listInstallments(RequestListInstallments request) {
        loanDataService.findById(request.getLoanId()).orElseThrow(() -> CreditModuleException.LOAN_NOT_FOUND);
        return LoanInstallmentMapper.INSTANCE.toDTO(loanInstallmentDataService.findAll(request));
    }

    @Transactional
    public ResponsePayLoan payLoan(RequestPayLoan request) {
        ResponsePayLoan response = new ResponsePayLoan(0, BigDecimal.ZERO, false);
        Loan loanEntity =  loanDataService.findById(request.getLoanId()).orElseThrow(() -> CreditModuleException.LOAN_NOT_FOUND);

        if (loanEntity.getIsPaid())
            throw CreditModuleException.LOAN_ALREADY_PAID;

        List<LoanInstallment> loanInstallmentList = loanInstallmentDataService.getInstallmentsByLoanIdAndIsPaidWithSize(request.getLoanId(), Boolean.FALSE, 3).getContent();

        if (CollectionUtils.isEmpty(loanInstallmentList))
            throw CreditModuleException.NO_PAYABLE_INSTALLMENT_FOUND;

        long monthsBetweenFirstInstallment = ChronoUnit.MONTHS.between(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), loanInstallmentList.getFirst().getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if (monthsBetweenFirstInstallment >= 3)
            throw CreditModuleException.NO_PAYABLE_INSTALLMENT_FOUND_FOR_NEXT_THREE_MONTHS;

        Customer customer =  customerDataService.findById(loanEntity.getCustomerId()).orElseThrow(() -> CreditModuleException.CUSTOMER_NOT_FOUND);
        BigDecimal remainingAmount = request.getAmount();
        for (LoanInstallment installment : loanInstallmentList){
            long monthsBetween = ChronoUnit.MONTHS.between(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), installment.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (monthsBetween >= 3){
                break;
            }
            long daysBetween = ChronoUnit.DAYS.between(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), installment.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            BigDecimal amountToBePaid = installment.getAmount().subtract(BigDecimal.valueOf(0.001).multiply(BigDecimal.valueOf(daysBetween)));
            if (remainingAmount.compareTo(amountToBePaid) < 0)
                break;
            remainingAmount = remainingAmount.subtract(amountToBePaid);
            installment.setPaidAmount(amountToBePaid);
            installment.setPaymentDate(new Date());
            installment.setIsPaid(Boolean.TRUE);
            loanInstallmentDataService.save(installment);
            customer.setUsedCreditLimit(customer.getUsedCreditLimit().subtract(installment.getAmount()));
            customerDataService.save(customer);
            response.setNumberOfPaidInstallments(response.getNumberOfPaidInstallments() + 1);
            response.setTotalAmountSpent(response.getTotalAmountSpent().add(amountToBePaid));
        }
        if(!loanInstallmentDataService.exist(loanEntity.getId(), Boolean.FALSE)){
            loanEntity.setIsPaid(Boolean.TRUE);
            loanDataService.save(loanEntity);
            response.setLoanPaidCompletely(true);
        }
        return response;
    }
}
