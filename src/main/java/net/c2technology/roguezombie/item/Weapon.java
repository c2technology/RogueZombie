/*
 * Copyright (C) 2015 Chris
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
package net.c2technology.roguezombie.item;

/**
 *
 * @author Chris
 */
public interface Weapon extends Item {

    /**
     * The bonus damage this {@link Weapon} grants to its wielder.
     *
     * @return
     */
    public int getDamage();

    /**
     * The weight of this {@link Weapon}
     *
     * @return
     */
    public int getWeight();

    /**
     * Penalty damage for it's use.
     *
     * @return
     */
    public int getPenaltyDamage();
}
