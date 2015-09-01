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
package net.c2technology.roguezombie.world.cave;

import net.c2technology.roguezombie.world.AbstractWorldBuilder;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;

/**
 * Builds a dungeon based on a cave system. The resulting cave system currently
 * has inaccessible locations that may spawn creatures.
 *
 * @author cryan
 */
public class CaveBuilder extends AbstractWorldBuilder {

    /**
     * Creates the {@code WorldBuilder}.
     *
     * @param width
     * @param height
     */
    public CaveBuilder(int width, int height) {
        super(width, height);
    }

    /**
     * Randomly constructs the world design. This does not add any
     * {@code Creature} or {@code Player}, but sets the boundaries to which the
     * {@code Creature}s and {@code Player} must abide.
     */
    @Override
    public void designWorld() {
        randomizeTiles();
        smoothWalls(8);
    }

    /**
     * Fills the world with random {@link Tile#FLOOR} and {@link Tile#WALL}
     * tiles.
     *
     * @return
     */
    private void randomizeTiles() {
        int width = getWidth();
        int height = getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setTile(Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL, new Coordinate(x, y));
            }
        }
    }

    /**
     * Smooths the randomness into caverns and rocks.
     *
     * @param iterations
     */
    private void smoothWalls(int iterations) {
        //TODO: Optimize!
        int width = getWidth();
        int height = getHeight();
        Tile[][] smootherTiles = new Tile[width][height];
        for (int i = 0; i < iterations; i++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int passableTiles = 0;
                    int impassableTiles = 0;
                    Coordinate currentCoordinate = new Coordinate(x, y);
                    for (int offsetX = -1; offsetX < 2; offsetX++) {
                        for (int offsetY = -1; offsetY < 2; offsetY++) {
                            int smoothX = currentCoordinate.getX() + offsetX;
                            int smoothY = currentCoordinate.getY() + offsetY;
                            Coordinate smootherCoordinate = new Coordinate(smoothX, smoothY);
                            if (!inBounds(smootherCoordinate)) {
                                continue;
                            }
                            if (getTile(smootherCoordinate) == Tile.FLOOR) {
                                passableTiles++;
                            } else {
                                impassableTiles++;
                            }
                        }
                    }
                    setTile(passableTiles >= impassableTiles ? Tile.FLOOR : Tile.WALL, currentCoordinate);
                }
            }
            setTiles(smootherTiles);
        }
    }
}
