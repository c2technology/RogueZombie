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
 * A {@code Screen} is a visual context of the game. It determines how to
 * display the game and how to represent the various states of various entities
 * within the game.
 *
 * @author cryan
 */
public interface Screen {

    /**
     * Displays the current state of the game on the given {@code terminal}.
     *
     * @param terminal
     */
    public void display(AsciiPanel terminal);

    /**
     * Handles the delegated {@code KeyEvent} based on the {@code Screen}
     * implementation.
     *
     * @param key
     * @return
     */
    public Screen respond(KeyEvent key);

}
