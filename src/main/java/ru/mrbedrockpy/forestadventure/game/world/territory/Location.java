package ru.mrbedrockpy.forestadventure.game.world.territory;

import ru.mrbedrockpy.forestadventure.game.item.Item;
import ru.mrbedrockpy.forestadventure.game.loottable.LootTable;

import java.util.List;
import java.util.Map;

public enum Location {

    HOUSE(
            "Заброшенный дом",
            List.of(
                    "Кажется, в этом доме уже давно не кто не живет."
            ),
            new LootTable(Map.of(
                    Item.SILVER_KEY, 50,
                    Item.GOLDEN_KEY, 5
            ))
    ),

    TENT(
            "Палатка",
            List.of(
                    "Кажется тут кто-то живет"
            ),
            new LootTable(Map.of(
                    Item.SILVER_KEY, 10
            ))
    );

    private final String name;

    private final List<String> lore;

    private final LootTable lootTable;

    Location(String name, List<String> lore, LootTable lootTable) {
        this.name = name;
        this.lore = lore;
        this.lootTable = lootTable;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public LootTable getLootTable() {
        return lootTable;
    }
}
