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
 */
public interface Ai<T> {

    public void resolveTurn(T me);

    public boolean canEnter(T me, Coordinate newCoordinate, Tile targetTile);

    /**
     * Performs checks against the given parameters to determine if the attack
     * is legal. If the attack is, the attack is performed.
     *
     * @param me
     * @param creature
     */
    public void attack(T me, Creature creature);
}
