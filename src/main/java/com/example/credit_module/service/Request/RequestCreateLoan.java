package com.example.credit_module.service.Request;

import com.example.credit_module.anotation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class RequestCreateLoan {
    @NotNull(message = "customerId must not be null")
    private Long customerId;
    @NotNull(message = "loanAmount must not be null")
    private BigDecimal loanAmount;
    @NotNull(message = "numberOfInstallment must not be null")
    @ValueOfEnum(acceptedValues = {6, 9, 12, 24}, message = "numberOfInstallment must be one of: 6, 9, 12, 24")
    private Integer numberOfInstallment;
    @NotNull(message = "interestRate must not be null")
    @DecimalMin(value = "0.1", message = "Interest rate should be greater than 0.1")
    @DecimalMax(value = "0.5", message = "Interest rate should be less than 0.5")
    private BigDecimal interestRate;
}
