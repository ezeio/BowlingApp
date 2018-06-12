package com.app.gameplay.impl;

import com.app.display.GamePlayDisplay;
import com.app.display.impl.TenPinGamePlayDisplay;
import com.app.gameplay.GamePlay;
import com.app.model.TenPinGameScore;
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
    private Map <Player, TenPinGameScore> currentGamePlayScore;
    private static final int NUMBER_OF_FRAMES = 10;

    public GamePlayValidator getGamePlayValidator() {
        return gamePlayValidator;
    }

    public void setGamePlayValidator(GamePlayValidator gamePlayValidator) {
        this.gamePlayValidator = gamePlayValidator;
    }

    private static Set<Player> getExistingPlayers() {
        return existingPlayers;
    }
    @Override
    public int getNumberOfCurrentPlayers() {
        return currentPlayers.length;
    }

    public void setUpGamePlay(Scanner keyboardInput, int numberOfPlayers){
        currentGamePlayScore = new HashMap<>();
        gamePlayValidator = new TenPinGamePlayValidator();
        setGamePlayDisplay(new TenPinGamePlayDisplay());
        setScanner(keyboardInput);
        getScanner().useDelimiter("\n");
        setUpPlayers(numberOfPlayers);
        setUpScoreboard();


    }

    private void setUpScoreboard() {

        for (Player player : this.getCurrentPlayers()) {
            this.getCurrentGamePlayScore().put(player, new TenPinGameScore(NUMBER_OF_FRAMES));
        }
    }

    private void endGamePlay(){

    }

    public boolean isStartGameSelected() {
        gamePlayDisplay.startOrEndGameMsg();
        String result = readStringInput();
        while (!(result.equals("yes") || result.equals("no"))){
            gamePlayDisplay.displayInvalidInputError();
            result = readStringInput();
        }
        return result.equalsIgnoreCase("yes");
    }

    @Override
    public void startGamePlay() {
        TenPinGameScore gameScore = null;
        int pinsDown;
        int maxPinsPerFrame = 10;

        for (int frameNum = 1; frameNum < NUMBER_OF_FRAMES; frameNum++) {

            for (int player = 0; player < currentPlayers.length; player++ ){
                int frameIndex = frameNum -1;
                System.out.println(currentPlayers[player].getFormattedName()+ ""+" is playing");
                gameScore = currentGamePlayScore.get(currentPlayers[0]);
                while (!gameScore.isNextPlayerTurn(frameIndex)){
                    pinsDown = bowl(maxPinsPerFrame);

                    gameScore.calculate(frameIndex, pinsDown);
                }
                maxPinsPerFrame = 10;
            }

        }

        System.out.println("TODO: starting game");
        //TODO STARTING GAME PLAY
    }

    private int bowl(int maxPinsPerFrame) {
        int pinsDown = readIntInput();
        while (!(pinsDown > -1 && pinsDown <= maxPinsPerFrame)){
            //Display message to knock down the correct number of pins
            System.out.println("knock down the right number of pins");
        }
        return pinsDown;
    }

    @Override
    public void setUpPlayers(int numberOfPlayers) {
        setCurrentPlayers(new Player[numberOfPlayers]);
        playerDetailsMenu();
    }

    public void playerDetailsMenu(){
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

    @Override
    public TenPinGameScore getScore(Player player) {
        if(getCurrentGamePlayScore().containsKey(player))return getCurrentGamePlayScore().get(player);

        return null;
    }

    private boolean isCurrentPlayerListFull() {
        if(currentPlayers.length > 0 ){
            return currentPlayers[currentPlayers.length - 1] != null;
        }
        return false;
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
        int intInput = 0;
        String stringInput = "";
        boolean isValidInput;
        Player[] existingPlayers = TenPinGamePlay.getExistingPlayers().toArray(new Player[TenPinGamePlay.getExistingPlayers().size()]);
        gamePlayDisplay.existingPlayerMenu(existingPlayers);

        do {
            if (getScanner().hasNextInt()) intInput = readIntInput();
            if (!(intInput > 0) && getScanner().hasNext()) stringInput = readStringInput();
            isValidInput = (intInput > 0 && intInput <= gamePlayDisplay.getExistingPlayerMenu().size())  || (stringInput.toLowerCase().equals("cancel"));
            if(!isValidInput)gamePlayDisplay.selectValidOptionOrCancel();
        } while (!isValidInput);
        if(stringInput.equals("cancel")){playerDetailsMenu();return;}

        Player player = existingPlayers[intInput-1];
        addPlayerToCurrentList(player);
        playerDetailsMenu();
        System.out.println("TODO");//TODO
    }

    private void addPlayerToCurrentList(Player player) {
//        for (int i =  0; i < currentPlayers.length; i++){
//            if (currentPlayers[i] != null && currentPlayers[i].equals(player)) {
//
//                gamePlayDisplay.playerAlreadyAddedError();
//                return;
//            }
//        }
        for (Player aPlayer : currentPlayers) {
            if(aPlayer != null && aPlayer.equals(player)){
                gamePlayDisplay.playerAlreadyAddedError();
                return;
            }
        }
        for(int i = 0; i < currentPlayers.length; i++){
            if(currentPlayers[0] == null){
                currentPlayers[0] = player;
            }
        }
    }

    private void addNewPlayer() {
        String firstName = null;
        String lastName = null;
        gamePlayDisplay.addNewPlayerMenu(firstName, lastName);
        firstName = readStringInput();
        if(isCanceled(firstName)){ playerDetailsMenu(); return;}
        gamePlayDisplay.addNewPlayerMenu(firstName,lastName);
        lastName = readStringInput();
        if(isCanceled(lastName)){ playerDetailsMenu(); return;}
        gamePlayDisplay.addNewPlayerMenu(firstName,lastName);
        Player player = createNewPlayer(firstName, lastName);
        addPlayerToCurrentAndExistingList(player);
        playerDetailsMenu();
    }

    private boolean isCanceled(String lastName) {
        return lastName.toLowerCase().matches("cancel");
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

    public Map<Player, TenPinGameScore> getCurrentGamePlayScore() {
        return currentGamePlayScore;
    }

    public void setCurrentGamePlayScore(Map<Player, TenPinGameScore> currentGamePlayScore) {
        this.currentGamePlayScore = currentGamePlayScore;
    }
}
