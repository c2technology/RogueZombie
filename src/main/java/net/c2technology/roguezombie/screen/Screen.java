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
public interface Screen {

    public void display(AsciiPanel terminal);

    public Screen respond(KeyEvent key);

}
