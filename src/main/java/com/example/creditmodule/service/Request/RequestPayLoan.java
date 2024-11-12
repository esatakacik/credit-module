package com.example.creditmodule.service.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestPayLoan {
    @NotNull(message = "loanId must not be null")
    private Long loanId;
    private BigDecimal amount;
}
