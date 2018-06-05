package com.app.display;

import java.util.List;

public interface GameDisplay {
    void displayMainMenu();

    void requestNumberOfPlayers();

    void displayInvalidInputError();

    List<String> getMainMenuOptions();

    void setMainMenuOptions(List<String> mainMenuOptions);

    void displayOptionSelectorError();
}
