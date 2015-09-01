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
 * An {@code Entity} is anything that can be placed in a world.
 *
 * @author cryan
 */
public interface Entity {

    /**
     * The color of this {@code Entity}
     *
     * @return
     */
    public Color getColor();

    /**
     * The representation if this {@code Entity}
     *
     * @return
     */
    public char getGlyph();

    /**
     * If this {@code Entity} is passable, it can be moved and seen through.
     *
     * @return {@code true} if passable
     */
    public boolean isPassable();

}
