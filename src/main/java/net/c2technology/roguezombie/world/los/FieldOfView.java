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
    private boolean[][] visibleTiles;
    private final int width;
    private final int height;

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
//                    if (!world.isInBounds(point)) {
//                        continue;
//                    }
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
