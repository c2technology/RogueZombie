/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world;

import asciiPanel.AsciiPanel;
import java.awt.Color;

/**
 *
 * @author cryan
 */
public enum Tile implements Entity {

    FLOOR((char) 250, AsciiPanel.yellow),
    WALL((char) 177, AsciiPanel.yellow),
    BOUNDS('x', AsciiPanel.brightBlack);

    private final char glyph;
    private final Color color;

    private Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    @Override
    public char getGlyph() {
        return this.glyph;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean isPassable() {
        return this != Tile.BOUNDS && this != Tile.WALL;
    }
}
