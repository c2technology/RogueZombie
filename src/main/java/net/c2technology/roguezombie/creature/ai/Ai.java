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

/**
 * A template for artifical intelligence.
 *
 * @author cryan
 * @param <T> The body type of the AI
 */
public interface Ai<T> {

    /**
     * Handles the end of the turn for this {@code Ai}.
     */
    public void resolveTurn();

    /**
     * Determines if the {@code targetTile} at the {@code newCoordinate} is able
     * to be entered by this {@code Ai}.
     *
     * @param newCoordinate
     * @param targetTile
     * @return
     */
    public boolean canEnter(Coordinate newCoordinate, Tile targetTile);

    /**
     * Performs checks against the given parameters to determine if the attack
     * is legal. If the attack is, the attack is performed.
     *
     * @param creature
     */
    public void attack(Creature creature);

    /**
     * Determines if this {@code CreatureAI} can see the given coordinate.
     *
     * @param coordinate The target coordinate to see.
     * @return
     */
    public boolean canSee(Coordinate coordinate);

    /**
     * A message to be processed by the {@code Ai}. This is typically an event
     * that has happened nearby.
     *
     * @param message
     */
    public void onNotify(String message);
}
