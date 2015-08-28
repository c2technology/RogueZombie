package net.c2technology.roguezombie.old;

import javax.swing.JFrame;
import squidpony.SColor;

/**
 *
 * @author cryan
 */
public class RogueZombie {

    //TODO: Move these somewhere else
    private static final SColor HUMAN = SColor.ATOMIC_TANGERINE;
    private static final SColor ZOMBIE = SColor.ASPARAGUS;
    private static final SColor WALL = SColor.ASPARAGUS;
    private static final SColor FLOOR = SColor.GRAY;
    private static final SColor DOOR = SColor.BLACK;
    private final MapGenerator mapGenerator = MapGenerator.getInstance();

    private final JFrame map;

    public RogueZombie(int width, int height, int scale) {
        mapGenerator.setWidth(width);
        mapGenerator.setHeight(height);
        mapGenerator.setName("Rogue Zombie!");
        map = mapGenerator.build();
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setLocationRelativeTo(null);
        map.getContentPane().setBackground(SColor.BLACK);
        map.pack();
        map.setVisible(true);
    }

    public void display() {
        map.setVisible(true);
    }

    public static void main(String[] args) {
        //TODO: Add width and height parameter options, default to 80 x 20
        RogueZombie app = new RogueZombie(80, 80, 80);
        app.display();
    }
}
