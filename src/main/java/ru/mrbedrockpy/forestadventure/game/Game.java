package ru.mrbedrockpy.forestadventure.game;

import ru.mrbedrockpy.forestadventure.GameUtil;
import ru.mrbedrockpy.forestadventure.game.input.InventoryInput;
import ru.mrbedrockpy.forestadventure.game.inventory.Inventory;
import ru.mrbedrockpy.forestadventure.game.item.Item;
import ru.mrbedrockpy.forestadventure.game.player.Player;
import ru.mrbedrockpy.forestadventure.game.position.Position;
import ru.mrbedrockpy.forestadventure.game.world.territory.Location;
import ru.mrbedrockpy.forestadventure.game.worldgenerator.WorldGenerator;
import ru.mrbedrockpy.forestadventure.game.world.World;
import ru.mrbedrockpy.forestadventure.game.world.territory.Territory;
import ru.mrbedrockpy.forestadventure.game.input.*;

import java.util.*;

public class Game {

    public static Scanner scanner = new Scanner(System.in);

    public static void start() {

        history();

        first_quest();

        game();

    }

    private static void game() {

        World world = WorldGenerator.generate(20, 20);

        Player player = new Player(new Position(10, 10));

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

            GameUtil.screen(lines);

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

                        GameUtil.screen(lines);

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

        GameUtil.screen(lines);

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

        List<String> lines = new ArrayList<>();

        lines.add(" ");

        lines.add(" Война! Война никогда не меняется. ");
        lines.add(" Война началась снова. Но эта война ");
        lines.add(" стирает все живое на планете. ");
        lines.add(" Единственный способ выжить - уйти ");
        lines.add(" от цивилизации! ");

        lines.add(" ");

        GameUtil.screen(lines);

        System.out.println();

        System.out.println("Введите любой символ");

        System.out.print(" > ");

        scanner.next();

        GameUtil.clearConsole();

        lines.clear();

        lines.add(" ");

        lines.add("  Я был не один такого мнения, ");
        lines.add(" поэтому я присоединился к общине, ");
        lines.add(" которая решила поселится подальше от всех. ");
        lines.add(" По пути до места, упал в яму и ");
        lines.add(" потерял сознание... ");
        lines.add("  Когда я проснулся, не моих вещей, не отряда ");
        lines.add(" не было. Меня обокрали и оставили в яме... ");

        lines.add(" ");

        GameUtil.screen(lines);

        System.out.println(" ");

        System.out.println("Введите любой символ");

        System.out.print(" > ");

        scanner.next();

        GameUtil.clearConsole();

    }

    private static void first_quest() {

        List<String> lines = new ArrayList<>();

        lines.add(" ");

        lines.add(" Для начала нужно выбротся из ямы. ");
        lines.add(" Введите последовательность движений, ");
        lines.add(" для того, чтобы выбротся из ямы. ");

        lines.add(" ");

        lines.add(" 1. Левая рука ");
        lines.add(" 2. Правая рука ");
        lines.add(" 3. Левая нога ");
        lines.add(" 4. Правая нога ");
        lines.add(" 5. Прыжок ");

        lines.add(" ");

        GameUtil.screen(lines);

        System.out.println("Введите код из 5 цифр.");

        System.out.print(" > ");

        String enter = scanner.next();

        GameUtil.sleep(0.1);
        GameUtil.clearConsole();

        for (char sym : enter.toCharArray()) {
            if (!(Character.isDigit(sym)) || !(Character.getNumericValue(sym) > 1 && Character.getNumericValue(sym) < 6)) {
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

}
