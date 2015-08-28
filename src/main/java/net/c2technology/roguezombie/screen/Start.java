/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

/**
 *
 * @author cryan
 */
public class Start implements Screen {

    @Override
    public void display(AsciiPanel terminal) {
        terminal.write("Rogue Zombie!!!", 1, 1);
        terminal.writeCenter("-- Press [Enter] to Start --", 22);
    }

    @Override
    public Screen respond(KeyEvent key) {
         return key.getKeyCode() == KeyEvent.VK_ENTER ? new Play() : this;
    }

}
