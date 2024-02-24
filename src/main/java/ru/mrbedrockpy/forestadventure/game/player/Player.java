package ru.mrbedrockpy.forestadventure.game.player;

import ru.mrbedrockpy.forestadventure.game.inventory.Inventory;
import ru.mrbedrockpy.forestadventure.game.position.Position;

import java.util.ArrayList;

public class Player {

    private final Inventory inventory = new Inventory(new ArrayList<>(), null);

    private final Position position;

    public Player(Position position) {
        this.position = position;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Position getPosition() {
        return position;
    }
}
