/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import net.c2technology.roguezombie.screen.Screen;
import net.c2technology.roguezombie.screen.Start;

/**
 *
 * @author cryan
 */
public class Game implements KeyListener {

    private final AsciiPanel content;
    private Screen context;
    private final JFrame window;
    private boolean started = false;

    public Game() {
        window = new JFrame();
        content = new AsciiPanel();
        content.write("Rogue Zombie", 1, 1);
        window.add(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
    }

    public void start() {
        if (!started) {
            context = new Start();
            window.addKeyListener(this);
            this.repaint();
            started = true;
            this.show();
        }
    }

    private void show() {
        if (!this.window.isVisible()) {
            this.window.setVisible(true);
        }
    }

    private void repaint() {
        content.clear();
        context.display(content);
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyPressed(KeyEvent event) {
        context = context.respond(event);
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Do nothing
    }

}
