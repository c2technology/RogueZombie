/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature;

import java.awt.Color;
import net.c2technology.roguezombie.creature.ai.Creature;
import net.c2technology.roguezombie.creature.ai.ZombieAi;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.World;

/**
 *
 * @author cryan
 */
public class Zombie extends AbstractCreature {

    public Zombie(World world, char glyph, Color color, ZombieAi ai) {
        super(world, glyph, color, ai, CreatureType.MOB);
    }

    @Override
    public void onEnter(Coordinate coordinate) {
        if (getAi().canEnter(this, coordinate, getWorld().getTile(coordinate))) {
            Coordinate oldCoordinate = this.getCoordinate();
            this.setCoordinate(coordinate);
            getWorld().creatureMoved(this, oldCoordinate);
        }

    }

    /**
     * When the turn ends, a Zombie searches for food. If there is food
     * available (the player), the zombie increases speed towards the player. If
     * there is no food, the zombie takes a step in a random direction.
     *
     * Zombies also have a small change to multiply in order to simulate
     * infection and other hiding zombies.
     *
     */
    @Override
    public void resolveTurn() {
        getAi().resolveTurn(this);
    }

    @Override
    public void attack(Creature other) {
        getAi().attack(this, other);
    }
}
