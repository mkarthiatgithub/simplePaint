import CmdParser.CmdParser;

import java.util.Scanner;

public class SimplePaint {
    public static void main(String[] args) {
        final Scanner sis = new Scanner(System.in);
        CmdParser cmd = new CmdParser();
        cmd.getCommandPaint(sis);
    }
}
