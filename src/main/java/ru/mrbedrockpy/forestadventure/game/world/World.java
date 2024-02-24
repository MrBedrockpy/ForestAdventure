package ru.mrbedrockpy.forestadventure.game.world;

import ru.mrbedrockpy.forestadventure.game.player.Player;
import ru.mrbedrockpy.forestadventure.game.position.Position;
import ru.mrbedrockpy.forestadventure.game.world.territory.Territory;

import java.util.List;

public class World {

    private final int width;
    private final int height;

    private final List<Territory> territories;

    public World(int width, int height, List<Territory> territories) {
        this.width = width;
        this.height = height;
        this.territories = territories;
    }

    public static boolean doGoingNorth(Player player, World world) {
        Position searchPosition = player.getPosition().clone();
        searchPosition.setY(searchPosition.getY() - 1);
        for (Territory territory: world.getTerritories()) {
            if (territory.getPosition().equals(searchPosition)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doGoingEast(Player player, World world) {
        Position searchPosition = player.getPosition().clone();
        searchPosition.setX(searchPosition.getX() + 1);
        for (Territory territory: world.getTerritories()) {
            if (territory.getPosition().equals(searchPosition)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doGoingSouth(Player player, World world) {
        Position searchPosition = player.getPosition().clone();
        searchPosition.setY(searchPosition.getY() + 1);
        for (Territory territory: world.getTerritories()) {
            if (territory.getPosition().equals(searchPosition)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doGoingWest(Player player, World world) {
        Position searchPosition = player.getPosition().clone();
        searchPosition.setX(searchPosition.getX() - 1);
        for (Territory territory: world.getTerritories()) {
            if (territory.getPosition().equals(searchPosition)) {
                return true;
            }
        }
        return false;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
