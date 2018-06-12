package stepDefinitions;

import com.app.display.GameDisplay;
import com.app.game.Game;
import com.app.model.Player;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddANewPlayerException {

    BowlingAppHook hook = new BowlingAppHook();
    private Game game = hook.game;
    private ByteArrayOutputStream outContent = BowlingAppHook.outContent;
    private ByteArrayInputStream inputStream = BowlingAppHook.inputStream;


    @When("^I enter the player name$")
    public void iEnterThePlayerName() throws Throwable {
        inputStream = new ByteArrayInputStream("1\n2\n2\nola\nozo\n2\nola\nozo\n2\npipa\noyana\n".getBytes());
        System.setIn(inputStream);
        game.startGame();
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

    @When("^I am asked for a name$")
    public void iAmAskedForAName() throws Throwable {
        inputStream = new ByteArrayInputStream("1\n2\n2\nnkem\ncancel\n2\nlalabi\nuche\n2\nozomena\nozomena\n".getBytes());
        System.setIn(inputStream);
        game.startGame();
        assertTrue(outContent.toString().contains("[A-Z a-z] input name"));
        assertTrue(outContent.toString().contains("nkem"));
    }

    @And("^I input cancel instead of a valid name$")
    public void iInputCancelInsteadOfAValidName() throws Throwable {
        //The cancel was inputted as the last name value in the byte stream
    }

    @Then("^I should be taken back to the provide player name menu$")
    public void iShouldBeTakenBackToTheProvidePlayerNameMenu() throws Throwable {
        boolean playerNotAdded = false;
        for (Player aPlayer : game.getGamePlay().getCurrentPlayers()) {
            if(aPlayer != null && aPlayer.equals(new Player("nkem","cancel"))){
                playerNotAdded = true;
            }
        }
        assertTrue(outContent.toString().contains("provide player names menu"));
        assertFalse(playerNotAdded);

    }
}
