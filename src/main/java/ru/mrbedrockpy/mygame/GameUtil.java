package ru.mrbedrockpy.mygame;

import java.io.IOException;

public class GameUtil {

    public static void sleep(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
