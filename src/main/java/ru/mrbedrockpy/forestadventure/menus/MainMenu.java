package ru.mrbedrockpy.forestadventure.menus;

import ru.mrbedrockpy.forestadventure.GameUtil;
import ru.mrbedrockpy.forestadventure.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    public static void launch() {

        List<String> lines = new ArrayList<>();

        lines.add(" ");

        lines.add("   Forest Adventure ");

        lines.add(" ");

        lines.add(" 1. Новая игра ");
        lines.add(" 2. Продолжить ");
        lines.add(" 3. Настройки ");

        lines.add(" ");

        GameUtil.screen(lines);

        System.out.println(" ");

        System.out.print(" > ");

        int num = new Scanner(System.in).nextInt();

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
