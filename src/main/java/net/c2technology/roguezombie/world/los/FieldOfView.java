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
package net.c2technology.roguezombie.world.los;

import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;
import net.c2technology.roguezombie.world.World;

/**
 * The FieldOfView is the memory of what a {@code Creature} has seen. The state
 * of the {@code Tile} is remembered as it once was and is not necessarily the
 * same upon returning. This is essentially a mapping mechanism for the
 * consumer.
 *
 * @author cryan
 */
public class FieldOfView {

    private final World world;
    private final Tile[][] rememberedTiles;
    private boolean[][] visibleTiles;
    private final int width;
    private final int height;

    /**
     * Creates the field of view for the given {@code World}. The view is
     * initialized with all {@code Tile}s as {@link Tile#UNDISCOVERED}.
     *
     * @param world
     */
    public FieldOfView(World world) {
        this.world = world;
        width = world.getWidth();
        height = world.getHeight();
        this.visibleTiles = new boolean[width][height];
        this.rememberedTiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                visibleTiles[x][y] = false;
                rememberedTiles[x][y] = Tile.UNDISCOVERED;
            }
        }
    }

    /**
     * Returns {@code true} if the {@code target} is inside the {@link World}
     * and is visible.
     *
     * @param target
     * @return
     */
    public boolean isVisible(Coordinate target) {
        return world.isInBounds(target) && visibleTiles[target.getX()][target.getY()];
    }

    /**
     * Updates this {@code FieldOfView} to show all tiles within the given
     * {@code radius} around the {@code origin}. This is done via basic
     * raycasting
     *
     * @param origin
     * @param radius
     */
    public void update(Coordinate origin, int radius) {
        //Reset what is visible...
        visibleTiles = new boolean[width][height];
        int originX = origin.getX();
        int originY = origin.getY();
        int minX = originX - radius;
        int maxX = originX + radius;
        int minY = originY - radius;
        int maxY = originY + radius;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Coordinate target = new Coordinate(x, y);
                if (!world.isInBounds(target)) {
                    continue;
                }
                if (!LineOfSight.insideRadius(origin, target, radius)) {
                    visibleTiles[target.getX()][target.getY()] = false;
                    continue;
                }
                LineOfSight los = LineOfSight.getLineOfSight(origin, target);
                for (Coordinate point : los) {
                    Tile tile = world.getTile(point);
                    int pointX = point.getX();
                    int pointY = point.getY();
                    visibleTiles[pointX][pointY] = true;
                    rememberedTiles[pointX][pointY] = tile;
                    if (!world.getTile(point).isPassable()) {
                        break;
                    }

                }
            }
        }
    }

    /**
     * Retrieves the last remembered tile at the given Coordinate. If no tile is
     * remembered, an {@link Tile#UNDISCOVERED} tile is returned.
     *
     * @param coordinate
     * @return
     */
    public Tile getRememberedTile(Coordinate coordinate) {
        return rememberedTiles[coordinate.getX()][coordinate.getY()];
    }

}
