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
import java.util.ArrayList;
import java.util.List;
import net.c2technology.roguezombie.creature.Creature;
import net.c2technology.roguezombie.creature.ClutterFactory;
import net.c2technology.roguezombie.creature.Player;
import net.c2technology.roguezombie.item.Item;
import net.c2technology.roguezombie.world.Cardinal;
import net.c2technology.roguezombie.world.Coordinate;
import net.c2technology.roguezombie.world.Entity;
import net.c2technology.roguezombie.world.RandomNumber;
import net.c2technology.roguezombie.world.Tile;
import net.c2technology.roguezombie.world.World;
import net.c2technology.roguezombie.world.dungeon.DungeonBuilder;
import net.c2technology.roguezombie.world.los.FieldOfView;

/**
 * The {@code Play} {@code Screen} shows the actual progress and state of the
 * game. This also processes delegated input from the human user.
 *
 * Game time!
 *
 * @author cryan
 */
public class Play implements Screen {

    private final World world;
    private final int screenWidth;
    private final int screenHeight;
    private final FieldOfView fieldOfView;
    private final ClutterFactory clutterFactory;
    private final Player player;
    private boolean fogOfWar = true;
    //TODO: Convert messaging to Listner/Observer Pattern
    private final List<String> globalMessageQueue;
    private final Item winItem;

    /**
     * Default constructor
     */
    public Play() {
        //TODO: Take screen size as parameters? Could make the underlying World it proportional to the visible screen size...
        this.screenWidth = 80;
        this.screenHeight = 21;
        clutterFactory = new ClutterFactory();
        world = createWorld();
        fieldOfView = new FieldOfView(world);
        globalMessageQueue = new ArrayList();
        player = clutterFactory.makePlayer(world, fieldOfView, globalMessageQueue);
        winItem = clutterFactory.makeWinItem();
        player.performMove(world.getRandomSpawnableLocation());
        world.addItem(winItem);

        world.setPlayer(player);

    }

    /**
     * Creates the actual world. This world is of the Dungeon variety and will
     * have rooms and hallways.
     *
     * @return
     */
    private World createWorld() {
        World newWorld = new DungeonBuilder(90, 31).build();
        decorateWorld(newWorld);
        return newWorld;

    }

    /**
     * Gets the coordinate at the top-left corner of the visible screen. Useful
     * when determining the scrolling offset.
     *
     * @return
     */
    private Coordinate getTopLeft() {
        int x = Math.max(0, Math.min(player.getCoordinate().getX() - screenWidth / 2, world.getWidth() - screenWidth));
        int y = Math.max(0, Math.min(player.getCoordinate().getY() - screenHeight / 2, world.getHeight() - screenHeight));
        return new Coordinate(x, y);
    }

    /**
     * Render all visible aspects of the world relative to the {@code Player}.
     *
     * @param context
     */
    private void displayWorld(AsciiPanel context) {
        Coordinate topLeft = getTopLeft();
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Coordinate coordinate = new Coordinate(x + topLeft.getX(), y + topLeft.getY());
                Entity entity;
                Color color;
                if (player.look(coordinate) || !fogOfWar) {
                    //Get the creature or tile, whatever is there..
                    entity = world.getEntity(coordinate);
                    color = entity.getColor();
                } else {
                    //Get the last remembered tile and fade it out..
                    entity = fieldOfView.getRememberedTile(coordinate);
                    color = Color.DARK_GRAY;
                }
                context.write(entity.getGlyph(), x, y, color);
            }
        }
    }

    /**
     * Displays this {@code Screen} on the given {@code terminal}.
     *
     * @param terminal
     */
    @Override
    public void display(AsciiPanel terminal) {
        Coordinate playerCoordinate = player.getCoordinate();
        fieldOfView.update(playerCoordinate, player.getVisionRadius());

        displayWorld(terminal);
        displayMessages(terminal);
        Coordinate upperLeftPosition = getTopLeft();

        terminal.write(player.getGlyph(), playerCoordinate.getX() - upperLeftPosition.getX(), playerCoordinate.getY() - upperLeftPosition.getY(), player.getColor());

        terminal.write(String.format("Health: %s/%s", player.getHealth(), player.getMaxHealth()), 0, 21);
        terminal.write(String.format("Armor: %s", player.getArmor()), 0, 22);
        terminal.write(String.format("Attack: %s", player.getAttack()), 0, 23);

        terminal.write("F: Toggle Fog of War", 17, 21);
        terminal.write("[ENTER]: Pick up Item", 17, 22);
        terminal.write(String.format("Current Location: %s, %s", player.getCoordinate().getX(), player.getCoordinate().getY()), 17, 23);

        terminal.write("Use the Num Pad or Arrows to move", 45, 21);
        terminal.write("You can move in any direction!", 45, 22);
        terminal.write("Total Zombies: " + world.getCreatures().size(), 45, 23);
    }

    /**
     * Handles the delegated {@code KeyEvent} and returns the updated
     * {@code Screen}.
     *
     * @param key
     * @return
     */
    @Override
    public Screen respond(KeyEvent key) {
        if (!player.hasHealth()) {
            return new Lose();
        }

        switch (key.getKeyCode()) {
            //North
            case KeyEvent.VK_UP:
            case 104://8 on the keypad
                player.move(Cardinal.NORTH);
                break;
            //North East
            case 105://9 on the keypad
            case 33://9 on the keypad (without Num Lock)
                player.move(Cardinal.NORTH_EAST);
                break;
            //East
            case KeyEvent.VK_RIGHT:
            case 102://6 on the keypad
                player.move(Cardinal.EAST);
                break;
            //South East                
            case 99://3 on the keypad
            case 34://3 on the keypad (without Num Lock)
                player.move(Cardinal.SOUTH_EAST);
                break;
            //South
            case KeyEvent.VK_DOWN:
            case 98://2 on the keypad
                player.move(Cardinal.SOUTH);
                break;
            //South West
            case 97://1 on the keypad
            case 35://1 on the keypad (without Num Lock)
                player.move(Cardinal.SOUTH_WEST);
                break;
            //West
            case KeyEvent.VK_LEFT:
            case 100://4 on the keypad
                player.move(Cardinal.WEST);
                break;
            //North West
            case 103://4 on the keypad
            case 36://7 on the keypad (without Num Lock)
                player.move(Cardinal.NORTH_WEST);
                break;
            //Pickup
            case KeyEvent.VK_ENTER:
                player.pickup();
                break;
            //Toggle Fog of War
            case KeyEvent.VK_F:
                fogOfWar = !fogOfWar;
                return this;

            case KeyEvent.VK_Q:
                return new Lose();
            case KeyEvent.VK_W:
                return new Win();
            default:
                //don't do anything if an unknown key was pressed
                return this;
        }
        world.endTurn();
        if (onExit()) {
            if (isExitEligible()) {
                return new Win();
            }
            player.notify("Hmmm... I can't leave without the President....");
        }
        return this;
    }

    /**
     * Determines if the {@code Player} is currently on the exit {@code Tile}.
     *
     * @return
     */
    private boolean onExit() {
        return world.getTile(player.getCoordinate()) == Tile.EXIT;
    }

    /**
     * Determines if the {@code Player} can exit. To be eligible for exit, the
     * {@code Player} must have the "Zombie Cure."
     *
     * @return
     */
    private boolean isExitEligible() {
        for (Item item : player.getInventory().getItems()) {
            if (item.equals(winItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Decorates the {@code World} with various items.
     *
     * @param world
     * @return
     */
    private void decorateWorld(World world) {
        createCreatures(world);
        createItems(world);
    }

    /**
     * Creates random creatures and places them into the {@code world}
     *
     * @param factory
     */
    private void createCreatures(World world) {
        //TODO: Allow this to be configurable for difficulty
        int creatureLimit = RandomNumber.between(5, 10);
        for (int i = 0; i <= creatureLimit; i++) {
            Creature creature = clutterFactory.makeCreature(world);
            world.spawnCreature(creature);
        }
    }

    /**
     * Creates random {@code Item}s and places them into the {@code world}
     *
     * @param factory
     */
    private void createItems(World world) {
        //TODO: Add more items
//        int itemLimit = RandomNumber.between(15, 20);
//        for (int i = 0; i <= itemLimit; i++) {
//        Item item = clutterFactory.makeItem(world);
//            world.addItem(item);
//        }
    }

    /**
     * Writes all messages added to the global message queue then clears the
     * queue,
     *
     * @param terminal
     */
    private void displayMessages(AsciiPanel terminal) {
        int top = screenHeight - globalMessageQueue.size();
        for (int i = 0; i < globalMessageQueue.size(); i++) {
            terminal.writeCenter(globalMessageQueue.get(i), top + i);
        }
        globalMessageQueue.clear();
    }
}
