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
package net.c2technology.roguezombie.creature.enemy;

import java.awt.Color;
import net.c2technology.roguezombie.creature.AbstractCreature;
import net.c2technology.roguezombie.creature.CreatureType;
import net.c2technology.roguezombie.creature.Creature;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.World;

/**
 * A simple enemy of the {@code Player}. The {@code Zombie} is merely a shell
 * for intelligence. The {@code ZombieAi} provided to this {@code Zombie} will
 * determine how this {@code Zombie} interacts with the {@code World}.
 *
 * @author cryan
 */
public class Zombie extends AbstractCreature {

    /**
     * Creates a {@code Zombie} object.
     *
     * @param world The {@code World} in which this {@code Zombie} resides.
     * @param glyph The representation of this {@code Zombie}
     * @param color The color of this {@code Zombie}
     */
    public Zombie(World world, char glyph, Color color) {
        super(CreatureType.MOB, glyph, glyph, color, world);
    }

    /**
     * Handles the {@code Zombie} action when attempting to enter the given
     * {@code coordinate}.
     *
     * @param coordinate
     */
    @Override
    public void onEnter(Coordinate coordinate) {
        if (getAi().canEnter(coordinate, getWorld().getTile(coordinate))) {
            Coordinate oldCoordinate = this.getCoordinate();
            this.setCoordinate(coordinate);
            getWorld().creatureMoved(this, oldCoordinate);
        }

    }

    /**
     * Delegates turn resolution to the {@code Ai}.
     */
    @Override
    public void resolveTurn() {
        getAi().resolveTurn();
    }

    /**
     * Delegates attacking to the {@code Ai}.
     *
     * @param other
     */
    @Override
    public void attack(Creature other) {
        getAi().attack(other);
    }

}
