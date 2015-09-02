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
import net.c2technology.roguezombie.creature.enemy.Zombie;

/**
 * This is the intelligence object for a {@code Zombie}. A {@code Zombie} mill
 * around. If the {@code Zombie} cannot move, the {@code Zombie} will stay in
 * place. The {@code Zombie} has a 1% chance of generating a new {@code Zombie}
 * to simulate a hoarding effect (because there are hidden Zombies everywhere!)
 *
 * @author cryan
 */
public abstract class ZombieAi extends AbstractCreatureAi<Zombie> {

    /**
     * Protected constructor to deter anonymous sub-classing.
     *
     * @param zombie
     */
    protected ZombieAi(Zombie zombie) {
        super(zombie);
    }

    /**
     * When the turn ends a {@code Zombie} generally moves.
     */
    @Override
    public void resolveTurn() {
        move();
    }

    /**
     * Delegates movement to implementing classes.
     */
    protected abstract void move();

    /**
     * In general, a {@code Zombie} will only attack another {@code Creature} if
     * it is not the same type as itself.
     *
     * @param other
     */
    @Override
    public void attack(Creature other) {
        if (other.getCreatureType() != self.getCreatureType()) {
            super.attack(other);
        }
    }
}
