/* 
 * Copyright (C) 2015 Chris Ryan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.creature.Creature;
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

    protected final T self;

    /**
     * Locally accessible default constructor. This guarantees an
     * {@code AbstractCreatureAi} cannot be created anonymously.
     *
     * @param me
     */
    protected AbstractCreatureAi(T me) {
        this.self = me;
        this.self.setAi(this);
    }

    /**
     * By default, nothing happens at the end of a turn. Certain
     * {@code Creature}s, such as the {@code Player}, may wish to do things at
     * the end of their turn.
     */
    @Override
    public void resolveTurn() {
    }

    /**
     * Causes this Zombie to infect the {@code Creature}. If the attack
     * succeeds, the {@code Creature} is turned into a Zombie.
     *
     * @param other the {@code Creature} to attack.
     */
    @Override
    public void attack(Creature other) {
        self.getWorld().remove(other);
    }

    /**
     * Determines if the {@code Creature} can enter the given {@code targetTile}
     * at the given {@code coordinate}.
     *
     * @return {@code true} if the {@code Creature} can enter.
     */
    @Override
    public boolean canEnter(Coordinate newCoordinate, Tile targetTile) {
        //provide basic movement
        return (targetTile.isPassable() && self.getWorld().getCreature(newCoordinate) == null);
    }

    /**
     * Determines if the {@code Creature} can see the {@code target}.
     *
     * @param target The target {@code Creature} to attempt to see.
     * @return {@code true} if the {@code target} can be seen.
     */
    @Override
    public boolean canSee(Coordinate target) {
        //TODO: Move this to a HasSight AI
        Coordinate myLocation = self.getCoordinate();
        int horizontalDelta = myLocation.horizontalDelta(target);
        int verticalDelta = myLocation.verticalDelta(target);
        int myVision = self.getVisionRadius();

        //pythagorean theorem
        int a2 = horizontalDelta * horizontalDelta;
        int b2 = verticalDelta * verticalDelta;
        int c2 = myVision * myVision;

        //They are outside of the radius!
        if (a2 + b2 > c2) {
            return false;
        }
        for (Coordinate coordinate : LineOfSight.getLineOfSight(self.getCoordinate(), target)) {
            //Stop when we get to an impassible tile. Note that this will never be a Creature
            if (!self.getWorld().getTile(coordinate).isPassable()) {
                return false;
            }
        }
        //We have reached the target LOS and have not hit an impassible object. Whatever we were looking at, we can see.
        return true;
    }

}
