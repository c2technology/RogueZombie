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

import java.awt.Color;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.World;

/**
 * The {@code Player}, while also a {@code Creature}, is the only
 * {@code Creature} that is controlled by a human player.
 *
 * <pre>Our hero! &lt;3</pre>
 *
 * @author cryan
 */
public class Player extends AbstractCreature {

    //TODO: Add Inventory
    //TODO: Implement changeable AI for added effects (poison, confused, etc)..
    /**
     * Constructs a {@code Player} object. The {@code PlayerAi} provides the
     * mechanics of how the {@code Player} may interact with the {@code World}
     * based in the current {@code Player} state.
     *
     * @param world The {@code World} in which this {@code Player} lives
     * @param glyph The representation of this {@code Player}
     * @param color The color if this {@code Player}
     * @param inventory The starting {@code Inventory} of this {@code Player}
     * @param health The max starting health of this {@code Player}
     * @param baseAttack The base attack value for this {@code Player}
     * @param baseDefense the base defense value of this {@code Player}
     * @param visionRadius The range of vision of this {@code Player}
     *
     */
    public Player(World world, char glyph, Color color, Inventory inventory, int health, int baseAttack, int baseDefense, int visionRadius) {
        super("Hero", CreatureType.PLAYER, visionRadius, glyph, color, inventory, health, baseAttack, baseDefense, world);
    }

    /**
     * Handles this {@code Player} entering the given {@code coordinate}
     *
     * @param coordinate
     */
    @Override
    public void enter(Coordinate coordinate) {
        getAi().canEnter(coordinate, getWorld().getTile(coordinate));
    }

    /**
     * Resolves the turn of this {@code Player}.
     */
    @Override
    public void resolveTurn() {
        //TODO: Check if they are dead
        //TODO: ???
    }

}
