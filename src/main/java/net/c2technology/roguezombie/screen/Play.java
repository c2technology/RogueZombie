/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import net.c2technology.roguezombie.creature.Player;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Entity;
import net.c2technology.roguezombie.world.World;
import net.c2technology.roguezombie.world.WorldBuilder;

/**
 *
 * @author cryan
 */
public class Play implements Screen {

    private final World world;
    private final int screenWidth;
    private final int screenHeight;

    public Play() {
        this.screenWidth = 80;
        this.screenHeight = 21;
        world = createWorld();

    }

    private World createWorld() {
        return new WorldBuilder(90, 31).designWorld().build();
    }

    public Coordinate getScrollCoordinate() {
        int x = Math.max(0, Math.min(world.getPlayer().getCoordinate().getX() - screenWidth / 2, world.getWidth() - screenWidth));
        int y = Math.max(0, Math.min(world.getPlayer().getCoordinate().getY() - screenHeight / 2, world.getHeight() - screenHeight));
        return new Coordinate(x, y);
    }

    /**
     * Render all visible aspects of the world starting at the {@code topLeft}
     * of the visible world.
     *
     * @param context
     * @param topLeft
     */
    private void displayWorld(AsciiPanel context, Coordinate topLeft) {//10, 15
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Coordinate coordinate = new Coordinate(x + topLeft.getX(), y + topLeft.getY());
                Entity entity = world.getEntity(coordinate);
                context.write(entity.getGlyph(), x, y, entity.getColor());
            }
        }
    }

    @Override
    public void display(AsciiPanel terminal) {
        terminal.write("You are having fun.", 1, 1);
        Coordinate upperLeftPosition = getScrollCoordinate();
        displayWorld(terminal, upperLeftPosition);
        Player player = world.getPlayer();
        Coordinate playerCoordinate = player.getCoordinate();
        terminal.write(player.getGlyph(), playerCoordinate.getX() - upperLeftPosition.getX(), playerCoordinate.getY() - upperLeftPosition.getY(), player.getColor());
        terminal.writeCenter("X: " + playerCoordinate.getX(), 21);
        terminal.writeCenter("Y: " + playerCoordinate.getY(), 22);
        terminal.writeCenter("Total Zombies: " + world.getCreatures().size(), 23);
//        terminal.writeCenter("-- press [escape] to lose or [enter] to win --", 23);
    }

    @Override
    public Screen respond(KeyEvent key) {
        Cardinal cardinal;
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new Lose();//TODO: Show Player results (close calls, how close they were to the helicopter, etc)
            case KeyEvent.VK_ENTER:
                return new Win();//TODO: Show Player results (close calls, etc)
            //North
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                cardinal = Cardinal.NORTH;
                break;
            //North East
            case KeyEvent.VK_U:
                cardinal = Cardinal.NORTH_EAST;
                break;
            //East
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                cardinal = Cardinal.EAST;
                break;
            //South East                
            case KeyEvent.VK_N:
                cardinal = Cardinal.SOUTH_EAST;
                break;
            //South
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                cardinal = Cardinal.SOUTH;
                break;
            //South West
            case KeyEvent.VK_B:
                cardinal = Cardinal.SOUTH_WEST;
                break;
            //West
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
                cardinal = Cardinal.WEST;
                break;
            //North West
            case KeyEvent.VK_Y:
                cardinal = Cardinal.NORTH_WEST;
                break;
            default:
                cardinal = Cardinal.NONE;
                break;
        }
        world.movePlayer(cardinal);
        world.endTurn();

        return this;
    }

}
