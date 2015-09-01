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
package net.c2technology.roguezombie.item;

import java.awt.Color;
import java.util.UUID;

/**
 * This is the only exit for a {@code Player}. Once a {@code Player} reaches
 * this, he will be free from the horror.
 *
 * @author Chris Ryan
 */
public class Helicopter extends AbstractItem {

    public Helicopter() {
        super(UUID.randomUUID(), "Helicopter", 'H', Color.CYAN, true);
    }

}
