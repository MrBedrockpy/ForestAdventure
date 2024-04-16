package ru.mrbedrockpy.forestadventure.saves;

import ru.mrbedrockpy.forestadventure.game.player.Player;
import ru.mrbedrockpy.forestadventure.game.world.World;

public class GameData {

    private final Player player;

    private final World world;

    public GameData(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }
}
