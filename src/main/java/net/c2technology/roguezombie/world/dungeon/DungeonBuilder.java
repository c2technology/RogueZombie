/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world.dungeon;

import net.c2technology.roguezombie.world.AbstractWorldBuilder;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;
import net.c2technology.roguezombie.world.World;
import squidpony.squidgrid.mapping.ClassicRogueMapGenerator;
import squidpony.squidgrid.mapping.Terrain;

/**
 * Builds a randomized dungeon based world.
 *
 * @author cryan
 */
public class DungeonBuilder extends AbstractWorldBuilder {

    public DungeonBuilder(int width, int height) {
        super(width, height);
    }

    @Override
    public final World build() {
        //TODO: Implement dungeon building... for now, use this dungeon builder and extract walls and floors
        int horizontalRooms = 10;
        int verticalRooms = 5;
        int minHorizontalRoomSize = 3;
        int maxHorizontalRoomSize = 6;
        int minVerticalRoomSize = 2;
        int maxVerticalRoomSize = 4;

        if (horizontalRooms * maxHorizontalRoomSize > width - 10) {
            throw new IllegalArgumentException("Horizontal Rooms exceed width!");
        }
        if (verticalRooms * maxVerticalRoomSize > height - 10) {
            throw new IllegalArgumentException("Vertical Rooms exceed height!");
        }
        try {
            Terrain[][] terrain = new ClassicRogueMapGenerator(horizontalRooms, verticalRooms, width, height, minHorizontalRoomSize, maxHorizontalRoomSize, minVerticalRoomSize, maxVerticalRoomSize).create();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
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
            e.printStackTrace(System.out);
        }
        return new World(tiles);
    }

}
