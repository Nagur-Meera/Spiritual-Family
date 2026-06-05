package com.spiritualfamily.backend.validation;

public class PasswordValidator {

    public static boolean isValid(
            String password
    ) {

        return password != null
                &&
                password.length() >= 6;
    }
}