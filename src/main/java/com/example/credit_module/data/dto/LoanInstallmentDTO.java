package com.example.credit_module.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LoanInstallmentDTO {
    private Long id;
    private Long loanId;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private Date dueDate;
    private Date paymentDate;
    private Boolean isPaid;
}
