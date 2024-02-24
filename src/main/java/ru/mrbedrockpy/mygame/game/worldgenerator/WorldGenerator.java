package ru.mrbedrockpy.mygame.game.worldgenerator;

import org.jetbrains.annotations.Nullable;
import ru.mrbedrockpy.mygame.game.world.World;
import ru.mrbedrockpy.mygame.game.world.territory.Location;
import ru.mrbedrockpy.mygame.game.position.Position;
import ru.mrbedrockpy.mygame.game.world.territory.Territory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenerator {

    public static NoiseGenerator heightGenerator = new NoiseGenerator();
    public static NoiseGenerator forestGenerator = new NoiseGenerator();

    public static World generate(int width, int height) {

        List<Territory> territories = new ArrayList<>();

        for (int x = 0; x < width; x++) {

            for (int y = 0; y < height; y++) {

                double position_height = heightGenerator.noise(x, y);

                if (position_height > 20) {
                    territories.add(
                            new Territory(Territory.TerritoryType.HILL,
                                    new Position(x, y),
                                    getLocation()
                            )
                    );
                }

                else {

                    if (forestGenerator.noise(x, y) >= 25) {
                        territories.add(
                                new Territory(Territory.TerritoryType.FOREST,
                                        new Position(x, y),
                                        getLocation()
                                )
                        );
                    }

                    else {
                        territories.add(
                                new Territory(Territory.TerritoryType.PLAINS,
                                        new Position(x, y),
                                        getLocation()
                                )
                        );
                    }
                }

            }

        }

        return new World(width, height, territories);
    }

    private static @Nullable Location getLocation() {

        Random random = new Random();

        if (random.nextInt(20) == 0) {
            return Location.values()[random.nextInt(Location.values().length)];
        } else {
            return null;
        }

    }

}
