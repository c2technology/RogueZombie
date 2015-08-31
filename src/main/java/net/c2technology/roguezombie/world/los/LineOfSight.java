/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world.los;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
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
     * Uses Bresenham's line algorithm to determine the tiles within the given
     * line of sight.
     *
     * @param origin The start of line of sight
     * @param destination The target for line of sight. This might not
     * necessarily be the end of the line of sight.
     * @return
     */
    public static LineOfSight getLineOfSight(Coordinate origin, Coordinate destination) {
        List<Coordinate> coordinates = new ArrayList();

        int x0 = origin.getX();
        int y0 = origin.getY();
        int x1 = destination.getX();
        int y1 = destination.getY();

        int deltaX = x1 - x0;
        int deltaY = y1 - y0;

        int delta = 2 * deltaY - deltaX;
        coordinates.add(new Coordinate(x0, y0));
        int y = y0;
        for (int x = x0 + 1; x <= x1; x++) {
            if (delta > 0) {
                y = y + 1;
                coordinates.add(new Coordinate(x, y));
                delta = delta + (2 * deltaY - 2 * deltaX);
            } else {
                coordinates.add(new Coordinate(x, y));
                delta = delta + (2 * deltaY);
            }
        }
        return new LineOfSight(coordinates);
    }

}
