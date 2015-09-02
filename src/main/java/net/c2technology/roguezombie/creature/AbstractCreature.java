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
import java.awt.Color;
import java.util.UUID;
import net.c2technology.roguezombie.item.Item;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.World;

/**
 * The generic functions of any given {@code Creature}. Any {@code Creature}
 * that needs to perform differently should {@code Override} the appropriate
 * function(s).
 *
 * All {@code Creature} implementations should extend this class for ease of
 * use.
 *
 * @author cryan
 */
public abstract class AbstractCreature implements Creature {

    //TODO: Migrate creature settings into D&D style (dex, int... etc)?
    private final UUID id;
    private final Color color;
    private final char glyph;
    private final Inventory inventory;
    private Coordinate coordinate;
    private final World world;
    private Ai ai;
    private final CreatureType type;
    private final int visionRadius;
    private final int maxHealth;
    private int health;
    private final int baseAttack;
    private final int baseDefense;
    private final String name;

    /**
     * An {@code AbstractCreature} is an "incomplete" {@code Creature} with the
     * basic requirements.
     *
     * @param name
     * @param type
     * @param visionRadius
     * @param glyph
     * @param color
     * @param inventory
     * @param world
     */
    protected AbstractCreature(String name, CreatureType type, int visionRadius, char glyph, Color color, Inventory inventory, int health, int baseAttack, int baseDefense, World world) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.type = type;
        this.visionRadius = visionRadius;
        this.glyph = glyph;
        this.color = color;
        this.inventory = inventory;
        this.world = world;
        this.maxHealth = health;
        this.health = health;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    @Override
    public boolean hasHealth() {
        return this.health > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Determines, using the {@code AI} of the {@code Creature}, whether or not
     * this {@code Creature} can see the given {@code Coordinate}
     *
     * @param coordinate The {@code Coordinate} at which this {@code Creature}
     * is attempting to look.
     * @return
     */
    @Override
    public boolean look(Coordinate coordinate) {
        return ai.canSee(coordinate);
    }

    @Override
    public void pickup() {
        inventory.add(this.world.getItem(getCoordinate()));
    }

    @Override
    public void drop(Item item) {
        world.addItem(item);
        inventory.remove(item);
    }

    @Override
    public void setAi(Ai ai) {
        this.ai = ai;
    }

    /**
     * The type of this {@code Creature}.
     *
     * @return
     */
    @Override
    public CreatureType getCreatureType() {
        return type;
    }

    /**
     * The unique ID of this {@code Creature}.
     *
     * @return
     */
    @Override
    public UUID getId() {
        return this.id;
    }

    /**
     * The {@code Color} of this {@code Creature}
     *
     * @return
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * The radius of vision for this {@code Creature}. This is not guaranteed to
     * remain constant as effects on the {@code Creature} could affect the
     * overall vision.
     *
     * @return
     */
    @Override
    public int getVisionRadius() {
        //TODO: Change this to non-final when an effects are introduced
        return this.visionRadius;
    }

    /**
     * The {@code World} in which this {@code Creature} resides.
     *
     * @return
     */
    @Override
    public World getWorld() {
        return world;
    }

    /**
     * Locally accessible {@code Ai} of this {@code Creature}.
     *
     * @return
     */
    protected final Ai getAi() {
        return ai;
    }

    /**
     * The representation of this {@code Creature}
     *
     * @return
     */
    @Override
    public char getGlyph() {
        return glyph;
    }

    /**
     * The current location of this {@code Creature}.
     *
     * @return
     */
    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Updates the current location of this {@code Creature}.
     *
     * @param coordinate
     */
    @Override
    public void performMove(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Determines if this {@code Creature} is able to be passed through.
     * Typically, a non-corporeal {@code Creature} is not passable.
     *
     * @return
     */
    @Override
    public boolean isPassable() {
        //TODO: This is more related to a Tile than a Creature. Determine a better way to handle this.
        return false;
    }

    /**
     * Attempts to move this {@code Creature} in the {@code cardinal} direction.
     *
     * @param cardinal
     */
    @Override
    public void move(Cardinal cardinal) {
        Coordinate newCoordinate = new Coordinate(coordinate.getX() + cardinal.getOffsetX(), coordinate.getY() + cardinal.getOffsetY());
        if (getAi().canEnter(newCoordinate, getWorld().getTile(newCoordinate))) {
            performMove(newCoordinate);
        } else {
            //We can't enter... is this an enemy encounter?
            Creature otherCreature = getWorld().getCreature(newCoordinate);
            if (otherCreature != null) {
                //attack determines wether or not to actually attack
                attack(otherCreature);
            }
        }
    }

    /**
     * Delegates to {@code Ai} to attack the {@code other} {@code Creature}.
     *
     * @param other
     */
    @Override
    public void attack(Creature other) {
        getAi().attack(other);
    }

    @Override
    public int getAttack() {
        return this.baseAttack;
    }

    @Override
    public int getArmor() {
        return this.baseDefense;
    }

    @Override
    public void modifyHealth(int adjustment) {
        this.health += adjustment;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void notify(String message) {

        ai.onNotify(message);
    }

}
