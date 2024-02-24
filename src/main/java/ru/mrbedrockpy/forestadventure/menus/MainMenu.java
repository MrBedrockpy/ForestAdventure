package ru.mrbedrockpy.forestadventure.menus;

import org.jetbrains.annotations.Range;
import ru.mrbedrockpy.forestadventure.GameUtil;
import ru.mrbedrockpy.forestadventure.game.Game;

import java.util.Scanner;

public class MainMenu {

    public static void launch() {

        System.out.println("        ====================");

        System.out.println("        | Forest Adventure |");

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
            case 3:
                System.out.println(" Скоро! ");
                GameUtil.sleep(1);
                GameUtil.clearConsole();
                launch();
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
