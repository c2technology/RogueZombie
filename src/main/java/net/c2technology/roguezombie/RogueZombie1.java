package net.c2technology.roguezombie;

import javax.swing.JFrame;

/**
 *
 * @author cryan
 */
public class RogueZombie1 extends JFrame {

    private RogueZombie1() {

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
