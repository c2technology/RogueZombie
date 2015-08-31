/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
