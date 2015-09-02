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
import java.awt.Color;
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
        String shortStripe = "                       ";
        String longStripe = "                                                ";
        String star1 = " *   *   *   *   *   *   ";
        String star2 = "   *   *   *   *   *   * ";

        terminal.write(star1, 30, 1, Color.WHITE, Color.BLUE);
        terminal.write(star2, 30, 2, Color.WHITE, Color.BLUE);
        terminal.write(star1, 30, 3, Color.WHITE, Color.BLUE);
        terminal.write(star2, 30, 4, Color.WHITE, Color.BLUE);
        terminal.write(star1, 30, 5, Color.WHITE, Color.BLUE);
        terminal.write(star2, 30, 6, Color.WHITE, Color.BLUE);
        terminal.write(star1, 30, 7, Color.WHITE, Color.BLUE);

        terminal.write(shortStripe, 30 + star1.length(), 1, Color.RED, Color.RED);
        terminal.write(shortStripe, 30 + star2.length(), 2, Color.WHITE, Color.WHITE);
        terminal.write(shortStripe, 30 + star1.length(), 3, Color.RED, Color.RED);
        terminal.write(shortStripe, 30 + star2.length(), 4, Color.WHITE, Color.WHITE);
        terminal.write(shortStripe, 30 + star1.length(), 5, Color.RED, Color.RED);
        terminal.write(shortStripe, 30 + star2.length(), 6, Color.WHITE, Color.WHITE);
        terminal.write(shortStripe, 30 + star1.length(), 7, Color.RED, Color.RED);
        terminal.write(longStripe, 30, 8, Color.WHITE, Color.WHITE);
        terminal.write(longStripe, 30, 9, Color.RED, Color.RED);
        terminal.write(longStripe, 30, 10, Color.WHITE, Color.WHITE);
        terminal.write(longStripe, 30, 11, Color.RED, Color.RED);
        terminal.write(longStripe, 30, 12, Color.WHITE, Color.WHITE);
        terminal.write(longStripe, 30, 13, Color.RED, Color.RED);

        terminal.write("Hey dudes, Thanks", 1, 2);
        terminal.write("for rescuing me.", 5, 4);
        terminal.write("Let's go for", 9, 6);
        terminal.write("a burger...", 9, 8);

        terminal.writeCenter("--> Press [ENTER] to Restart <--", 20);
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
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new Start() : this;
    }

}
