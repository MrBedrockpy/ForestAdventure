package ru.mrbedrockpy.forestadventure.saves;

import com.google.gson.Gson;
import ru.mrbedrockpy.forestadventure.GameUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveGameManager {

    private static final Gson gson = new Gson();
    
    public static void saveGame(GameData data) {

        File savesDir = new File(System.getProperty("user.dir") + File.separator + "saves");

        if (!savesDir.exists()) {
            if (!savesDir.mkdir()) printError(2);
        }

        File[] savesFiles = savesDir.listFiles();

        if (savesFiles == null || savesFiles.length == 0) {
            createNewSave(data);
            return;
        }

        List<File> saves = new ArrayList<>();

        List<String> lines = new ArrayList<>();

        lines.add(" ");

        lines.add(" Сохранения:");

        lines.add(" ");

        for (File save: savesFiles) {

            if (!save.canWrite()) {
                printError(4);
                continue;
            } else if (!save.getName().endsWith(".json")) {
                printError(5);
                continue;
            }

            lines.add((saves.size() + 1) + ". " + save.getName().replace(".json", ""));

            saves.add(save);

        }

        lines.add(" ");

        GameUtil.screen(lines);

        System.out.println(" ");
        System.out.println(" Введите номер сохранение для перезаписи или 0 для создания нового сохранения");

        System.out.println(" ");
        System.out.print(" > ");

        int input = GameUtil.scanner.nextInt();

        if (input == 0) {
            createNewSave(data);
            return;
        }

        try {
            File save = saves.get(input - 1);

            try {
                FileWriter fileWriter = new FileWriter(save);
                fileWriter.write(gson.toJson(data));
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Некорректный вариант");
        }

    }

    private static void createNewSave(GameData data) {

        File savesDir = new File("saves");

        System.out.println(" ");

        System.out.print(" Введите название > ");

        File save = new File(savesDir.getAbsolutePath() + "/" + GameUtil.scanner.next() + ".json");

        try {
            save.getParentFile().mkdirs();
            save.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter fileWriter = new FileWriter(save);
            fileWriter.write(gson.toJson(data));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void printError(int status) {
        GameUtil.clearConsole();
        System.out.println("Save game failed! Status: " + status);
        GameUtil.sleep(1);
        GameUtil.clearConsole();
        System.exit(0);
    }

}
