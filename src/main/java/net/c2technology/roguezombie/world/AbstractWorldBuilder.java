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
package net.c2technology.roguezombie.world;

/**
 * This class provides common {@code WorldBuilder} implementations a foundation
 * for developing the {@code World}.
 *
 * @author cryan
 */
public abstract class AbstractWorldBuilder implements WorldBuilder {

    private final int width;
    private final int height;
    private Tile[][] tiles;

    /**
     * Protected constructor to deter anonymous sub-classing.
     *
     * @param width the width of the {@code World} to build
     * @param height the height of the {@code World} to build
     */
    protected AbstractWorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    /**
     * Delegates design of the {@code World} to implementing classes and builds
     * the designed {@code World}.
     *
     * @return
     */
    @Override
    public World build() {
        designWorld();
        return new World(tiles);
    }

    /**
     * Constructs the world design.
     */
    protected abstract void designWorld();

    /**
     * The width of the world to build
     *
     * @return
     */
    public final int getWidth() {
        return this.width;
    }

    /**
     * Sets the given {@code tile} at the given {@code coordinate} if in bounds.
     * This will replace whatever {@code Tile} currently exists.
     *
     * @param tile
     * @param coordinate
     */
    protected final void setTile(Tile tile, Coordinate coordinate) {
        if (inBounds(coordinate)) {
            tiles[coordinate.getX()][coordinate.getY()] = tile;
        }
    }

    /**
     * Gets the {@code Tile} currently at the given {@code coordinate}. If the
     * {@code coordinate} is out of bounds, or no {@code Tile} is found,
     * {@code null} is returned.
     *
     * @param coordinate
     * @return
     */
    protected final Tile getTile(Coordinate coordinate) {
        if (inBounds(coordinate)) {
            return tiles[coordinate.getX()][coordinate.getY()];
        }
        //TODO: Make this throw an exception?
        return null;
    }

    /**
     * Sets the given {@code tiles} to this {@code WorldBuilder} replacing all
     * existing {@code Tile}s
     *
     * @param tiles
     */
    protected final void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * The height of the world to build
     *
     * @return
     */
    public final int getHeight() {
        return this.height;
    }

    /**
     * Helper function to determine if a given {@code coordinate} is in bounds
     * relative to the width and height.
     *
     * @param coordinate
     * @return
     */
    protected final boolean inBounds(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
}
