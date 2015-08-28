/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world;

import net.c2technology.roguezombie.creature.CreatureFactory;

/**
 *
 * @author cryan
 */
public class WorldBuilder {

    private int width;
    private int height;
    private Tile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public World build() {
        return new World(tiles);
    }

    /**
     * Randomly constructs the world design. This does not add any
     * {@code Creature} or {@code Player}, but sets the boundaries to which the
     * {@code Creature}s and {@code Player} must abide.
     *
     * @return
     */
    public WorldBuilder designWorld() {
        return randomizeTiles().smooth(8);
    }

    private WorldBuilder randomizeTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
            }
        }
        return this;
    }

    private WorldBuilder smooth(int iterations) {
        Tile[][] smootherTiles = new Tile[width][height];
        for (int i = 0; i < iterations; i++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int passableTiles = 0;
                    int impassableTiles = 0;

                    for (int offsetX = -1; offsetX < 2; offsetX++) {
                        for (int offsetY = -1; offsetY < 2; offsetY++) {
                            int smoothX = x + offsetX;
                            int smoothY = y + offsetY;
                            if (smoothX < 0 || smoothX >= width || smoothY < 0 || smoothY >= height) {
                                continue;
                            }
                            if (tiles[smoothX][smoothY] == Tile.FLOOR) {
                                passableTiles++;
                            } else {
                                impassableTiles++;
                            }
                        }
                    }
                    smootherTiles[x][y] = passableTiles >= impassableTiles ? Tile.FLOOR : Tile.WALL;
                }
            }
            tiles = smootherTiles;
        }
        return this;
    }

}
