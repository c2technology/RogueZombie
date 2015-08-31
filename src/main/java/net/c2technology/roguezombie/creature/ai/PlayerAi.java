/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.creature.ai;

import net.c2technology.roguezombie.creature.Player;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.los.FieldOfView;

/**
 *
 * @author cryan
 */
public class PlayerAi extends AbstractCreatureAi<Player> {

    private final FieldOfView fieldOfView;

    public PlayerAi(FieldOfView fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    @Override
    public void resolveTurn(Player me) {
        //TODO: Check if they are dead
        //TODO: ???
    }

    /**
     * The player's Field of View is used when determining if the target can be
     * seen.
     *
     * @param me
     * @param target
     * @return
     */
    @Override
    public boolean canSee(Player me, Coordinate target) {
        return fieldOfView.isVisible(target);
    }

}
