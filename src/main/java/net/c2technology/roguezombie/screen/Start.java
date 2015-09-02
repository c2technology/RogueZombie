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
 * The start state representation of the game. This {@code Screen} shows the
 * human user information about the game and how to play.
 *
 * It's dangerous to go alone, take this...
 *
 * @author cryan
 */
public class Start implements Screen {

    /**
     * Displays the instructions to the user.
     *
     * @param terminal
     */
    @Override
    public void display(AsciiPanel terminal) {
        //FIXME: Write the instructions here!!!
        terminal.write("Rogue Zombie!!!", 1, 1);
        terminal.writeCenter("-- Press [Enter] to Start --", 22);
    }

    /**
     * Handles the Enter key event. Any other keys are ignored.
     *
     * @param key
     * @return
     */
    @Override
    public Screen respond(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new Play() : this;
    }

}
