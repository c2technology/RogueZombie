/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world;

/**
 *
 * @author cryan
 */
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("X: %s, Y: %s", x, y);
    }

    public int horizontalDelta(Coordinate coordinate) {
        return coordinate.x - this.x;
    }

    public int verticalDelta(Coordinate coordinate) {
        return coordinate.y - this.y;
    }

}
