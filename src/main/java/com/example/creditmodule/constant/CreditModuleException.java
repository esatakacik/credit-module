package com.example.creditmodule.constant;

import com.example.creditmodule.exception.CustomException;

public class CreditModuleException {
    public static final CustomException CUSTOMER_NOT_FOUND = new CustomException(1000000L, "Customer not found");
    public static final CustomException OVERDRAFT_LOAN_AMOUNT = new CustomException(1000001L, "Overdraft loan amount");
    public static final CustomException LOAN_NOT_FOUND = new CustomException(1000003L, "Loan not found");
    public static final CustomException LOAN_ALREADY_PAID = new CustomException(1000004L, "The loan has already been paid");
    public static final CustomException NO_PAYABLE_INSTALLMENT_FOUND = new CustomException(1000005L, "No payable installment found");
    public static final CustomException NO_PAYABLE_INSTALLMENT_FOUND_FOR_NEXT_THREE_MONTHS = new CustomException(1000005L, "No payable installment found for next 3 months");
}
