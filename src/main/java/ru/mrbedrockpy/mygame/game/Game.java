package ru.mrbedrockpy.mygame.game;

import ru.mrbedrockpy.mygame.GameUtil;
import ru.mrbedrockpy.mygame.game.input.InventoryInput;
import ru.mrbedrockpy.mygame.game.inventory.Inventory;
import ru.mrbedrockpy.mygame.game.item.Item;
import ru.mrbedrockpy.mygame.game.player.Player;
import ru.mrbedrockpy.mygame.game.position.Position;
import ru.mrbedrockpy.mygame.game.world.territory.Location;
import ru.mrbedrockpy.mygame.game.worldgenerator.WorldGenerator;
import ru.mrbedrockpy.mygame.game.world.World;
import ru.mrbedrockpy.mygame.game.world.territory.Territory;
import ru.mrbedrockpy.mygame.game.input.*;

import java.util.*;

public class Game {

    public static Scanner scanner = new Scanner(System.in);

    private static boolean testerMode = false;

    public static void start() {

        history();

        first_quest();

        game();

    }

    private static void game() {

        World world = WorldGenerator.generate(20, 20);

        Player player = new Player(new Position(10, 10));

        if (testerMode) {player.getInventory().addItem(Item.GOLDEN_KEY);}

        while (true) {

            Map<Integer, Input> controllers = new HashMap<>();

            List<String> lines = new ArrayList<>();

            lines.add(" ");

            for (Territory territory: world.getTerritories()) {
                if (territory.getPosition().equals(player.getPosition())) {

                    // Information of about this place

                    lines.add("Информация о месности:");

                    lines.add(" Тип местности: " + territory.getType().name());

                    lines.add(" Координаты: " + player.getPosition().getX() + ", " + player.getPosition().getY());

                    lines.add(" ");

                    if (territory.getLocation() != null) {

                        Location location = territory.getLocation();

                        lines.add("Локация:");

                        lines.add(" Название: " + location.getName());

                        lines.add(" Описание:");

                        lines.add(" ");

                        lines.addAll(location.getLore());

                        lines.add(" ");

                        List<Item> lootTable = location.getLootTable().generateLoot();

                        if (!lootTable.isEmpty()) {

                            lines.add(" При изучении локации вы нашли: ");

                            for (Item item: lootTable) {
                                lines.add(item.toString());
                                player.getInventory().addItem(item);
                            }
                        }

                        lines.add(" ");

                    }

                    // Controllers

                    // Movement

                    int numInput = 1;

                    if (World.doGoingNorth(player, world)) {

                        lines.add(" " + numInput + ". Двигаться на север");

                        controllers.put(numInput, new Input() {

                            @Override
                            public void function(Player player) {player.getPosition().setY(player.getPosition().getY() - 1);}

                        });

                        numInput++;

                    }

                    if (World.doGoingEast(player, world)) {

                        lines.add(" " + numInput + ". Двигаться на восток");

                        controllers.put(numInput, new Input() {

                            @Override
                            public void function(Player player) {player.getPosition().setX(player.getPosition().getX() + 1);}

                        });

                        numInput++;

                    }

                    if (World.doGoingSouth(player, world)) {

                        lines.add(" " + numInput + ". Двигаться на юг");

                        controllers.put(numInput, new Input() {

                            @Override
                            public void function(Player player) {player.getPosition().setY(player.getPosition().getY() + 1);}

                        });

                        numInput++;

                    }

                    if (World.doGoingWest(player, world)) {

                        lines.add(" " + numInput + ". Двигаться на запад");

                        controllers.put(numInput, new Input() {

                            @Override
                            public void function(Player player) {player.getPosition().setX(player.getPosition().getX() - 1);}

                        });

                        numInput++;

                    }

                    lines.add(" ");

                    // Inventory

                    lines.add(" " + numInput + ". Открыть инвентарь");

                    controllers.put(numInput, new Input() {

                        @Override
                        public void function(Player player) {
                            GameUtil.sleep(0.5);
                            GameUtil.clearConsole();
                            openInventory(player);
                        }

                    });

                    numInput++;

                    lines.add(" ");

                    // Other

                    lines.add(" " + numInput + ". Выйти из игры");

                    controllers.put(numInput, new Input() {

                        @Override
                        public void function(Player player) {
                            GameUtil.clearConsole();
                            System.exit(0);
                        }

                    });

                    lines.add(" ");

                    break;

                }
            }

            screen(lines);

            System.out.print(" > ");
            int input = scanner.nextInt();

            controllers.get(input).function(player);

            GameUtil.sleep(0.5);
            GameUtil.clearConsole();

        }

    }

    private static void openInventory(Player player) {

        Inventory inventory = player.getInventory();

        Map<Integer, InventoryInput> controllers = new HashMap<>();

        List<String> lines = new ArrayList<>();

        int numInput = 1;

        lines.add(" ");

        if (inventory.getSlots().isEmpty()) {

            lines.add(" В инвентаре пусто. Попробуйте найти что-нибудь, чтобы взаимодействовать с инвентарем!");

        } else {
            for (Item item: inventory.getSlots()) {
                lines.add(" " + numInput + ". " + item.getName());
                controllers.put(numInput, new InventoryInput() {

                    @Override
                    public void function(Inventory inventory, Item item) {

                        Map<Integer, InventoryInput> controllers = new HashMap<>();

                        List<String> lines = new ArrayList<>();

                        int numInput = 1;

                        lines.add(" ");

                        lines.add(" Предмет: " + item.getName());

                        lines.add(" ");

                        lines.add(" " + numInput + ". Выкинуть");
                        controllers.put(numInput, new InventoryInput() {

                            @Override
                            public void function(Inventory inventory, Item item) {
                                inventory.getSlots().remove(item);
                            }

                        });

                        numInput++;

                        lines.add(" ");

                        lines.add(" " + numInput + ". Отмена");
                        controllers.put(numInput, new InventoryInput() {

                            @Override
                            public void function(Inventory inventory, Item item) {

                            }

                        });

                        lines.add(" ");

                        screen(lines);

                        System.out.print(" > ");
                        int input = scanner.nextInt();

                        GameUtil.sleep(0.5);
                        GameUtil.clearConsole();

                        controllers.get(input).function(inventory, item);

                        GameUtil.sleep(0.5);
                        GameUtil.clearConsole();

                        openInventory(player);

                    }

                });
                numInput++;
            }
        }

        lines.add(" ");

        lines.add(" " + numInput + ". Выйти из инвентаря");
        controllers.put(numInput, new InventoryInput() {

            @Override
            public void function(Inventory inventory, Item item) {}

        });

        lines.add(" ");

        screen(lines);

        System.out.print(" > ");
        int input = scanner.nextInt();

        GameUtil.sleep(0.5);
        GameUtil.clearConsole();

        if (input == numInput) {
            return;
        }

        controllers.get(input).function(inventory, inventory.getSlots().get(input));

        GameUtil.sleep(0.5);
        GameUtil.clearConsole();

    }

    private static void history() {

        System.out.println(
                "Война! Война никогда не меняется.\n" +
                "Война началась снова. Но эта война\n" +
                "стирает все живое на планете.\n" +
                "Единственный способ выжить - уйти\n" +
                "от цивилизации!\n"
        );

        System.out.println("Введите любой символ");

        System.out.print(" > ");

        if (scanner.next().equals("test12321")) {
            testerMode = true;
        }

        GameUtil.clearConsole();

        System.out.println(
                " Я был не один такого мнения,\n" +
                "поэтому я присоединился к общине,\n" +
                "которая решила поселится в лесу.\n" +
                "По пути до места, упал в яму и\n" +
                "потерял сознание...\n" +
                " Когда я проснулся, не моих вещей,\nне отряда не было." +
                "Меня обокрали и оставили...\n"
        );

        System.out.println("Введите любой символ");

        System.out.print(" > ");

        scanner.next();

        GameUtil.clearConsole();

    }

    private static void first_quest() {

        System.out.println(
                "   Для начала нужно выбротся из ямы.\n" +
                "   Введите последовательность движений,\n" +
                "   для того, чтобы выбротся из ямы.\n"
        );

        System.out.println(
                "===========================\n" +
                "| 1. Левая рука           |\n" +
                "| 2. Правая рука          |\n" +
                "| 3. Левая нога           |\n" +
                "| 4. Правая нога          |\n" +
                "| 5. Прыжок               |\n" +
                "===========================\n"
        );

        System.out.println("Введите код из 5 цифр.");

        System.out.print(" > ");

        String enter = scanner.next();

        GameUtil.sleep(0.1);
        GameUtil.clearConsole();

        for (char sym : enter.toCharArray()) {
            if (!(Character.isDigit(sym)) || !(Character.getNumericValue(sym) > 0 && Character.getNumericValue(sym) < 6)) {
                System.out.println("Недопустимое значение!");
                GameUtil.sleep(1);
                GameUtil.clearConsole();
                first_quest();
                return;
            }
        }

        if (enter.charAt(0) == '5') {
            if (enter.equals("52143") || enter.equals("51234")) {
                System.out.println("Прекрасно, вы выбрались из ямы!");
                GameUtil.sleep(1);
                GameUtil.clearConsole();
                return;
            }

            else {
                System.out.println("Задействуй сначала руки, а потом ноги!");
            }
        }

        else {
            System.out.println("Нее... Тут надо с прыжка!");
        }

        GameUtil.sleep(1);

        System.out.println("Попробуй еще раз!");

        GameUtil.sleep(1);
        GameUtil.clearConsole();
        first_quest();

    }

    private static void screen(List<String> lines) {
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
