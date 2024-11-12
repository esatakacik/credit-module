package com.example.creditmodule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private Long errorCode;
    private String errorMessage;
}
