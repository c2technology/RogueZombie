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
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;

/**
 * This is the intelligence object for a smart {@code Zombie}. The smart
 * {@code Zombie} builds upon the intelligence of the dumb {@code Zombie} and
 * will mill around until the {@code Player} is close. When the {@code Player}
 * is close, the smart {@code Zombie} shambles towards the {@code Player}. If
 * the {@code Player} is not close, the smart {@code Zombie} will either take a
 * random and valid step or stand still if it fails at taking a step.
 *
 * @author cryan
 */
public class SmartZombieAi extends DumbZombieAi {

    public SmartZombieAi(Zombie zombie) {
        super(zombie);
    }

    /**
     * Determine if the Zombie is near the Player and move towards the player.
     * If the Zombie is not near a player, attempt to move to a random space. If
     * the Zombie cannot move to the random space, the Zombie does not move.
     */
    @Override
    protected void move() {
        if (senseHumans(self)) {
            Coordinate playerCoordinate = self.getWorld().getPlayer().getCoordinate();
            Coordinate myCoordinate = self.getCoordinate();
            self.move(Cardinal.getDirection(myCoordinate, playerCoordinate));
        } else {
            super.move();
        }
    }

    /**
     * Determines if the Zombie can sense any humans around.
     *
     * @param me
     * @return
     */
    private boolean senseHumans(Zombie me) {
        Creature player = me.getWorld().getPlayer();

        boolean xAxis = Math.abs(me.getCoordinate().getX() - player.getCoordinate().getX()) <= 5;
        boolean yAxis = Math.abs(me.getCoordinate().getY() - player.getCoordinate().getY()) <= 5;

        return xAxis && yAxis;
    }

}
