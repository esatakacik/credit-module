package com.example.creditmodule.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePayLoan {
    private int numberOfPaidInstallments;
    private BigDecimal totalAmountSpent;
    private boolean isLoanPaidCompletely;

}
