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
 *
 * @author cryan
 */
public interface Creature extends Entity {

    public UUID getId();

    public void move(Cardinal cardinal);

    public void resolveTurn();

    public void onEnter(Coordinate coordinate);

    public Coordinate getCoordinate();

    public void setCoordinate(Coordinate coordinate);

    public World getWorld();

    public CreatureType getCreatureType();

    /**
     * Determines weather to attach the given {@code creature} and if it should,
     * performs the attack;
     *
     * @param creature
     */
    public void attack(Creature creature);

}
