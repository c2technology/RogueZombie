/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.creature.CreatureFactory;
import net.c2technology.roguezombie.creature.Zombie;

/**
 * This is the intelligence object for a {@code Zombie}. A {@code Zombie} mill
 * around. If the {@code Zombie} cannot move, the {@code Zombie} will stay in
 * place. The {@code Zombie} has a 1% chance of generating a new {@code Zombie}
 * to simulate a hoarding effect (because there are hidden Zombies everywhere!)
 *
 * @author cryan
 */
public abstract class ZombieAi extends AbstractCreatureAi<Zombie> {

    protected final CreatureFactory factory;

    protected ZombieAi(CreatureFactory factory) {
        this.factory = factory;
    }

    /**
     * When the turn ends, a Zombie tries to move in a random direction. It may
     * or may not attempt to do anything. Zombies also have a 1% chance to
     * replicate in order to simulate other hiding zombies suddenly appearing.
     *
     * @param me The {@code Zombie} using this AI
     */
    @Override
    public void resolveTurn(Zombie me) {
        move(me);
        if (willReplicate()) {
            replicate(me);
        }
    }

    /**
     * Moves the {@code Zombie}. If the {@code Zombie} would move into another
     * creature, the implementation should determine it's action.
     *
     * @param me
     */
    protected abstract void move(Zombie me);

    /**
     * Attempt to spawn a new Zombie. If successful, and the new Zombie can be
     * legally placed, the Zombie enters the World. Otherwise, nothing happens.
     *
     * @param me
     */
    protected abstract void replicate(Creature me);

    /**
     * Determines if replication will occur;
     *
     * @return
     */
    protected abstract boolean willReplicate();

    /**
     * A Zombie will attack anything that is not the same type as itself.
     *
     * @param me
     * @param other
     */
    @Override
    public void attack(Zombie me, Creature other) {
        if (other.getCreatureType() != me.getCreatureType()) {
            me.getWorld().remove(other);
        }
    }
}
