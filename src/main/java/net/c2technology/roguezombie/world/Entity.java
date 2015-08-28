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

    public Color getColor();

    public char getGlyph();

    public boolean isPassable();

}
