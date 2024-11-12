package com.example.creditmodule.data.service;

import com.example.creditmodule.data.entity.LoanInstallment;
import com.example.creditmodule.data.repository.LoanInstallmentRepository;
import com.example.creditmodule.data.specification.LoanInstallmentSpecification;
import com.example.creditmodule.service.Request.RequestListInstallments;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanInstallmentDataService {
    private final LoanInstallmentRepository repository;
    private final LoanInstallmentSpecification loanInstallmentSpecification;

    public Iterable<LoanInstallment> saveAllLoanInstallments(List<LoanInstallment> loanInstallmentList){
        return repository.saveAll(loanInstallmentList);
    }

    public LoanInstallment save(LoanInstallment loanInstallment){
        return repository.save(loanInstallment);
    }

    public List<LoanInstallment> findAll(RequestListInstallments request){
        return repository.findAll(loanInstallmentSpecification.getLoanInstallments(request), Sort.by("dueDate").ascending());
    }

    public Page<LoanInstallment> getInstallmentsByLoanIdAndIsPaidWithSize(Long loanId, Boolean isPaid, Integer size){
        Pageable pageable = PageRequest.of(0, size, Sort.by("dueDate").ascending());
        return repository.findAll(loanInstallmentSpecification.getLoanInstallments(new RequestListInstallments(loanId, isPaid)), pageable);
    }

    public boolean exist(Long loanId, Boolean isPaid){
        return repository.exists(loanInstallmentSpecification.getLoanInstallments(new RequestListInstallments(loanId, isPaid)));
    }
}
