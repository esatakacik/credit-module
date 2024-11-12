package com.example.credit_module.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class LoanDTO {
    private Long id;
    private Long customerId;
    private BigDecimal loanAmount;
    private Integer numberOfInstallment;
    private Date createDate;
    private Boolean isPaid;
}
