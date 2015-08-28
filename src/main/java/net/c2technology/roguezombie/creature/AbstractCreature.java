/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature;

import net.c2technology.roguezombie.creature.ai.Creature;
import net.c2technology.roguezombie.creature.ai.Ai;
import java.awt.Color;
import java.util.UUID;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.World;

/**
 *
 * @author cryan
 */
public abstract class AbstractCreature implements Creature {

    private final UUID id;
    private final Color color;
    private final char glyph;
    private Coordinate coordinate;
    private final World world;
    private final Ai ai;
    private final CreatureType type;

    protected AbstractCreature(World world, char glyph, Color color, Ai ai, CreatureType type) {
        this.id = UUID.randomUUID();
        this.glyph = glyph;
        this.color = color;
        this.world = world;
        this.ai = ai;
        this.type = type;
    }

    @Override
    public CreatureType getCreatureType() {
        return type;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public World getWorld() {
        return world;
    }

    protected final Ai getAi() {
        return ai;
    }

    @Override
    public char getGlyph() {
        return glyph;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public void move(Cardinal cardinal) {
        Coordinate oldCoordinate = getCoordinate();
        Coordinate newCoordinate = new Coordinate(coordinate.getX() + cardinal.getOffsetX(), coordinate.getY() + cardinal.getOffsetY());
        if (getAi().canEnter(this, newCoordinate, getWorld().getTile(newCoordinate))) {
            setCoordinate(newCoordinate);
            getWorld().creatureMoved(this, oldCoordinate);
        } else {
            //We can't enter... is this an enemy encounter?
            Creature otherCreature = getWorld().getCreature(newCoordinate);
            if (otherCreature != null) {
                //attack determines wether or not to actually attack
                attack(otherCreature);
            }

        }
    }

}
