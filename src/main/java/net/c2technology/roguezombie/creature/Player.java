/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature;

import java.awt.Color;
import net.c2technology.roguezombie.creature.ai.Creature;
import net.c2technology.roguezombie.creature.ai.PlayerAi;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.World;

/**
 *
 * @author cryan
 */
public class Player extends AbstractCreature {

    //TODO: Implement changeable AI for added effects (poison, confused, etc)..
    public Player(World world, char glyph, Color color, PlayerAi ai) {
        super(world, glyph, color, ai, CreatureType.PLAYER);
    }

    /**
     * Causes this {@code creture} to attack.
     *
     * @param other the {@code Creature} to attack.
     */
    @Override
    public void attack(Creature other) {
        getAi().attack(this, other);
    }

    @Override
    public void onEnter(Coordinate coordinate) {
        getAi().canEnter(this, coordinate, getWorld().getTile(coordinate));
    }

    @Override
    public void resolveTurn() {
        //TODO: Check if they are dead
        //TODO: ???
    }
}
