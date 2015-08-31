/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;
import net.c2technology.roguezombie.world.los.LineOfSight;

/**
 * Provides basic AI functions, such as moving and determining if the owner is
 * dead.
 *
 * @author Chris
 * @param <T>
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

    @Override
    public boolean canSee(T me, Coordinate target) {
        //TODO: Move this to a HasSight AI
        Coordinate myLocation = me.getCoordinate();
        int horizontalDelta = myLocation.horizontalDelta(target);
        int verticalDelta = myLocation.verticalDelta(target);
        int myVision = me.getVisionRadius();

        //pythagorean theorem
        int a2 = horizontalDelta * horizontalDelta;
        int b2 = verticalDelta * verticalDelta;
        int c2 = myVision * myVision;

        //They are outside of the radius!
        if (a2 + b2 > c2) {
            return false;
        }
        for (Coordinate coordinate : LineOfSight.getLineOfSight(me.getCoordinate(), target)) {
            //Stop when we get to an impassible tile. Note that this will never be a Creature
            if (!me.getWorld().getTile(coordinate).isPassable()) {
                return false;
            }
        }
        //We have reached the target LOS and have not hit an impassible object. Whatever we were looking at, we can see.
        return true;
    }

}
