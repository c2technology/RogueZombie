package net.c2technology.roguezombie.old;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import squidpony.SColor;
import squidpony.squidgrid.Direction;
import squidpony.squidgrid.gui.SquidPanel;
import squidpony.squidgrid.mapping.ClassicRogueMapGenerator;
import squidpony.squidgrid.mapping.Terrain;

/**
 * Generates a random map with varying rooms and room sizes.
 *
 * @author cryan
 */
public class MapGenerator {

    private int frameWidth = 80;
    private int frameHeight = 25;
    private int width = 100;
    private int height = 80;
    private int scale = 10;
    private int horizontalRooms = 5;
    private int verticalRooms = 4;
    private int minRoomWidth = 3;
    private int maxRoomWidth = 15;
    private int minRoomHeight = 4;
    private int maxRoomHeight = 15;
    private String name = "";

    private static final MapGenerator INSTANCE = new MapGenerator();

    private MapGenerator() {

    }

    /**
     * Retrieves a MapGenerator with default values. The various values are
     * modifiable by calling the appropriate setter method before calling
     * {@link MapGenerator#build()}.
     *
     * @return
     */
    public static MapGenerator getInstance() {
        return INSTANCE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setHorizontalRooms(int horizontalRooms) {
        this.horizontalRooms = horizontalRooms;
    }

    public void setVerticalRooms(int verticalRooms) {
        this.verticalRooms = verticalRooms;
    }

    public void setMinRoomWidth(int minRoomWidth) {
        this.minRoomWidth = minRoomWidth;
    }

    public void setMaxRoomWidth(int maxRoomWidth) {
        this.maxRoomWidth = maxRoomWidth;
    }

    public void setMinRoomHeight(int minRoomHeight) {
        this.minRoomHeight = minRoomHeight;
    }

    public void setMaxRoomHeight(int maxRoomHeight) {
        this.maxRoomHeight = maxRoomHeight;
    }

    /**
     * Builds the map panel using values set using the various setter methods.
     *
     * @return
     */
    public JFrame build() {
        final ClassicRogueMapGenerator gen = new ClassicRogueMapGenerator(horizontalRooms, verticalRooms, width, height, minRoomWidth, maxRoomWidth, minRoomHeight, maxRoomHeight);
        final SquidPanel back = new SquidPanel(width, height, scale, scale);
        final SquidPanel front = new SquidPanel(width, height, scale, scale);

        JFrame frame = new JFrame(name);
        JLayeredPane layer = new JLayeredPane();
        layer.setLayer(back, JLayeredPane.DEFAULT_LAYER);
        layer.setLayer(front, JLayeredPane.PALETTE_LAYER);
        layer.add(back);
        layer.add(front);
        layer.setPreferredSize(back.getPreferredSize());
        layer.setSize(back.getPreferredSize());
        frame.add(layer);
        frame.setSize(frameWidth, frameHeight);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        paint(back, front, gen);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                paint(back, front, gen);
            }

        });
        return frame;
    }

    /**
     * Repaints the {@code back} and {@code front} panels using the provided map
     * generator
     *
     * @param back
     * @param front
     * @param gen
     */
    private void paint(SquidPanel back, SquidPanel front, ClassicRogueMapGenerator gen) {
        back.erase();
        front.erase();
        Terrain[][] map;
        map = gen.create();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                back.put(x, y, SColor.BLACK);
                SColor color;
                boolean hasNeighbor = false;
                for (Direction dir : Direction.OUTWARDS) {
                    int x2 = x + dir.deltaX;
                    int y2 = y + dir.deltaY;
                    if (x2 >= 0 && y2 >= 0 && x2 < width && y2 < height && map[x2][y2] != Terrain.WALL) {
                        hasNeighbor = true;
                        break;
                    }
                }
                if (hasNeighbor) {
                    front.put(x, y, map[x][y].symbol(), map[x][y].color());
                }
            }
        }

        back.refresh();
        front.refresh();
    }
}
