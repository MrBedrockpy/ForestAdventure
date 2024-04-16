package ru.mrbedrockpy.forestadventure.saves;

import com.google.gson.Gson;
import org.jetbrains.annotations.Nullable;
import ru.mrbedrockpy.forestadventure.GameUtil;
import ru.mrbedrockpy.forestadventure.game.Game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadGameManager {

    private static final Gson gson = new Gson();

    @Nullable
    public static GameData loadGame() {

        File savesDir = new File(System.getProperty("user.dir") + File.separator + "saves");

        if (!savesDir.exists()) {
            return null;
        }

        File[] savesFiles = savesDir.listFiles();

        if (savesFiles == null || savesFiles.length == 0) {
            return null;
        }

        List<File> saves = new ArrayList<>();

        List<String> lines = new ArrayList<>();

        lines.add(" ");

        lines.add(" Сохранения:");

        lines.add(" ");

        for (File save: savesFiles) {

            if (!save.canWrite()) {
                continue;
            } else if (!save.getName().endsWith(".json")) {
                continue;
            }

            lines.add((saves.size() + 1) + ". " + save.getName().replace(".json", ""));

            saves.add(save);

        }

        lines.add(" ");

        GameUtil.screen(lines);

        System.out.println(" ");
        System.out.println("Введите номер сохранение для загрузки");

        System.out.println(" ");
        System.out.print(" > ");

        int input = GameUtil.scanner.nextInt();

        StringBuilder stringJson = new StringBuilder();

        GameData data;

        try {
            File save = saves.get(input - 1);

            try {

                FileReader fileReader = new FileReader(save);
                Scanner scanner = new Scanner(fileReader);

                while (scanner.hasNextLine()) {
                    stringJson.append(scanner.nextLine()).append("\n");
                }

                data = gson.fromJson(stringJson.toString(), GameData.class);
                fileReader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Некорректный вариант");
            GameUtil.sleep(1);
            GameUtil.clearConsole();
            return loadGame();
        }

        return data;

    }

}
