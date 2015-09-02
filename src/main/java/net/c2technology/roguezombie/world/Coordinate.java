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
 * A single location in a dimensional plane.
 *
 * @author cryan
 */
public class Coordinate {

    private final int x;
    private final int y;

    /**
     * Creates a new location that can be mapped.
     *
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A point on the horizontal axis.
     *
     * @return
     */
    public final int getX() {
        return x;
    }

    /**
     * A point on the vertical axis.
     *
     * @return
     */
    public final int getY() {
        return y;
    }

    /**
     * The {@code String} representation of this {@code Coordinate}
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("X: %s, Y: %s", x, y);
    }

    /**
     * The horizontal delta of this {@code Coordinate} when compared to
     * {@code coordinate}.
     *
     * @param coordinate
     * @return
     */
    public final int horizontalDelta(Coordinate coordinate) {
        return coordinate.x - this.x;
    }

    /**
     * The vertical delta of this {@code Coordinate} when compared to
     * {@code coordinate}.
     *
     * @param coordinate
     * @return
     */
    public final int verticalDelta(Coordinate coordinate) {
        return coordinate.y - this.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
