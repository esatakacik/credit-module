package com.example.credit_module.validator;

import com.example.credit_module.anotation.ValueOfEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Integer> {

    private int[] acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        this.acceptedValues = annotation.acceptedValues();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (null == value)
            return true;
        for(int val : acceptedValues){
            if (val == value)
                return true;
        }
        return false;
    }
}
