/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import java.util.UUID;
import net.c2technology.roguezombie.creature.CreatureType;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Entity;
import net.c2technology.roguezombie.world.World;

/**
 * A resident of the game world. This includes all enemies, non-player
 * characters, and players.
 *
 * @author cryan
 */
public interface Creature extends Entity {

    /**
     * The unique ID of this {@code Creature}.
     *
     * @return
     */
    public UUID getId();

    /**
     * Moves the {@code Creature} in the given cardinal direction.
     *
     * @param cardinal
     */
    public void move(Cardinal cardinal);

    /**
     * Resolves this {@code Creature}'s turn
     *
     */
    public void resolveTurn();

    /**
     * Performs any actions when this {@code Creature} enters the given
     * {@code coordinate}
     *
     * @param coordinate
     */
    public void onEnter(Coordinate coordinate);

    /**
     * The current location of this {@code Creature}
     *
     * @return
     */
    public Coordinate getCoordinate();

    /**
     * Changes the current location of the {@code Creature}.
     *
     * @param coordinate
     */
    public void setCoordinate(Coordinate coordinate);

    /**
     * The {@code World} this {@code Creature} belongs in.
     *
     * @return
     */
    public World getWorld();

    /**
     * How far out the {@code Creature} can see.
     *
     * @return
     */
    public int getVisionRadius();

    /**
     * The type of this {@code Creature}
     *
     * @return
     */
    public CreatureType getCreatureType();

    /**
     * Determines weather to attach the given {@code creature} and if it should,
     * performs the attack;
     *
     * @param creature
     */
    public void attack(Creature creature);

    /**
     * Determines if this {@code Creature} can see the given {@code coordinate}.
     * Factors within the {@code Creature} may affect this and may not be
     * guaranteed to always return the same result.
     *
     * @param coordinate
     * @return
     */
    public boolean canSee(Coordinate coordinate);

}
