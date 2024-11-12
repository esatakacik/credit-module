package com.example.creditmodule.service.Request;

import com.example.creditmodule.anotation.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestListLoan {
    @NotNull(message = "customerId must not be null")
    private Long customerId;
    private BigDecimal loanAmount;
    @ValueOfEnum(acceptedValues = {6, 9, 12, 24}, message = "numberOfInstallment must be one of: 6, 9, 12, 24")
    private Integer numberOfInstallment;
    private Boolean isPaid;
}
