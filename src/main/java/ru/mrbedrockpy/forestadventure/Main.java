package ru.mrbedrockpy.forestadventure;

import ru.mrbedrockpy.forestadventure.menus.MainMenu;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("    Добро пожаловать в игру!    ");
        System.out.println("================================");

        GameUtil.sleep(2);

        GameUtil.clearConsole();

        MainMenu.launch();

    }

}
