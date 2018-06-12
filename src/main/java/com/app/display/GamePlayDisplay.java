package com.app.display;

import com.app.model.Player;

import java.util.List;

public interface GamePlayDisplay {

    void displayPlayerDetailsMenu(Player[] currentPlayers, boolean listIsFull);

    void displayInvalidInputError();

    List<String> getPlayerDetailsMenu();

    void displayOptionSelectorError();

    void addNewPlayerMenu(String firstName, String lastName);

    void duplicatePlayerNameError();

    void existingPlayerMenu(Player[] tenPinGamePlay);
    List<String> getExistingPlayerMenu();

    void playerAlreadyAddedError();

    void selectValidOptionOrCancel();

    void startOrEndGameMsg();
}
