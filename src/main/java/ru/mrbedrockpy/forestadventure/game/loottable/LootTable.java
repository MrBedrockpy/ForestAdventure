package ru.mrbedrockpy.forestadventure.game.loottable;

import ru.mrbedrockpy.forestadventure.game.item.Item;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class LootTable {

    private final Map<Item, Integer> chances;

    public LootTable(Map<Item, Integer> chances) {
        this.chances = chances;
    }

    public List<Item> generateLoot() {

        List<Item> result = new ArrayList<>();

        for (Item item: chances.keySet()) {

            if (new Random().nextInt(100) < chances.get(item)) {
                result.add(item);
            }

        }

        return result;
    }
}
