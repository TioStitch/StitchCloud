package org.tiostitch.socket.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.PrintStream;

@Getter
@AllArgsConstructor
public enum Colors {
    RED("§c", RGB("255", "50", "50")),
    CYAN("§b", RGB("120", "190", "240")),
    GREEN("§a", RGB("120", "255", "120")),
    GRAY("§7", RGB("100", "100", "100")),
    PURPLE("§5", RGB("140", "80", "180")),
    WHITE_GRAY("§f", RGB("255", "255", "255"));

    private static String RGB(String R, String G, String B) {
        return "\u001B[38;2;" + R + ";" + G + ";" + B + "m";
    }

    private final String symbol;
    private final String AC;

    public static void srvPrintar(String message) {
        for (Colors str : Colors.values()) {
            message = message.replace(str.getSymbol(), str.getAC());
        }
        System.out.println(message);
    }

    public static void srvPrintar(PrintStream print, String message) {
        for (Colors str : Colors.values()) {
            message = message.replace(str.getSymbol(), str.getAC());
        }
        print.println(message);
    }
}
