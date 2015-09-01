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
 * A directional value with the offset for determining relative positioning.
 *
 * @author cryan
 */
public enum Cardinal {

    NORTH(0, -1),
    NORTH_EAST(1, -1),
    NORTH_WEST(-1, -1),
    SOUTH(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH_WEST(-1, 1),
    EAST(1, 0),
    WEST(-1, 0),
    NONE(0, 0);

    /**
     * Returns a randomly selected direction. The direction is guaranteed to not
     * be {@link Cardinal#NONE}.
     *
     * @return
     */
    public static Cardinal random() {
        return Cardinal.values()[RandomNumber.between(0, Cardinal.values().length - 1)];
    }

    private final int xOffset;
    private final int yOffset;

    /**
     * Enum constructor
     *
     * @param xOffset
     * @param yOffset
     */
    private Cardinal(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * The horizontal offset for moving in this direction
     *
     * @return
     */
    public int getOffsetX() {
        return xOffset;
    }

    /**
     * The vertical offset for moving in this direction
     *
     * @return
     */
    public int getOffsetY() {
        return yOffset;
    }

    /**
     * Determines the direction from the {@code origination} to the the
     * {@code destination}. If they are the same, {@link Cardinal#NONE} is
     * returned.
     *
     * @param origination
     * @param destination
     * @return
     */
    public static Cardinal getDirection(Coordinate origination, Coordinate destination) {
        int xDelta = destination.getX() - origination.getX();
        int yDelta = destination.getY() - origination.getY();
        boolean north = false;
        boolean east = false;
        boolean south = false;
        boolean west = false;

        if (yDelta < 0) {
            north = true;
        }
        if (yDelta > 0) {
            south = true;
        }
        if (xDelta > 0) {
            east = true;
        }
        if (xDelta < 0) {
            west = true;
        }

        if (north && east) {
            return NORTH_EAST;
        } else if (north && west) {
            return NORTH_WEST;
        } else if (south && east) {
            return SOUTH_EAST;
        } else if (south && west) {
            return SOUTH_WEST;
        } else if (north) {
            return NORTH;
        } else if (south) {
            return SOUTH;
        } else if (east) {
            return EAST;
        } else if (west) {
            return WEST;
        }
        return NONE;
    }

}
