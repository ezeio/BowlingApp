package com.app.display.impl;

import com.app.display.GamePlayDisplay;
import com.app.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TenPinGamePlayDisplay implements GamePlayDisplay {

    private List<String> playerDetailsMenu;
    private List<String> addNewPlayerMenu;
    private List<String> existingPlayerMenu;

    public TenPinGamePlayDisplay(){
        setUpMenus();
    }

    private void setUpMenus() {
        playerDetailsMenu = new ArrayList<>();
        addNewPlayerMenu = new ArrayList<>();
        existingPlayerMenu = new ArrayList<>();
        playerDetailsMenu.add("[1] use existing player");
        playerDetailsMenu.add("[2] new player");
        addNewPlayerMenu.add("[A-Z a-z] input name");
        addNewPlayerMenu.add("[cancel] back");
        existingPlayerMenu.add("[1 - *] select player");
        existingPlayerMenu.add("[cancel] back");
    }

    @Override
    public void displayPlayerDetailsMenu(Player[] numberOfPlayers, boolean listIsFull) {
        Player player;
        System.out.println("\n\nprovide player names menu");
        System.out.println();
        System.out.println();
        for (int i = 0; i < numberOfPlayers.length; i++) {
            player = numberOfPlayers[i];
            System.out.print("Player "+ (i+1) + ": ");
            if(player != null && player.getLastName() != null){
                System.out.println("\t"+player.getFirstName() + " " + player.getLastName());
            }
            else {
                System.out.println("\t *");
            }
        }
        System.out.println();
        /*if(listIsFull){
            System.out.println("loading game ....... .. .. . .. .");
            return;
        }*/
        if(!listIsFull){
            playerDetailsMenu.forEach((option -> System.out.print(option+"\t\t")));
            System.out.println();
        }
    }

    public List<String> getPlayerDetailsMenu() {
        return playerDetailsMenu;
    }

    @Override
    public void displayOptionSelectorError() {
        System.out.println("Please select number that corresponds to an option");
    }

    @Override
    public void addNewPlayerMenu(String firstName, String lastName) {
        System.out.println("\n");
        System.out.println("new player menu");
        System.out.println("\n");

        System.out.println(firstName != null?"First Name: "+ firstName:"First Name:  *");
        System.out.println(lastName  != null?"Last  Name: "+ lastName:"Last  Name:  *");
        System.out.println("\n");

        if(firstName != null && lastName != null){
            System.out.println("provided names have been added to the list");
            return;
        }
        addNewPlayerMenu.forEach((option -> System.out.print(option + "\t\t")));
        System.out.println("\n");
    }

    @Override
    public void duplicatePlayerNameError() {
        System.err.println("an existing player already has that first and last name combination");
    }

    @Override
    public void existingPlayerMenu(Player[] existingPlayers) {
        System.out.println("existing player(s) menu");
        System.out.println();
        System.out.println();
        System.out.println();

        for(int i = 0; i < existingPlayers.length; i++){
            System.out.println("["+(i+1)+"] \t" + existingPlayers[i]);
        }

        System.out.println();
        System.out.println();
        System.out.println();

        existingPlayerMenu.forEach((option -> System.out.print(option + "\t\t")));
        System.out.println("\n\n");

    }

    @Override
    public void displayInvalidInputError() {
        System.err.println("Please provide a valid value");
    }
    @Override
    public List<String> getExistingPlayerMenu() {
        return existingPlayerMenu;
    }

    @Override
    public void playerAlreadyAddedError() {
        System.err.println("The player has already been added to the list\n" +
                "");
    }

    @Override
    public void selectValidOptionOrCancel() {
        System.err.println("Please select a valid option or type cancel to quit");
    }

    @Override
    public void startOrEndGameMsg() {
        System.out.println("\n\n");
        System.out.println("[yes] start game [no] end game");
    }
}
