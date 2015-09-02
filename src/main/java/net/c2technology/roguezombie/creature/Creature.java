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
package net.c2technology.roguezombie.creature;

import net.c2technology.roguezombie.creature.ai.Ai;
import net.c2technology.roguezombie.item.Item;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Identifiable;
import net.c2technology.roguezombie.world.World;

/**
 * A resident of the game world. Examples include all enemies, non-player
 * characters, and players.
 *
 * @author cryan
 */
public interface Creature extends Identifiable {

    /**
     * Moves the {@code Creature} in the given cardinal direction.
     *
     * @param cardinal
     */
    public void move(Cardinal cardinal);

    /**
     * Returns {@code true} if this {@code Creature} still has health.
     *
     * @return
     */
    public boolean hasHealth();

    /**
     * Resolves this {@code Creature}'s turn
     *
     */
    public void resolveTurn();

    /**
     * Performs any actions when this {@code Creature} enters the given
     * {@code coordinate}
     *
     * @param coordinate
     */
    public void enter(Coordinate coordinate);

    /**
     * The current location of this {@code Creature}
     *
     * @return
     */
    public Coordinate getCoordinate();

    /**
     * Changes the current {@code Ai} of this {@code Creature}.
     *
     * @param ai
     */
    public void setAi(Ai ai);

    /**
     * Changes the current location of the {@code Creature}.
     *
     * @param coordinate
     */
    public void performMove(Coordinate coordinate);

    /**
     * The {@code World} this {@code Creature} belongs in.
     *
     * @return
     */
    public World getWorld();

    /**
     * How far out the {@code Creature} can see.
     *
     * @return
     */
    public int getVisionRadius();

    /**
     * The type of this {@code Creature}
     *
     * @return
     */
    public CreatureType getCreatureType();

    /**
     * Determines weather to attach the given {@code creature} and if it should,
     * performs the attack;
     *
     * @param creature
     */
    public void attack(Creature creature);

    /**
     * The combined damage output of the {@code Creature}.
     *
     * @return
     */
    public int getAttack();

    /**
     * Determines if this {@code Creature} can see the given {@code coordinate}.
     * Factors within the {@code Creature} may affect this and may not be
     * guaranteed to always return the same result.
     *
     * @param coordinate
     * @return
     */
    public boolean look(Coordinate coordinate);

    /**
     * Picks up an {@code Item} at the current {@code Coordinate}.
     */
    public void pickup();

    /**
     * Drops the {@code item} at the current {@code Coordinate}.
     *
     * @param item The {@code Item} to drop.
     */
    public void drop(Item item);

//    public void modifyHp(int amount) {
//        hp += amount;
//    
//        if (hp < 1)
//         world.remove(this);
//    }
    /**
     * The armor value of the {@code Creature}.
     *
     * @return
     */
    public int getArmor();

    /**
     * The current health of this {@code Creature}
     *
     * @return
     */
    public int getHealth();

    /**
     * The maximum health of this {@code Creature}
     *
     * @return
     */
    public int getMaxHealth();

    /**
     * Adjusts the current health of this {@code Creature} by the
     * {@code adjustment} amount.
     *
     * @param adjustment A positive or negative number
     */
    public void modifyHealth(int adjustment);

    /**
     * Generic notification of nearby events to the {@code Creature}
     *
     * @param message The message to send
     */
    public void notify(String message);

}
