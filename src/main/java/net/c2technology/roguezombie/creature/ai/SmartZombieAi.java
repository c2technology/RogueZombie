/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.creature.CreatureFactory;
import net.c2technology.roguezombie.creature.Player;
import net.c2technology.roguezombie.creature.Zombie;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import squidpony.squidmath.RNG;

/**
 * This is the intelligence object for a smart {@code Zombie}. The smart
 * {@code Zombie} builds upon the intelligence of the dumb {@code Zombie} and
 * will mill around until the {@code Player} is close. When the {@code Player}
 * is close, the smart {@code Zombie} shambles towards the {@code Player}. If
 * the {@code Player} is not close, the smart {@code Zombie} will either take a
 * random and valid step. The smart {@code Zombie} has a 5% chance of generating
 * a new smart {@code Zombie} to simulate a hoarding effect (because there are
 * hidden Zombies everywhere!)
 *
 * @author cryan
 */
public class SmartZombieAi extends DumbZombieAi {

    public SmartZombieAi(CreatureFactory factory) {
        super(factory);
    }

    /**
     * Determine if the Zombie is near the Player and move towards the player.
     * If the Zombie is not near a player, attempt to move to a random space. If
     * the Zombie cannot move to the random space, the Zombie does not move.
     *
     * @param me
     */
    @Override
    protected void move(Zombie me) {
        if (senseHumans(me)) {
//            Coordinate playerCoordinate = me.getWorld().getPlayer().getCoordinate();
//            Coordinate myCoordinate = me.getCoordinate();
//            me.move(Cardinal.getDirection(myCoordinate, playerCoordinate));
        } else {
            super.move(me);
        }
    }

    /**
     * 5% chance to create a new Zombie.
     *
     * @return
     */
    @Override
    protected boolean willReplicate() {
        return new RNG().between(0, 20) == 5;
    }

    /**
     * Determines if the Zombie can sense any humans around.
     *
     * @param me
     * @return
     */
    private boolean senseHumans(Zombie me) {
//        Creature player = me.getWorld().getPlayer();
//
//        boolean xAxis = Math.abs(me.getCoordinate().getX() - player.getCoordinate().getX()) <= 5;
//        boolean yAxis = Math.abs(me.getCoordinate().getY() - player.getCoordinate().getY()) <= 5;
//
//        return xAxis && yAxis;
        return false;
    }

}
