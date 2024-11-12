package com.example.credit_module.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private Long errorCode;
    private String errorMessage;
}
