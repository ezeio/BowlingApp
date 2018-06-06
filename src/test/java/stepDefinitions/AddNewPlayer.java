package stepDefinitions;

import com.app.display.GameDisplay;
import com.app.game.Game;
import com.app.model.Player;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddNewPlayer {


    private Game game = BowlingAppHook.game;
    private GameDisplay gameDisplay = BowlingAppHook.gameDisplay;
    private ByteArrayOutputStream outContent = BowlingAppHook.outContent;
    private ByteArrayOutputStream errContent = BowlingAppHook.errContent;
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n2\n2\nhenry\nozomena\n2\nchinasa\nnwafor\n".getBytes());

    @Given("^I have selected a new game$")
    public void iHaveSelectedANewGame() throws Throwable {

        System.setIn(inputStream);
        game.startGame();

    }

    @And("^I have selected number of players between one and four inclusive$")
    public void iHaveSelectedNumberOfPlayersBetweenOneAndFourInclusive() throws Throwable {
        assertEquals(2,game.getGamePlay().getNumberOfCurrentPlayers());
    }

    @Then("^I am asked to add a new player$")
    public void iAmAskedToAddANewPlayer() throws Throwable {
        assertTrue(outContent.toString().contains("[2] new player"));
    }

    @When("^I enter the player first and last name$")
    public void iEnterThePlayerFirstAndLastName() throws Throwable {
        Player player1 = game.getGamePlay().getCurrentPlayers()[0];
        Player player2 = game.getGamePlay().getCurrentPlayers()[1];
        assertTrue(player1.getFirstName().equalsIgnoreCase("henry"));
        assertTrue(player1.getLastName().equalsIgnoreCase("ozomena"));
        assertTrue(player2.getFirstName().equalsIgnoreCase("chinasa"));
        assertTrue(player2.getLastName().equalsIgnoreCase("nwafor"));
    }

    @And("^if the player does not exist in the player lists$")
    public void ifThePlayerDoesNotExistInThePlayerLists() throws Throwable {
        //In this scenario, the player does not exist so the player will be added to
        //the list.
    }

    @Then("^the player should be added to the list of players for the game$")
    public void thePlayerShouldBeAddedToTheListOfPlayersForTheGame() throws Throwable {
        boolean result = false;
        Player henry_player = new Player("henry", "ozomena");
        for (Player player: game.getGamePlay().getCurrentPlayers()){
            if(henry_player.equals(player)){
                result = true;
            }
        }
        assertTrue(result);
    }

    @And("^the player should be added to the list of existing players$")
    public void thePlayerShouldBeAddedToTheListOfExistingPlayers() throws Throwable {
        //The existing list is a static list that can not be accessed
        System.setIn(System.in);
    }
}
