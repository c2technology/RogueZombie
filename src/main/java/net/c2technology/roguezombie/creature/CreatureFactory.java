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

import net.c2technology.roguezombie.creature.enemy.Zombie;
import net.c2technology.roguezombie.creature.ai.PlayerAi;
//import net.c2technology.roguezombie.creature.ai.DumbZombieAi;
import asciiPanel.AsciiPanel;
import net.c2technology.roguezombie.creature.ai.DumbZombieAi;
import net.c2technology.roguezombie.creature.ai.SmartZombieAi;
import net.c2technology.roguezombie.creature.ai.ZombieAi;
//import net.c2technology.roguezombie.creature.ai.SmartZombieAi;
//import net.c2technology.roguezombie.creature.ai.ZombieAi;
import net.c2technology.roguezombie.world.World;
import net.c2technology.roguezombie.world.los.FieldOfView;

/**
 * Creates the majority of {@code Creature} objects that could be placed in the
 * {@code World}. All but the {@code Player} is randomized to some degree.
 *
 * Churns out {@code Creature}s like there's no tomorrow (for the {@code Player}
 * that is).
 *
 * @author cryan
 */
public class CreatureFactory {

    /**
     * Default constructor.
     */
    public CreatureFactory() {

    }

    /**
     * Makes a new {@code Player}.
     *
     * @param world the {@code World} in which the new {@code Player} belongs.
     * @param fieldOfView the originating {@code FieldOfView} for the player.
     * This could already have certain areas exposed if desired.
     * @return
     */
    public Player makePlayer(World world, FieldOfView fieldOfView) {

        Player player = new Player(world, '@', AsciiPanel.brightWhite, 2);
        //TODO: This seems a bit wonky.
        PlayerAi playerAi = new PlayerAi(player, fieldOfView);
        return player;
    }

    /**
     * Creates a {@code Zombie}. The new {@code Zombie} has a randomly selected
     * {@code Ai}.
     *
     * @param world The {@code World} in which the new {@code Zombie} belongs.
     * @return
     */
    public Zombie makeZombie(World world) {

        Zombie zombie = new Zombie(world, 'Z', AsciiPanel.green);
        //TODO: This seems a bit wonky.
        ZombieAi zombieAi = Math.random() > .5 ? new DumbZombieAi(zombie) : new SmartZombieAi(zombie);
        return zombie;
    }

}
