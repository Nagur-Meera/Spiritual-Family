package com.spiritualfamily.backend.validation;

public class EmailValidator {

    public static boolean isValid(
            String email
    ) {

        return email != null
                &&
                email.matches(
                        "^[A-Za-z0-9+_.-]+@(.+)$"
                );
    }
}