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

import net.c2technology.roguezombie.creature.enemy.Zombie;
import net.c2technology.roguezombie.world.Cardinal;

/**
 * This is the intelligence object for a dumb zombie. Dumb zombies mill around
 * even when the player is close, without paying any attention to the player.
 *
 * @author cryan
 */
public class DumbZombieAi extends ZombieAi {

    /**
     * A {@code DumbZombieAi} only allows for random movement in addition to the
     * logic provided by the {@link ZombieAi}.
     *
     * @param zombie The {@code Zombie} to control.
     */
    public DumbZombieAi(Zombie zombie) {
        super(zombie);
    }

    /**
     * Attempt to move to a random adjacent space. If the Zombie cannot move,
     * the Zombie does not try again and remains in the original space.
     */
    @Override
    protected void move() {
        self.move(Cardinal.random());
    }

}
