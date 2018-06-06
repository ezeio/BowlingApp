package com.app.validator.impl;

import com.app.validator.GamePlayValidator;

public class TenPinGamePlayValidator implements GamePlayValidator {


    @Override
    public boolean isValidFirstName(String firstName) {
        System.out.println("isValidFirstName " + firstName);
        return false;
    }

    @Override
    public boolean isValidLastName(String lastName) {
        System.out.println("isValidLastName "+ lastName);
        return false;
    }
}
