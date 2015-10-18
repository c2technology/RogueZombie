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

import java.awt.Color;
import java.util.UUID;

/**
 * A Fire Sword of ultimate glory and power. The beholder of this sword will
 * grant a bonus to damage.
 *
 * @author Chris
 */
public class Sword extends AbstractItem implements Weapon {

    private final int damage;
    private final int weight;
    private final int penaltyDamage;

    public Sword(String name, Color color, int damage, int weight, int penaltyDamage) {
        super(UUID.randomUUID(), name, 'X', color, true);
        this.damage = damage;
        this.weight = weight;
        this.penaltyDamage = penaltyDamage;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public int getPenaltyDamage() {
        return this.penaltyDamage;
    }

}
