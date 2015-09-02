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
package net.c2technology.roguezombie.creature;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.c2technology.roguezombie.item.Item;

/**
 * This {@code Inventory} is a basic implementation.
 *
 * @author Chris
 */
public class BasicInventory implements Inventory {

    private Map<UUID, Item> items;
    private final int capacity;

    public BasicInventory(int capacity) {
        this.capacity = capacity;
        this.items = new HashMap(this.capacity);
    }

    @Override
    public void add(Item item) {
        if (!items.containsKey(item.getId())) {
            items.put(item.getId(), item);
        }
    }

    @Override
    public void remove(Item item) {
        UUID id = item.getId();
        if (items.containsKey(id)) {
            items.remove(id);
        }
    }

    @Override
    public boolean isFull() {
        return items.size() == capacity;
    }

}
