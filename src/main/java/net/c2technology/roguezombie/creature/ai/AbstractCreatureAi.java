/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;

/**
 * Provides basic AI functions, such as moving and determining if the owner is
 * dead.
 *
 * @author Chris
 */
public abstract class AbstractCreatureAi<T extends Creature> implements Ai<T> {

    protected AbstractCreatureAi() {
    }

    @Override
    public void resolveTurn(T me) {
    }

    /**
     * Causes this Zombie to infect the {@code Creature}. If the attack
     * succeeds, the {@code Creature} is turned into a Zombie.
     *
     * @param me
     * @param other the {@code Creature} to attack.
     */
    @Override
    public void attack(T me, Creature other) {
        me.getWorld().remove(other);
    }

    @Override
    public boolean canEnter(T me, Coordinate newCoordinate, Tile targetTile) {
        //provide basic movement
        return (targetTile.isPassable() && me.getWorld().getCreature(newCoordinate) == null);
    }

}
