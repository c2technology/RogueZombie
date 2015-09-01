/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    private LineOfSight(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

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
                    int y = getY(x0, x1, y0, y1, x);
                    coordinates.add(new Coordinate(x, y));
                }
                break;
            case NORTH_WEST:
            case SOUTH_WEST:
            case WEST:
                //going left
                for (int x = x0 - 1; x >= x1; x--) {
                    int y = getY(x0, x1, y0, y1, x);
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

    private static int getY(int x0, int x1, int y0, int y1, int x) {
        return ((y1 - y0) / (x1 - x0)) * (x - x0) + y0;
    }
}
