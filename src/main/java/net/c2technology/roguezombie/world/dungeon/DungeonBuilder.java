/* 
 * Copyright (C) 2015 Chris Ryan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.c2technology.roguezombie.world.dungeon;

import net.c2technology.roguezombie.world.AbstractWorldBuilder;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;
import squidpony.squidgrid.mapping.ClassicRogueMapGenerator;
import squidpony.squidgrid.mapping.Terrain;

/**
 * Builds a randomized dungeon based world.
 *
 * @author cryan
 */
public class DungeonBuilder extends AbstractWorldBuilder {

    /**
     * The maximum attempts at building this world before failing
     */
    private static final int MAX_ATTEMPTS = 3;
    private int attempt = 1;

    public DungeonBuilder(int width, int height) {
        super(width, height);
    }

    /**
     * Designs the {@code World} based on the SquidLib
     * {@link ClassicRogueMapGenerator} then converts the designed world to this
     * game's world type.
     */
    @Override
    protected final void designWorld() {
        //TODO: Implement dungeon building... for now, use this dungeon builder and extract walls and floors
        int horizontalRooms = 10;
        int verticalRooms = 5;
        int minHorizontalRoomSize = 3;
        int maxHorizontalRoomSize = 7;
        int minVerticalRoomSize = 1;
        int maxVerticalRoomSize = 2;
        int width = getWidth();
        int height = getHeight();
        if (horizontalRooms * maxHorizontalRoomSize > width - 20) {
            throw new IllegalArgumentException("Horizontal Rooms exceed width!");
        }
        if (verticalRooms * maxVerticalRoomSize > height - 20) {
            throw new IllegalArgumentException("Vertical Rooms exceed height!");
        }
        try {
            Terrain[][] terrain = new ClassicRogueMapGenerator(horizontalRooms, verticalRooms, width, height, minHorizontalRoomSize, maxHorizontalRoomSize, minVerticalRoomSize, maxVerticalRoomSize).create();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    //Wall this place in!
                    if (x == 0 || y == 0 || x == width || y == height) {
                        setTile(Tile.WALL, new Coordinate(x, y));
                        continue;
                    }
                    Terrain tile = terrain[x][y];
                    switch (tile.symbol()) {
                        case '#':
                            setTile(Tile.WALL, new Coordinate(x, y));
                            break;
                        case '.':
                            setTile(Tile.FLOOR, new Coordinate(x, y));
                            break;
                        default:
                            setTile(Tile.FLOOR, new Coordinate(x, y));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            if (attempt <= MAX_ATTEMPTS) {
                attempt++;
                designWorld();
            }
            throw new RuntimeException("Failed to build world: " + e.getMessage(), e);
        }
    }

}
