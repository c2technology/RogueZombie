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
public class Start extends ResizeableScreen {

    public Start(int height, int width) {
        super(height, width);
    }

    /**
     * Displays the instructions to the user.
     *
     * @param terminal
     */
    @Override
    public void display(AsciiPanel terminal) {
        terminal.write("   __                          _____                _     _      ", 5, 1);
        terminal.write("  /__\\ ___   __ _ _   _  ___  / _  / ___  _ __ ___ | |__ (_) ___ ", 5, 2);
        terminal.write(" / \\/// _ \\ / _` | | | |/ _ \\ \\// / / _ \\| '_ ` _ \\| '_ \\| |/ _ \\", 5, 3);
        terminal.write("/ _  \\ (_) | (_| | |_| |  __/  / //\\ (_) | | | | | | |_) | |  __/", 5, 4);
        terminal.write("\\/ \\_/\\___/ \\__, |\\__,_|\\___| /____/\\___/|_| |_| |_|_.__/|_|\\___|", 5, 5);
        terminal.write("            |___/                                                ", 5, 6);
        terminal.writeCenter("Washington DC has been overrun by zombies.", 10);
        terminal.writeCenter("ARE YOU A BAD ENOUGH DUDE TO SAVE THE PRESIDENT?", 12);

        terminal.writeCenter("--> Press [ENTER] to Start <--", 15);
        terminal.writeCenter("(C) 2015, Chris Ryan ", 20);

    }

    /**
     * Handles the Enter key event. Any other keys are ignored.
     *
     * @param key
     * @return
     */
    @Override
    public Screen respond(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new Play(getHeight(), getWidth()) : this;
    }

}
