package com.example.creditmodule.service.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestListInstallments {
    @NotNull(message = "loanId must not be null")
    private Long loanId;
    private Boolean isPaid;
}
