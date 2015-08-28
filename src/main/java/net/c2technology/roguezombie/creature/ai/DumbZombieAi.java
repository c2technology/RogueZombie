/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.creature.CreatureFactory;
import net.c2technology.roguezombie.creature.Zombie;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import squidpony.squidmath.RNG;

/**
 * This is the intelligence object for a dumb zombie. Dumb zombies mill around
 * even when the player is close, without paying any attention to the player.
 * The dumb Zombie has a 1% chance of generating a new dumb Zombie to simulate a
 * hoarding effect (because there are hidden Zombies everywhere!)
 *
 * @author cryan
 */
public class DumbZombieAi extends ZombieAi {

    public DumbZombieAi(CreatureFactory factory) {
        super(factory);
    }

    /**
     * Attempt to move to a random adjacent space. If the Zombie cannot move,
     * the Zombie does not try again.
     *
     * @param me
     */
    @Override
    protected void move(Zombie me) {
        me.move(Cardinal.random());
    }

    /**
     * Attempt to replicate If successful, and the new Zombie can be legally
     * placed, the Zombie enters the World. Otherwise, nothing happens.
     *
     * @param me
     */
    @Override
    protected final void replicate(Creature me) {
        Creature child = factory.makeZombie(me.getWorld());
        Coordinate parentCoordinate = me.getCoordinate();

        //Pick a space next to the original zombie
        Coordinate childCoordinate = new Coordinate(parentCoordinate.getX() + (Math.random() > .5 ? 1 : -1), parentCoordinate.getY() + (Math.random() > .5 ? 1 : -1));
        child.setCoordinate(childCoordinate);

        me.getWorld().spawn(child);

    }

    /**
     * A 1% chance
     *
     * @return
     */
    @Override
    protected boolean willReplicate() {
        return new RNG().between(0, 100) == 49;
    }

}
