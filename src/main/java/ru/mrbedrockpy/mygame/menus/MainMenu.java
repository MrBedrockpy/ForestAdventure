package ru.mrbedrockpy.mygame.menus;

import org.jetbrains.annotations.Range;
import ru.mrbedrockpy.mygame.GameUtil;
import ru.mrbedrockpy.mygame.game.Game;

import java.util.Scanner;

public class MainMenu {

    public static void launch() {

        System.out.println("        ====================");

        System.out.println("        |  Adventure Game  |");

        System.out.println("        ====================");

        System.out.println("        |  1. Новая игра   |");
        System.out.println("        |  2. Продолжить   |");
        System.out.println("        |  3. Настройки    |");

        System.out.println("        ====================");

        System.out.println();

        System.out.print(" > ");

        @Range(from = 1, to = 3) int num = new Scanner(System.in).nextInt();

        GameUtil.clearConsole();

        switch (num) {

            case 1:
                Game.start();
                break;

            case 2:

                System.out.println(" Скоро! ");

                GameUtil.sleep(1);

                GameUtil.clearConsole();

                launch();
                break;

            case 3:
                SettingsMenu.launch();
                break;

            default:

                System.out.println(" Не допустимое значение! ");
                System.out.println(" Повторите еще раз! ");

                GameUtil.sleep(1);

                GameUtil.clearConsole();

                launch();
                break;

        }

    }

}
