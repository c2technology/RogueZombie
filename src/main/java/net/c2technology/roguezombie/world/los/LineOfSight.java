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

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;

/**
 * The {@code LineOfSight} is an area the character can "see." This area is
 * determined by using Bresenham's line algorithm.
 *
 * @author cryan
 */
public class LineOfSight implements Iterable<Coordinate> {

    private final List<Coordinate> coordinates;

    /**
     * A single line of sight based on the given {@code coordinates}.
     *
     * @param coordinates
     */
    private LineOfSight(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * The {@code Iterator} for the {@code Coordinate} locations within the
     * {@code LineOfSight}
     *
     * @return
     */
    @Override
    public Iterator<Coordinate> iterator() {
        return this.coordinates.iterator();
    }

    /**
     * Uses a modified Bresenham's line algorithm to determine the tiles within
     * the given line of sight. The modification is due to the origin point of
     * the grid being the top-left most coordinate (0,0). All coordinate points
     * are positive.
     *
     * @param origin The start of line of sight
     * @param destination The target for line of sight. This might not
     * necessarily be the end of the line of sight.
     * @return
     */
    public static LineOfSight getLineOfSight(Coordinate origin, Coordinate destination) {

        //y = ((y1-y0)/(x1-x0))(x-x0)+y0
        List<Coordinate> coordinates = new ArrayList();
        int x0 = origin.getX();
        int y0 = origin.getY();
        int x1 = destination.getX();
        int y1 = destination.getY();
        Cardinal direction = Cardinal.getDirection(origin, destination);
        coordinates.add(new Coordinate(x0, y0));

        //Determine the line of sight based on orthagonal cardinal direction
        switch (direction) {
            case NORTH_EAST:
            case SOUTH_EAST:
            case EAST:
                for (int x = x0 + 1; x <= x1; x++) {
                    int y = getSlope(x0, x1, y0, y1, x);
                    coordinates.add(new Coordinate(x, y));
                }
                break;
            case NORTH_WEST:
            case SOUTH_WEST:
            case WEST:
                //going left
                for (int x = x0 - 1; x >= x1; x--) {
                    int y = getSlope(x0, x1, y0, y1, x);
                    coordinates.add(new Coordinate(x, y));
                }
                break;
            case NORTH:
                //going up
                for (int y = y0 - 1; y >= y1; y--) {
                    coordinates.add(new Coordinate(x0, y));
                }
                break;
            case SOUTH:
                //going down
                for (int y = y0 + 1; y <= y1; y++) {
                    coordinates.add(new Coordinate(x0, y));
                }
                break;
            default:
                //NONE or UNKNOWN
                break;
        }
        return new LineOfSight(coordinates);
    }

    /**
     * Performs the Pythagorean Theorem to determine if the {@code target} is
     * within the given {@code radius} of the {@code origin} hypotenuse (c) is
     * greater than
     *
     * @param origin
     * @param target
     * @param radius
     * @return
     */
    public static boolean insideRadius(Coordinate origin, Coordinate target, int radius) {

        int a = origin.horizontalDelta(target);
        int b = origin.verticalDelta(target);
        int a2 = a * a;
        int b2 = b * b;
        int c2 = radius * radius;
        return a2 + b2 <= c2;
    }

    /**
     * Calculates the slope based on Bresenham's line algorithm
     *
     * @param x0
     * @param x1
     * @param y0
     * @param y1
     * @param x
     * @return
     * @see https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
     */
    private static int getSlope(int x0, int x1, int y0, int y1, int x) {
        return ((y1 - y0) / (x1 - x0)) * (x - x0) + y0;
    }
}
