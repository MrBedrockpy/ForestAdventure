package ru.mrbedrockpy.forestadventure;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameUtil {

    public static Scanner scanner = new Scanner(System.in);

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

    public static void screen(List<String> lines) {
        int maxLength = 0;
        for (String line: lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        maxLength += 3;

        for (int i = 0; i < maxLength + 4; i++) {
            System.out.print("=");
        }
        System.out.println();

        for (String line: lines) {

            System.out.print("| ");

            System.out.print(line);

            for (int i = 0; i < maxLength - line.length(); i++) {
                System.out.print(" ");
            }
            System.out.println(" |");

        }

        for (int i = 0; i < maxLength + 4; i++) {
            System.out.print("=");
        }
        System.out.println();

    }

}
