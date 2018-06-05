package com.app.validator.impl;

import com.app.validator.Validator;

public class TenPinValidator implements Validator {

    private static final int MAX_NUMBER_OF_PLAYER = 4;

    @Override
    public boolean isNumberOfPlayersValid(int numberOfPlayers) {
        if(!(numberOfPlayers > 0 && numberOfPlayers <= MAX_NUMBER_OF_PLAYER)){
            return false;
        }
        return true;
    }
}
