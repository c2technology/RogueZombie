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
package net.c2technology.roguezombie;

import javax.swing.JFrame;

/**
 * Rogue Zombie is a rogue-like game with a randomly generated world and
 * permanent deaths. The objective of this game is to find the helicopter to
 * escape the roaming zombies.
 *
 * @author cryan
 */
public class RogueZombie extends JFrame {

    /**
     * Private constructor
     */
    private RogueZombie() {

    }

    /**
     * Main running class. This takes no arguments and any passed in will be
     * ignored.
     *
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
