package com.spiritualfamily.backend.validation;

public class PhoneValidator {

    public static boolean isValid(
            String phone
    ) {

        return phone != null
                &&
                phone.matches(
                        "^[0-9]{10}$"
                );
    }
}