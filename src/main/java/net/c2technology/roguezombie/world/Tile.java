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

    FLOOR((char) 250, AsciiPanel.yellow, true),
    WALL((char) 177, AsciiPanel.yellow, false),
    UNDISCOVERED(' ', AsciiPanel.black, true),
    BOUNDS('x', AsciiPanel.brightBlack, false);

    private final char glyph;
    private final Color color;
    private final boolean passable;

    private Tile(char glyph, Color color, boolean passable) {
        this.glyph = glyph;
        this.color = color;
        this.passable = passable;
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
        return passable;
    }
}
