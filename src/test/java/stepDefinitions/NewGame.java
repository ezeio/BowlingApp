package stepDefinitions;

import com.app.Game;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NewGame {

    Game game = new Game();

    @Given("^The user is ready to start a new game$")
    public void theUserIsReadyToStartANewGame() throws Throwable {
        game.startGame();
    }

    @Given("^I select a new game$")
    public void iSelectANewGame() throws Throwable {
        game.selectNewGame();
    }

    @When("^I am asked for the number of players between one and four inclusive$")
    public void iAmAskedForTheNumberOfPlayersBetweenOneAndFourInclusive() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I provide the number of players$")
    public void iProvideTheNumberOfPlayers() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
