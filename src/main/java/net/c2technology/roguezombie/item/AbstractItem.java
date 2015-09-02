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
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Chris Ryan
 */
public abstract class AbstractItem implements Item {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractItem other = (AbstractItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    private final UUID id;
    private final String name;
    private final char glyph;
    private final Color color;
    private final boolean passable;

    protected AbstractItem(UUID id, String name, char glyph, Color color, boolean passable) {
        this.id = id;
        this.name = name;
        this.glyph = glyph;
        this.color = color;
        this.passable = passable;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public char getGlyph() {
        return this.glyph;
    }

    @Override
    public boolean isPassable() {
        return this.passable;
    }

}
