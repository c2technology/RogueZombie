/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature;

import net.c2technology.roguezombie.creature.ai.PlayerAi;
import net.c2technology.roguezombie.creature.ai.Creature;
import net.c2technology.roguezombie.creature.ai.DumbZombieAi;
import asciiPanel.AsciiPanel;
import net.c2technology.roguezombie.creature.ai.SmartZombieAi;
import net.c2technology.roguezombie.creature.ai.ZombieAi;
import net.c2technology.roguezombie.world.World;

/**
 *
 * @author cryan
 */
public class CreatureFactory {

    public CreatureFactory() {

    }

    public Player makePlayer(World world) {
        PlayerAi playerAi = new PlayerAi();
        Player player = new Player(world, '@', AsciiPanel.brightWhite, playerAi);
        return player;
    }

    public Creature makeZombie(World world) {
        ZombieAi zombieAi = Math.random() > .5 ? new DumbZombieAi(this) : new SmartZombieAi(this);
        Creature zombie = new Zombie(world, 'Z', AsciiPanel.green, zombieAi);
        return zombie;
    }

}