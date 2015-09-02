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
package net.c2technology.roguezombie.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.c2technology.roguezombie.creature.Creature;
import net.c2technology.roguezombie.creature.Player;
import net.c2technology.roguezombie.item.Item;

/**
 * A {@code World} handles all state manipulation of it's denizens and utilizes
 * all mechanics within.
 *
 * @author cryan
 */
public class World {

    private final Tile[][] tiles;
    private final UUID[][] itemLocator;
    private final int width;
    private final int height;
    private UUID[][] creatureLocator;
    private final Map<UUID, Creature> creatureRegistry;
    private final Map<UUID, Item> itemRegistry;
    private Player player;

    /**
     * Creates a new {@code World} with the given {@code tiles} as the terrain.
     *
     * @param tiles
     */
    public World(Tile[][] tiles) {

        this.tiles = tiles;
        this.width = tiles.length;
        if (tiles.length > 0) {
            this.height = tiles[0].length;
        } else {
            this.height = 0;
        }
        this.itemLocator = new UUID[width][height];
        this.creatureLocator = new UUID[width][height];
        this.creatureRegistry = new HashMap();
        this.itemRegistry = new HashMap();
    }

    /**
     * Returns a {@code Creature} if one exists at the given {@code coordinate}.
     * If no {@code Creature} exists, {@code null} is returned.
     *
     * @param coordinate
     * @return
     */
    public Creature getCreature(Coordinate coordinate) {
        if (isInBounds(coordinate)) {
            UUID creatureId = creatureLocator[coordinate.getX()][coordinate.getY()];
            //Is there a creature at this location?
            if (creatureId != null) {
                if (creatureRegistry.containsKey(creatureId)) {
                    return creatureRegistry.get(creatureId);
                } else {
                    System.out.println("Creature located but not in registry!");
                }
            }
            if (player != null) {
                if (player.getCoordinate().equals(coordinate)) {
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves an {@code Item} from the {@code World} if one exists at the
     * given {@code coordinate}. If no {@code Item} exists, {@code null} is
     * returned.
     *
     * @param coordinate
     * @return
     */
    private Item getItem(Coordinate coordinate) {
        //TODO: A more generic registry object can be made to handle the various types of entity types (item, creature, etc).
        if (isInBounds(coordinate)) {
            UUID itemId = itemLocator[coordinate.getX()][coordinate.getY()];
            //Is there a creature at this location?
            if (itemId != null && itemRegistry.containsKey(itemId)) {
                return itemRegistry.get(itemId);
            }
        }
        return null;
    }

    /**
     * The overall width of the {@code World}
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * The overall height of the {@code World}
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves a {@code Tile} at the given {@code coordinate}. If the
     * coordinate is out of bounds, a {@link Tile#BOUNDS} {@code Tile} is
     * returned.
     *
     * @param coordinate
     * @return
     */
    public final Tile getTile(Coordinate coordinate) {
        if (!isInBounds(coordinate)) {
            return Tile.BOUNDS;
        }
        return tiles[coordinate.getX()][coordinate.getY()];
    }

    /**
     * Retrieves the current {@code Tile} glyph at the given {@code coordinate}.
     *
     * @param coordinate
     * @return
     */
    public char getGlyph(Coordinate coordinate) {
        Creature creature = getCreature(coordinate);
        if (creature != null) {
            return creature.getGlyph();
        }
        return getTile(coordinate).getGlyph();
    }

    /**
     * Returns an {@code Entity}, whatever it is, at the given
     * {@code coordinate}.
     *
     * @param coordinate
     * @return
     */
    public Entity getEntity(Coordinate coordinate) {
        //TODO: Will have to add additional logic in here if we go past Creature, Item, and Tile...
        Entity entity = getCreature(coordinate);
        if (entity != null) {
            return entity;
        }
        entity = getItem(coordinate);
        if (entity != null) {
            return entity;
        }
        return getTile(coordinate);
    }

    /**
     * Ends the current turn and handles all end turn actions for the
     * {@code World}
     */
    public void endTurn() {
        creatureLocator = new UUID[width][height];
        List<UUID> deadCreatures = new ArrayList();
        for (Creature creature : this.getCreatures()) {
            //prune the dead things... well things that have no health...
            if (creature.hasHealth()) {
                creature.resolveTurn();
                Coordinate newLocation = creature.getCoordinate();
                creatureLocator[newLocation.getX()][newLocation.getY()] = creature.getId();
            } else {
                deadCreatures.add(creature.getId());
            }
        }
        for (UUID id : deadCreatures) {
            creatureRegistry.remove(id);
        }
    }

    /**
     * Returns the {@code Color} of the coordinate. This {@code Color} could
     * belong to a {@code Tile} or a {@code Creature}
     *
     * @param coordinate
     * @return
     */
    public Color getColor(Coordinate coordinate) {
        return getTile(coordinate).getColor();
    }

    /**
     * Gets a random location that is spawnable.
     *
     * @return
     */
    public final Coordinate getRandomSpawnableLocation() {

        Coordinate coordinate;
        //TODO: This logic should be determined by something else. Otherwise, we will need to add additional checks for unknown types (water is impassible without a boat [or bridge, but only for 1 block] .. etc).
        do {
            int x = (int) (Math.random() * width);//90
            int y = (int) (Math.random() * height);//31
            coordinate = new Coordinate(x, y);
        } while (!getTile(coordinate).isPassable() && getCreature(coordinate) == null);

        return coordinate;

    }

    /**
     * Gets a spawnable location near the given coordinate. If the given
     * coordinate it, itself, spawnable, the coordinate will be returned.
     *
     * @param coordinate
     * @return
     * @throws net.c2technology.roguezombie.world.UnspawnableException
     */
    public final Coordinate getSpawnableLocation(Coordinate coordinate) throws UnspawnableException {
        if (getEntity(coordinate).isPassable()) {
            return coordinate;
        }

        for (Cardinal cardinal : Cardinal.values()) {
            //Don't look at None.
            if (cardinal.equals(Cardinal.NONE)) {
                continue;
            }
            int x = coordinate.getX() + cardinal.getOffsetX();
            int y = coordinate.getY() + cardinal.getOffsetY();
            Coordinate coord = new Coordinate(x, y);
            if (getEntity(coord).isPassable()) {
                return coord;
            }
        }
        throw new UnspawnableException("Could not spawn at or around " + coordinate.toString());
    }

    /**
     * Moves a {@code Creature} or {@code Player} within the world in the given
     * {@code direction}.
     *
     * @param creature
     * @param direction
     */
    public void move(Creature creature, Cardinal direction) {
        creature.move(direction);
    }

    /**
     * Spawns the given {@code Creature} in a random location. If it does exist,
     * an additional {@code Creature} is not created but its location is
     * updated.
     *
     * @param creature
     * @return
     */
    public boolean spawnCreature(Creature creature) {
        //TODO: Make a spawn method that spawns within a given range of a given coordinate
        boolean added = false;
        Coordinate creatureLocation = getRandomSpawnableLocation();
        if (isInBounds(creatureLocation)) {
            creature.performMove(creatureLocation);
            creatureRegistry.put(creature.getId(), creature);
            creatureLocator[creatureLocation.getX()][creatureLocation.getY()] = creature.getId();
            added = true;
        }
        return added;
    }

    /**
     * Adds the {@code Player} if it does not exist. If it does exist, it is
     * updated.
     *
     * @param player
     */
    public void addPlayer(Player player) {
        this.player = player;
    }

    /**
     * All {@code Creatures} in this {@code World}, excluding the {@code Player}
     *
     * @return
     */
    public Collection<Creature> getCreatures() {
        return this.creatureRegistry.values();
    }

    /**
     * Determines if the given {@code target} is located within the
     * {@code World}
     *
     * @param target
     * @return
     */
    public boolean isInBounds(Coordinate target) {
        int x = target.getX();
        int y = target.getY();
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    /**
     * Sets the {@code Player} in this {@code World}
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Retrieves the current {@code Player} in this {@code World]
     * @return
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Adds an item to this {@code World} at a randomly selected location.
     * Returns {@code true} if successful.
     *
     * @param item
     * @return
     */
    public boolean addItem(Item item) {
        return addItem(item, getRandomSpawnableLocation());
    }

    /**
     * Adds an item to this {@code World}. Returns {@code true} if successful.
     *
     * @param item
     * @param coordinate
     * @return
     */
    public boolean addItem(Item item, Coordinate coordinate) {
        //TODO: Make a spawn method that spawns within a given range of a given coordinate
        boolean added = false;

        if (isInBounds(coordinate)) {
            itemRegistry.put(item.getId(), item);
            itemLocator[coordinate.getX()][coordinate.getY()] = item.getId();
            added = true;
        }
        return added;
    }

    /**
     * Removes the given {@code Item} from the world at the given
     * {@code Coordinate}. If not {@code Item} exists, {@code null} is returned.
     *
     * @param coordinate
     * @return
     */
    public Item removeItem(Coordinate coordinate) {
        if (isInBounds(coordinate)) {
            UUID itemId = this.itemLocator[coordinate.getX()][coordinate.getY()];

            this.itemLocator[coordinate.getX()][coordinate.getY()] = null;
            return itemRegistry.remove(itemId);
        }
        return null;
    }
}
