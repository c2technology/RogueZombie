/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.c2technology.roguezombie.creature.ai.Creature;
import net.c2technology.roguezombie.creature.CreatureFactory;
import squidpony.squidmath.RNG;

/**
 * A {@code World} handles all state manipulation of it's denizens and utilizes
 * all mechanics within.
 *
 * @author cryan
 */
public class World {

    private final Tile[][] tiles;
    private final int width;
    private final int height;
    private final UUID[][] creatureLocator;
    private final Map<UUID, Creature> creatureRegistry;

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
        this.creatureLocator = new UUID[width][height];
        this.creatureRegistry = new HashMap();

    }

    /**
     * Returns a {@code Creature} if one exists at the given {@code coordinate}.
     * If no {@code Creature} exists, {@code null} is returned.
     *
     * @param coordinate
     * @return
     */
    public Creature getCreature(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return getCreature(x, y);
    }

    /**
     * Helper method for direct access to a Creature internally.
     *
     * @param x
     * @param y
     * @return
     */
    private Creature getCreature(int x, int y) {
        if (!outOfBounds(x, y)) {
            UUID creatureId = creatureLocator[x][y];
            //Is there a creature at this location?
            if (creatureId != null && creatureRegistry.containsKey(creatureId)) {
                return creatureRegistry.get(creatureId);
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
    public Tile getTile(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return getTile(x, y);
    }

    /**
     * Internal direct access to a Tile.
     *
     * @param x
     * @param y
     * @return
     */
    private Tile getTile(int x, int y) {
        if (outOfBounds(x, y)) {
            return Tile.BOUNDS;
        }
        return tiles[x][y];
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
        //TODO: Will have to add additional logic in here if we go past Creature and Tile...
        Entity entity = getCreature(coordinate);
        if (entity != null) {
            return entity;
        }
        return getTile(coordinate);
    }

    /**
     * Removes a {@code Creature} from the {@code World} if it exists.
     *
     * @param creature
     */
    public void remove(Creature creature) {
        if (!outOfBounds(creature)) {
            Creature dead = getCreature(creature.getCoordinate());
            if (dead != null) {
                creatureRegistry.remove(dead.getId());
                creatureLocator[dead.getCoordinate().getX()][dead.getCoordinate().getY()] = null;
            }
        }
    }

    /**
     * Ends the current turn and handles all end turn actions for the
     * {@code World}
     */
    public void endTurn() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Creature creature = getCreature(x, y);
                if (creature != null) {
                    creature.resolveTurn();
                }
            }
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
        int x;
        int y;

        //TODO: This logic should be determined by something else. Otherwise, we will need to add additional checks for unknown types (water is impassible without a boat [or bridge, but only for 1 block] .. etc).
        do {
            x = (int) (Math.random() * width);//90
            y = (int) (Math.random() * height);//31
        } while (!getTile(x, y).isPassable() && getCreature(x, y) == null);

        return new Coordinate(x, y);

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
     * Moves a {@code creature} within the world in the given {@code direction}.
     *
     * @param creature
     * @param direction
     */
    public void move(Creature creature, Cardinal direction) {
        creature.move(direction);
    }

    /**
     * Updates the world state when a creature moves.
     *
     * @param creature
     * @param oldCoordinate
     */
    public void creatureMoved(Creature creature, Coordinate oldCoordinate) {
        Coordinate newCoordinate = creature.getCoordinate();
        creatureLocator[oldCoordinate.getX()][oldCoordinate.getY()] = null;
        creatureLocator[newCoordinate.getX()][newCoordinate.getY()] = creature.getId();
    }

    /**
     * Adds the given {@code Creature} if it does not exist. If it does exist,
     * it is updated.
     *
     * @param creature
     */
    public boolean addCreature(Creature creature) {
        //TODO: Make a spawn method that spawns within a given range of a given coordinate
        boolean added = false;
        if (!outOfBounds(creature)) {
            creatureRegistry.put(creature.getId(), creature);
            creatureLocator[creature.getCoordinate().getX()][creature.getCoordinate().getY()] = creature.getId();
            added = true;
        }
        return added;
    }

    private void createCreatures(CreatureFactory factory) {
        //TODO: Allow this to be configurable for difficulty
        int creatureLimit = new RNG().between(5, 10);
        for (int i = 0; i <= creatureLimit; i++) {
            Creature creature = factory.makeZombie(this);
            creature.setCoordinate(getRandomSpawnableLocation());
            addCreature(creature);
        }
    }

    public Collection<Creature> getCreatures() {
        return this.creatureRegistry.values();
    }

    /**
     * Helper function for boundary testing of {@code Coordinate}
     *
     * @param coordinate
     * @return
     */
    private boolean outOfBounds(Coordinate coordinate) {
        return outOfBounds(coordinate.getX(), coordinate.getY());
    }

    /**
     * Tests to determine if the given coordinates are out of bounds.
     *
     * @param creature
     * @return
     */
    private boolean outOfBounds(int x, int y) {
        //Check for out of bounds for fast failures
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    /**
     * Helper method for creatures
     *
     * @param creature
     * @return
     */
    private boolean outOfBounds(Creature creature) {
        return outOfBounds(creature.getCoordinate());
    }

    /**
     * Spawns a creature. If the creature's coordinates are not spawnable,
     * attempts will be made within a small range around the original
     * coordinates. If the creature cannot be spawned within the range it will
     * not spawn.
     *
     * @param creature
     */
    public void spawn(Creature creature) {
        try {
            Coordinate coord = getSpawnableLocation(creature.getCoordinate());
            creature.setCoordinate(coord);
            //TODO: Determine better method for this
            creatureRegistry.put(creature.getId(), creature);
            creatureLocator[creature.getCoordinate().getX()][creature.getCoordinate().getY()] = creature.getId();
        } catch (UnspawnableException e) {
            System.out.println("Could not spawn creature at " + creature.getCoordinate().toString());
        }
    }

    public boolean isInBounds(Coordinate target) {
        int x = target.getX();
        int y = target.getY();
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

}
