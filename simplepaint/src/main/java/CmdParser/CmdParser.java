package CmdParser;

import Draw.PaintLine;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class CmdParser {
    private int canvasRows;
    private int canvasCols;
    private CmdValidator cmdValidator = new CmdValidator();
    private Point[][] gridOfCoordinates;
    private static final Set<String> EXIT_COMMANDS;
    private static final Set<String> HELP_COMMANDS;
    private static final String HELP_MESSAGE = "" +
            "Hi , Following commands are allowed  \n" +
            "C w h\t          Create a new canvas of width w and height h\n" +
            "L x1 x2 y1 y2\t  Draw a new line from coordinates (x1, y1) to (x2, y2) horizontally or vertically.\n" +
            "R x1 x2 y1 y2\t  Draw a new rectangle, with upper left corner at coordinate (x1, y1) and lower right coordinate at (x2, y2).\n" +
            "Q\t              Quit the program" +
            "";

    static {
        final SortedSet<String> ecmds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        ecmds.addAll(Arrays.asList("exit", "Q"));
        EXIT_COMMANDS = Collections.unmodifiableSortedSet(ecmds);
        final SortedSet<String> hcmds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        hcmds.addAll(Arrays.asList("help", "h", "?"));
        HELP_COMMANDS = Collections.unmodifiableSet(hcmds);
    }

    private static void output(final String format, final Object... args) {
        System.out.println(format(format, args));
    }


    /**
     * Function to get CoOrdinates in the command , Skip Command Char and return rest of 2 of 4 numbers
     *
     * @param givenCommand
     * @return Coordinates of command
     */
    public List<Integer> getPoints(String[] givenCommand) {
        List<Integer> coordinates = new ArrayList<>();
        coordinates = Arrays.stream(givenCommand).skip(1).map(Integer::parseInt).collect(Collectors.toList());
        return coordinates;
    }

    public void getCommandPaint(Scanner sis) {
        PaintLine paintLine = new PaintLine();
        System.out.println(HELP_MESSAGE);
        String nextLine = "";
        while (sis.hasNextLine()) {
            nextLine = sis.nextLine();
            if (cmdValidator.matchesCanvasRegex(nextLine)) {
                List<Integer> canvasPoints = getPoints(nextLine.split(" "));
                output("You entered canVas command = %s", nextLine + "and Points" + canvasPoints.toString());
                canvasRows = canvasPoints.get(0);
                canvasCols = canvasPoints.get(1);
                gridOfCoordinates = paintLine.getGridOfCoordinates(canvasRows, canvasCols);

            } else if (cmdValidator.matchesLineRegex(nextLine)) {
                output("You entered command = %s", nextLine);
                List<Integer> linePoints = getPoints(nextLine.split(" "));
                List<Point> line = paintLine.findLine(gridOfCoordinates, linePoints.get(0), linePoints.get(1), linePoints.get(2), linePoints.get(3));
                paintLine.plot(gridOfCoordinates, line);
            } else if (cmdValidator.matchesReactangleRegex(nextLine)) {
                output("You entered command R = %s", nextLine);
                List<Integer> linePoints = getPoints(nextLine.split(" "));
                int sr = linePoints.get(0); //A 5
                int sc = linePoints.get(1); //B 10
                int fr = linePoints.get(2); //C 15
                int fc = linePoints.get(3); //D 20
                List<Point> line = paintLine.findLine(gridOfCoordinates, sr, sc, sr, fc);
                List<Point> line2 = paintLine.findLine(gridOfCoordinates, fr, sc, fr, fc);
                List<Point> line3 = paintLine.findLine(gridOfCoordinates, sr, sc, fr, sc);
                List<Point> line4 = paintLine.findLine(gridOfCoordinates, sr, fc, fr, fc);
                line.addAll(line2);
                line.addAll(line3);
                line.addAll(line4);
                paintLine.plot(gridOfCoordinates, line);

            } else {
                if (EXIT_COMMANDS.contains(nextLine)) {
                    output("Exit command %s issued, exiting!", nextLine);
                    System.exit(0);
                } else if (HELP_COMMANDS.contains(nextLine)) {
                    output(HELP_MESSAGE);
                } else {
                    output("You entered an unknown Command = %s", nextLine);
                    output(HELP_MESSAGE);
                }
            }
        }
        sis.close();
    }
}


