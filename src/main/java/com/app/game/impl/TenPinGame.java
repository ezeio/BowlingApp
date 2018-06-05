package com.app.game.impl;

import com.app.display.GameDisplay;
import com.app.game.Game;
import com.app.gameplay.GamePlay;
import com.app.gameplay.impl.TenPinGamePlay;
import com.app.validator.Validator;
import com.app.validator.impl.TenPinValidator;

import java.util.Scanner;

public class TenPinGame implements Game {

    private GameDisplay gameDisplay;
    private Validator validator;
    private int numberOfCurrentPlayers;
    private Scanner keybordInput;
    private GamePlay gamePlay;


    public TenPinGame () {
        //TODO the display should be set automatically when game is created ?
        validator = new TenPinValidator();
        //gamePlay = new TenPinGamePlay();
    }

    public void startGame() {
        gameDisplay.displayMainMenu();
        keybordInput = readKeyBoardInput();
        keybordInput.useDelimiter("\\n");

        int input = readIntInput(keybordInput);

        while (!(input > 0 && input <= gameDisplay.getMainMenuOptions().size())){
            gameDisplay.displayOptionSelectorError();
            input = readIntInput(keybordInput);
        }

        mainMenuSelector(input);
    }

    private int readIntInput(Scanner scanner) {

        while (!scanner.hasNextInt()){
            gameDisplay.displayInvalidInputError();
            if (scanner.hasNext()){
                scanner.next();
            }
        }

        return scanner.nextInt();
    }

    public void selectNewGame() {
        int numberOfPlayers = 0;
        gameDisplay.requestNumberOfPlayers();
        keybordInput.useDelimiter("\\n");

        while (this.getNumberOfCurrentPlayers() < 1){
            numberOfPlayers = readIntInput(keybordInput);

            if( validator.isNumberOfPlayersValid(numberOfPlayers)){
                this.setNumberOfCurrentPlayers(numberOfPlayers);
                break;
            }
            else {
                gameDisplay.requestNumberOfPlayers();
            }
        }

        //TODO complete implementation of the selectNewGame method
        System.out.println("The number of players has been provided");
        gamePlay = new TenPinGamePlay();
        gamePlay.setUpGamePlay(keybordInput, numberOfPlayers);
        gamePlay.startGamePlay();
    }

    private Scanner readKeyBoardInput() {
        return new Scanner(System.in);
    }

    private void closeScanner(Scanner scanner){
        scanner.close();
    }

    private void mainMenuSelector(int selectedMenu){
        switch (selectedMenu){
            case 1: selectNewGame();
                break;
            case 2: System.out.println("TODO");//TODO
        }
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }

    public void setGameDisplay(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    private int getNumberOfCurrentPlayers() {
        return numberOfCurrentPlayers;
    }

    private void setNumberOfCurrentPlayers(int numberOfCurrentPlayers) {
        this.numberOfCurrentPlayers = numberOfCurrentPlayers;
    }

    @Override
    public GamePlay getGamePlay() {
        return gamePlay;
    }

}
