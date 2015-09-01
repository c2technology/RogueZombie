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
 * The win {@code Screen}. This shows statistics about the game and provides the
 * ending for the {@code Player}.
 *
 * @author cryan
 */
public class Win implements Screen {

    /**
     * Describes the ending to the user.
     *
     * @param terminal
     */
    @Override
    public void display(AsciiPanel terminal) {
        //FIXME: Show the end results to the user. Give them an AWESOME ASCII HELICOPTER!
        terminal.write("You won.", 1, 1);
        terminal.writeCenter("-- press [enter] to restart --", 22);
    }

    /**
     * Handles delegations for the {@code ENTER} key. All other keys are
     * ignored.
     *
     * @param key
     * @return
     */
    @Override
    public Screen respond(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new Play() : this;
    }

}
