package stepDefinitions;

import com.app.display.GameDisplay;
import com.app.game.Game;
import com.app.model.Frame;
import com.app.model.Player;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CalculateStrike {

    BowlingAppHook hook = new BowlingAppHook();
    private Game game = hook.game;
    private GameDisplay gameDisplay = BowlingAppHook.gameDisplay;
    private ByteArrayOutputStream outContent = BowlingAppHook.outContent;
    private ByteArrayOutputStream errContent = BowlingAppHook.errContent;
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n1\n2\nmike\ntyson\nyes\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n".getBytes());
    private Player player = new Player("mike","tyson");

    @Given("^I start a new game with a single player$")
    public void iStartANewGameWithASinglePlayer() throws Throwable {
        game.setGameDisplay(gameDisplay);
        System.setIn(inputStream);
        game.startGame();
    }

    @Given("^I roll the bowling ball as my first roll on a \"([^\"]*)\"$")
    public void iRollTheBowlingBallAsMyFirstRollOnA(String frameNum) throws Throwable {
        Frame aFrame = game.getGamePlay().getScore(player).getFrame(Integer.parseInt(frameNum));
        assertNotEquals( aFrame.getFirstRoll(), -1);
        assertEquals(aFrame.getSecondRoll(),-1);
    }


    @When("^I \"([^\"]*)\" ten pins for that \"([^\"]*)\"$")
    public void iTenPinsForThat(String hit, String frameNum) throws Throwable {
        Frame aFrame = game.getGamePlay().getScore(player).getFrame(Integer.parseInt(frameNum));
        assertEquals(aFrame.getFirstRoll(), Integer.parseInt(hit));
    }



    @Then("^the \"([^\"]*)\" \"([^\"]*)\" is ten plus the score of my next two rolls$")
    public void theIsTenPlusTheScoreOfMyNextTwoRolls(String frameNum, String score) throws Throwable {
        Frame aFrame = game.getGamePlay().getScore(player).getFrame(Integer.parseInt(frameNum));
        assertEquals(aFrame.getTotalFrameScore(), Integer.parseInt(score));
    }
}
