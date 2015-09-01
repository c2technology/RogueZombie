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
package net.c2technology.roguezombie.screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

/**
 * The losing screen. Sums up what the {@code Player} did and lets the human
 * know they lost.
 *
 * Game over man... Game Over!
 *
 * @author cryan
 */
public class Lose implements Screen {

    /**
     * Writes out information related to the lost game.
     *
     * @param terminal
     */
    @Override
    public void display(AsciiPanel terminal) {
        terminal.write("You lost.", 1, 1);
        terminal.writeCenter("-- press [enter] to restart --", 22);
    }

    /**
     * Handles key events while on this screen. Pressing {@code [ENTER]} will
     * start a new game.
     *
     * @param key
     * @return
     */
    @Override
    public Screen respond(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new Play() : this;
    }

}
