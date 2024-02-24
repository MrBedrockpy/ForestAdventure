package ru.mrbedrockpy.mygame.game.item;

public class Item {

    public static final Item SILVER_KEY = new Item(1, "Серебрянный ключ", "Блястяшка");

    public static final Item GOLDEN_KEY = new Item(2, "Золотой ключ", "Ювелирная отмычка");

    private final Integer id;

    private final String name;

    private final String description;

    public Item(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "1x " + getName();
    }
}
