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

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import net.c2technology.roguezombie.screen.Screen;
import net.c2technology.roguezombie.screen.Start;

/**
 * The {@code Game} handles processing user input and redrawing the interface.
 *
 * @author cryan
 */
public class Game implements KeyListener {

    private final AsciiPanel content;
    private Screen context;
    private final JFrame window;
    private boolean started = false;

    /**
     * Create a new {@code Game} object. This will initialize a hidden window.
     */
    public Game() {
        window = new JFrame();
        content = new AsciiPanel();
        content.write("Rogue Zombie", 1, 1);
        window.add(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
    }

    /**
     * Starts and shows the game. Multiple calls to {@link Game#start()} are
     * ignored.
     */
    public void start() {
        if (!started) {
            context = new Start();
            window.addKeyListener(this);
            this.repaint();
            started = true;
            this.show();
        }
    }

    /**
     * Helper function for showing the game window. If the window is already
     * visible, this call is ignored.
     */
    private void show() {
        if (!this.window.isVisible()) {
            this.window.setVisible(true);
        }
    }

    /**
     * Repaints the user interface based on the current content and state of the
     * game.
     */
    private void repaint() {
        content.clear();
        context.display(content);
        window.repaint();
    }

    /**
     * Override for {@link KeyListener#keyTyped(java.awt.event.KeyEvent)}
     * events. No action is taken when a key is typed.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //Do nothing
    }

    /**
     * Handler for key presses. When a key is pressed, the event is passed to
     * the current game context for processing, then the interface is redrawn.
     *
     * @param event
     */
    @Override
    public void keyPressed(KeyEvent event) {
        context = context.respond(event);
        this.repaint();
    }

    /**
     * Override for {@link KeyListener#keyReleased(java.awt.event.KeyEvent)}
     * events. No action is taken.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //Do nothing
    }

}
