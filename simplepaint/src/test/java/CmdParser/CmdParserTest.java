package CmdParser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CmdParserTest {

    private CmdParser cmdParser;
    private String cCcommand = "C 12 13";
    private String lCommand = "L 12 13 14 15";

    @Before
    public void initData() {
        cmdParser = new CmdParser();
    }

    @Test
    public void getPointsC() {
        List<Integer> pointsExpeected = new ArrayList<>();
        pointsExpeected.add(12);
        pointsExpeected.add(13);
        String[] splitcCcommand = cCcommand.split(" ");
        List<Integer> points = cmdParser.getPoints(splitcCcommand);
        Assert.assertEquals(points, pointsExpeected);
    }

    @Test
    public void getPointsL() {
        List<Integer> pointsExpeected = new ArrayList<>();
        pointsExpeected.add(12);
        pointsExpeected.add(13);
        pointsExpeected.add(14);
        pointsExpeected.add(15);
        String[] splitcCcommand = lCommand.split(" ");
        List<Integer> points = cmdParser.getPoints(splitcCcommand);
        Assert.assertEquals(points, pointsExpeected);
    }

}