package com.app.display.impl;

import java.util.ArrayList;
import java.util.List;

public class TenPinGameDisplay implements com.app.display.GameDisplay {

    private List<String> mainMenuOptions = new ArrayList<String>();

    public TenPinGameDisplay (){
        setUpMainMenu();
    }


    public void displayMainMenu() {
        System.out.println();
        getMainMenuOptions().forEach((option)-> System.out.println(option+"\n"));
        System.out.println("|select an option to proceed|");
    }

    public void requestNumberOfPlayers() {
        System.out.println("Give number of players between 1 and 4 inclusive");
    }

    public void displayInvalidInputError() {
        System.err.println("Please provide a valid value");
    }

    @Override
    public List<String> getMainMenuOptions() {
        return mainMenuOptions;
    }

    @Override
    public void setMainMenuOptions(List<String> mainMenuOptions) {
        this.mainMenuOptions = mainMenuOptions;
    }

    @Override
    public void displayOptionSelectorError() {
        System.out.println("Please select number that corresponds to an option");
    }

    private void setUpMainMenu(){
        getMainMenuOptions().add("[1] Start new game");
        getMainMenuOptions().add("[2] View player stats");
    }
}
