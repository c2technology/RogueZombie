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
package net.c2technology.roguezombie.creature.ai;

import java.util.List;
import net.c2technology.roguezombie.creature.Player;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.los.FieldOfView;

/**
 * Provides the basic capabilities of the {@code Player} for interacting with
 * the {@code World}.
 *
 * @author cryan
 */
public class PlayerAi extends AbstractCreatureAi<Player> {

    private final FieldOfView fieldOfView;
    //This is passed by reference so the caller has a handle on any messages added
    protected final List<String> messages;

    /**
     * Creates the {@code PlayerAi} with a default {@code fieldOfView}.
     *
     * @param me
     * @param fieldOfView
     */
    public PlayerAi(Player me, FieldOfView fieldOfView, List<String> globalMessageQueue) {
        super(me);
        this.fieldOfView = fieldOfView;
        this.messages = globalMessageQueue;
    }

    /**
     * Resolves the {@code Player} turn.
     */
    @Override
    public void resolveTurn() {
        //TODO: Check if they are dead
        //TODO: Check surroundings and report to the player
    }

    /**
     * The player's Field of View is used when determining if the target can be
     * seen.
     *
     * @param target
     * @return
     */
    @Override
    public boolean canSee(Coordinate target) {
        return fieldOfView.isVisible(target);
    }

    /**
     * Add the message to the global message queue.
     *
     * @param message
     */
    @Override
    public void onNotify(String message) {
        messages.add(message);
    }
}
