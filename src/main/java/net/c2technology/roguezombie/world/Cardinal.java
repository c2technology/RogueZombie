/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world;

import java.util.Arrays;
import squidpony.squidmath.RNG;

/**
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
        return Cardinal.values()[new RNG().between(0, Cardinal.values().length - 1)];
    }

    private final int xOffset;
    private final int yOffset;

    private Cardinal(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getOffsetX() {
        return xOffset;
    }

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
