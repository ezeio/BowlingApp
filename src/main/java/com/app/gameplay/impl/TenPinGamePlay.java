package com.app.gameplay.impl;

import com.app.display.GamePlayDisplay;
import com.app.display.impl.TenPinGamePlayDisplay;
import com.app.gameplay.GamePlay;
import com.app.model.Player;
import com.app.validator.GamePlayValidator;
import com.app.validator.impl.TenPinGamePlayValidator;

import java.util.*;

public class TenPinGamePlay implements GamePlay {

    private Scanner scanner;
    private Player[] currentPlayers;
    private GamePlayDisplay gamePlayDisplay;
    private GamePlayValidator gamePlayValidator;
    private static final Set<Player> existingPlayers = new HashSet<>();


    public GamePlayValidator getGamePlayValidator() {
        return gamePlayValidator;
    }

    public void setGamePlayValidator(GamePlayValidator gamePlayValidator) {
        this.gamePlayValidator = gamePlayValidator;
    }

    public static Set<Player> getExistingPlayers() {
        return existingPlayers;
    }
    @Override
    public int getNumberOfCurrentPlayers() {
        return currentPlayers.length;
    }

    public void setUpGamePlay(Scanner keyboardInput, int numberOfPlayers){
        gamePlayValidator = new TenPinGamePlayValidator();
        setGamePlayDisplay(new TenPinGamePlayDisplay());
        setScanner(keyboardInput);
        getScanner().useDelimiter("\n");
        setUpPlayers(numberOfPlayers);
    }

    @Override
    public void startGamePlay() {

        getScanner().close();
    }

    @Override
    public void setUpPlayers(int numberOfPlayers) {
        setCurrentPlayers(new Player[numberOfPlayers]);
        playerDetailsMenu();
        //TODO STARTING GAME PLAY
    }

    private void playerDetailsMenu(){
        boolean listIsFull = isCurrentPlayerListFull();
        if(listIsFull){
           gamePlayDisplay.displayPlayerDetailsMenu(currentPlayers, listIsFull);
           return;
        }
        gamePlayDisplay.displayPlayerDetailsMenu(currentPlayers, listIsFull);
        int input = readIntInput();

        while (!(input > 0 && input <= gamePlayDisplay.getPlayerDetailsMenu().size() )){
            gamePlayDisplay.displayOptionSelectorError();
            input = readIntInput();
        }
        providePlayerDetails(input);
    }

    private boolean isCurrentPlayerListFull() {
        return currentPlayers[currentPlayers.length - 1] != null;
    }

    private void providePlayerDetails(int input) {
        switch(input){
            case 1: useExistingPlayer();
                break;
            case 2: addNewPlayer();
                break;
        }
    }

    private void useExistingPlayer() {
        //TODO
        System.out.println("No implementation yet");
    }

    private void addNewPlayer() {
        String firstName = null;
        String lastName = null;
        gamePlayDisplay.addNewPlayerMenu(firstName, lastName);
        firstName = readStringInput();
        gamePlayDisplay.addNewPlayerMenu(firstName,lastName);
        lastName = readStringInput();
        gamePlayDisplay.addNewPlayerMenu(firstName,lastName);
        Player player = createNewPlayer(firstName, lastName);
        addPlayerToCurrentAndExistingList(player);
        playerDetailsMenu();
    }

    private void addPlayerToCurrentAndExistingList(Player player) {
        if(getExistingPlayers().contains(player)){
            gamePlayDisplay.duplicatePlayerNameError();
            return;
        }

        getExistingPlayers().add(player);

        for(int i = 0; i < currentPlayers.length; i++){
            if(currentPlayers[i] == null){
                currentPlayers[i] = player;
                return;
            }
        }
    }

    private Player createNewPlayer(String firstName, String lastName) {
        Player player = new Player(firstName, lastName);
        addFormattedName(player);
        return player;
    }

    private void addFormattedName(Player player) {
        String modify = player.getFirstName().substring(1,player.getFirstName().length()).toLowerCase();
        StringBuilder builder = new StringBuilder();
        char formatFirstName = Character.toUpperCase(player.getFirstName().charAt(0));
        builder.append(String.valueOf(formatFirstName));
        builder.append(modify);
        char formatLastName = player.getLastName().toUpperCase().charAt(0);
        builder.append("\t");
        builder.append(String.valueOf(formatLastName));
        player.setFormattedName(builder.toString());
    }

    private String readStringInput() {

        String input = scanner.hasNext()?scanner.next(): "";

        while (!input.matches("[A-Za-z]+")){
            gamePlayDisplay.displayInvalidInputError();
            if(scanner.hasNext()){
                return scanner.next();
            }
        }
        return input;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Player[] getCurrentPlayers() {
        return currentPlayers;
    }

    private void setCurrentPlayers(Player[] currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public GamePlayDisplay getGamePlayDisplay() {
        return gamePlayDisplay;
    }

    private void setGamePlayDisplay(GamePlayDisplay gamePlayDisplay) {
        this.gamePlayDisplay = gamePlayDisplay;
    }

    private int readIntInput(){
        while (!scanner.hasNextInt()){
            gamePlayDisplay.displayInvalidInputError();
            if (scanner.hasNext()){
                scanner.next();
            }
        }
        return scanner.nextInt();
    }
}
