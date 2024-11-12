package com.example.credit_module.service.response;

import com.example.credit_module.data.dto.LoanInstallmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePayLoan {
    private int numberOfPaidInstallments;
    private BigDecimal totalAmountSpent;
    private boolean isLoanPaidCompletely;

}
