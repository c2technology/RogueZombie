/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Tile;

/**
 *
 * @author cryan
 * @param <T> The body type of the AI
 */
public interface Ai<T> {

    public void resolveTurn(T me);

    /**
     * Determines if the {@code targetTile} at the {@code newCoordinate} is able
     * to be entered by the body if this {@code AI}.
     *
     * @param me
     * @param newCoordinate
     * @param targetTile
     * @return
     */
    public boolean canEnter(T me, Coordinate newCoordinate, Tile targetTile);

    /**
     * Performs checks against the given parameters to determine if the attack
     * is legal. If the attack is, the attack is performed.
     *
     * @param me The body of the AI
     * @param creature
     */
    public void attack(T me, Creature creature);

    /**
     * Determines if this {@code CreatureAI} can see the given coordinate.
     *
     * @param me The body of the AI
     * @param coordinate The target coordinate to see.
     * @return
     */
    public boolean canSee(T me, Coordinate coordinate);
}
