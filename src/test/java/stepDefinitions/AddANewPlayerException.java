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

public class AddANewPlayerException {

    private Game game = BowlingAppHook.game;
    private GameDisplay gameDisplay = BowlingAppHook.gameDisplay;
    private ByteArrayOutputStream outContent = BowlingAppHook.outContent;
    private ByteArrayOutputStream errContent = BowlingAppHook.errContent;
    private ByteArrayInputStream inputStream = BowlingAppHook.inputStream;


    @Given("^I have started a new game$")
    public void iHaveStartedANewGame() throws Throwable {

        inputStream = new ByteArrayInputStream("1\n2\n2\nola\nozo\n2\nola\nozo\n2\npipa\noyana\n".getBytes());
        System.setIn(inputStream);
        game.startGame();
    }

    @And("^I have selected the number of players i desire$")
    public void iHaveSelectedTheNumberOfPlayersIDesire() throws Throwable {
        assertEquals(2,game.getGamePlay().getNumberOfCurrentPlayers());
    }

    @Then("^A request is made to add a new player$")
    public void aRequestIsMadeToAddANewPlayer() throws Throwable {
        assertTrue(outContent.toString().contains("[2] new player"));
    }
    @When("^I enter the player name$")
    public void iEnterThePlayerName() throws Throwable {
        //Player name is already provided in the byte stream (for example henry ozomena)
    }

    @And("^the player already exists in one of the player lists$")
    public void thePlayerAlreadyExistsInOneOfThePlayerLists() throws Throwable {

        Player[] players = game.getGamePlay().getCurrentPlayers();
        assertTrue(players[0].equals(new Player("ola", "ozo")));
    }

    @Then("^I should be informed that the player already exists$")
    public void iShouldBeInformedThatThePlayerAlreadyExists() throws Throwable {
        outContent.toString().contains("an existing player already has that first and last name combination");
    }

    @And("^I should be given the option to go back to the previous menu$")
    public void iShouldBeGivenTheOptionToGoBackToThePreviousMenu() throws Throwable {
        outContent.toString().contains("[cancel] back");
    }
}
