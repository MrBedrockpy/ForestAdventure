package ru.mrbedrockpy.mygame.game.world.territory;

import org.jetbrains.annotations.Nullable;
import ru.mrbedrockpy.mygame.game.position.Position;

public class Territory {

    private final TerritoryType type;

    private final Position position;

    private final @Nullable Location location;

    public Territory(TerritoryType type, Position position, @Nullable Location location) {
        this.type = type;
        this.position = position;
        this.location = location;
    }

    public TerritoryType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public @Nullable Location getLocation() {
        return location;
    }

    public enum TerritoryType {

        PLAINS,
        FOREST,
        HILL;

    }

}
