/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world.los;

import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;
import net.c2technology.roguezombie.world.World;

/**
 * The FieldOfView is the memory of what a {@code Creature} has seen. The state
 * of the {@code Tile} is remembered as it once was and is not necessarily the
 * same upon returning.
 *
 * @author cryan
 */
public class FieldOfView {

    private final World world;
    private final Tile[][] rememberedTiles;
    private final boolean[][] visibleTiles;

    public FieldOfView(World world) {
        this.world = world;
        int width = world.getWidth();
        int height = world.getHeight();
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
        int minX = origin.getX() - radius;
        int maxX = origin.getX() + radius;
        int minY = origin.getY() - radius;
        int maxY = origin.getY() + radius;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (!insideRadius(x, y, radius)) {
                    Coordinate target = new Coordinate(x, y);
                    LineOfSight los = LineOfSight.getLineOfSight(origin, target);
                    for (Coordinate point : los) {
                        Tile tile = world.getTile(point);
                        if (world.getTile(point).isPassable() && world.isInBounds(target)) {
                            visibleTiles[x][y] = true;
                            rememberedTiles[x][y] = tile;
                        } else {
                            //we hit something we can't see through
                            break;
                        }
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

    /**
     * Performs the Pythagorean Theorem to determine if the square of the
     * hypotenuse (c) is greater than
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    private boolean insideRadius(int a, int b, int c) {
        int a2 = a * a;
        int b2 = b * b;
        int c2 = c * c;
        return a2 + b2 <= c2;
    }
}
