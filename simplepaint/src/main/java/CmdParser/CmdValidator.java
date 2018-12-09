package CmdParser;

import java.util.regex.Pattern;

public class CmdValidator {
    private Pattern CANVAS_PATTERN;
    private Pattern LINE_PATTERN;
    private Pattern RECT_PATTERN;

    private static final String CANVASPATTERN = "^C\\s\\d\\d?\\s\\d\\d?$";
    private static final String LINEPATTERN = "^L\\s\\d\\d?\\s\\d\\d?\\s\\d\\d?\\s\\d\\d?$";
    private static final String RECTPATTERN = "^R\\s\\d\\d?\\s\\d\\d?\\s\\d\\d?\\s\\d\\d?$";


    public CmdValidator() {
        CANVAS_PATTERN = Pattern.compile(CANVASPATTERN);
        LINE_PATTERN = Pattern.compile(LINEPATTERN);
        RECT_PATTERN = Pattern.compile(RECTPATTERN);
    }

    public boolean matchesCanvasRegex(final String command) {
        return CANVAS_PATTERN.matcher(command).matches();
    }

    public boolean matchesLineRegex(final String command) {
        return LINE_PATTERN.matcher(command).matches();
    }

    public boolean matchesReactangleRegex(final String command) {
        return RECT_PATTERN.matcher(command).matches();
    }
}
