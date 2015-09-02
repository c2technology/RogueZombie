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

import java.awt.Color;

/**
 * A location on the {@code World} map.
 *
 * @author cryan
 */
public enum Tile implements Entity {

    FLOOR((char) 250, Color.WHITE, true),
    EXIT('H', Color.CYAN, true),
    WALL((char) 177, Color.WHITE, false),
    UNDISCOVERED(' ', Color.BLACK, true),
    BOUNDS('#', Color.BLACK, false);

    private final char glyph;
    private final Color color;
    private final boolean passable;

    /**
     * Enum constructor.
     *
     * @param glyph
     * @param color
     * @param passable
     */
    private Tile(char glyph, Color color, boolean passable) {
        this.glyph = glyph;
        this.color = color;
        this.passable = passable;
    }

    /**
     * The representation of the {@code Tile}
     *
     * @return
     */
    @Override
    public char getGlyph() {
        return this.glyph;
    }

    /**
     * The color of the {@code Tile}
     *
     * @return
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Whether or not this {@code Tile} can be moved through.
     *
     * @return {@code true} if this {@code Tile} can be moved through
     */
    @Override
    public boolean isPassable() {
        return passable;
    }
}
