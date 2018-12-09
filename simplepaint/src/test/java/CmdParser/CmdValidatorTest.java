package CmdParser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CmdValidatorTest {

    private CmdValidator cmdValidator;

    @Before
    public void initData() {
        cmdValidator = new CmdValidator();
    }

    @Test
    public void matchesAtLeastOneRegex() {
        boolean canvasCommandValid = cmdValidator.matchesCanvasRegex("C 12 13");
        Assert.assertTrue(canvasCommandValid);
    }

    @Test
    public void matchesAtLeastOneRegexLine() {
        boolean canvasCommandValid = cmdValidator.matchesLineRegex("L 12 13 14 15");
        Assert.assertTrue(canvasCommandValid);
    }

    @Test
    public void matchesAtLeastOneRegexRect() {
        boolean canvasCommandValid = cmdValidator.matchesReactangleRegex("R 1 1 1 6");
        Assert.assertTrue(canvasCommandValid);
    }

    @Test
    public void invalidOne() {
        boolean canvasCommandValid = cmdValidator.matchesLineRegex("D 12 12");
        Assert.assertFalse(canvasCommandValid);
    }

    @Test
    public void invalidTwo() {
        boolean canvasCommandValid = cmdValidator.matchesLineRegex("LCR 12 12 80 19");
        Assert.assertFalse(canvasCommandValid);
    }

    @Test
    public void invalidlessPoints() {
        boolean canvasCommandValid = cmdValidator.matchesLineRegex("L 1 2 3");
        Assert.assertFalse(canvasCommandValid);

        canvasCommandValid = cmdValidator.matchesLineRegex("R 10 20 30");
        Assert.assertFalse(canvasCommandValid);

    }
}