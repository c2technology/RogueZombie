//package net.c2technology.roguezombie.old;
//
//import java.awt.Point;
//import java.awt.Rectangle;
//import java.rmi.registry.Registry;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import squidpony.squidmath.RNG;
//
//public class Node {
//
//    private final int minLeafSize;
//    private final int maxLeafSize;
//
//    private final int y;
//    private final int x;
//    private final int width;
//    private final int height; // the position and size of this Leaf
//
//    private Node child1 = null; // the Leaf's left child Leaf
//    private Node child2 = null; // the Leaf's right child Leaf
//    private Room room;
////    public final Leaf room; // the room that is inside this Leaf
//    private LinkedList<Node> halls; // hallways to connect this Leaf to other Leafs
//
//    public Node(int x, int y, int width, int height, int minLeafSize, int maxLeafSize) {
//        // initialize our leaf
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//        this.minLeafSize = minLeafSize;
//        this.maxLeafSize = maxLeafSize;
//    }
//
//    public boolean split() {
//        // begin splitting the leaf into two children
//        if (child1 != null || child2 != null) {
//            return false; // we're already split! Abort!
//        }
//
//        // determine direction of split
//        // if the width is >25% larger than height, we split vertically
//        // if the height is >25% larger than the width, we split horizontally
//        // otherwise we split randomly
//        boolean splitHorizontally;
//        if (width > height && width / height >= 1.25) {
//            splitHorizontally = false;
//        } else if (height > width && height / width >= 1.25) {
//            splitHorizontally = true;
//        } else {
//            splitHorizontally = Math.random() > 0.5;
//        }
//
//        int max = (splitHorizontally ? height : width) - minLeafSize; // determine the maximum height or width
//        if (max <= minLeafSize) {
//            return false; // the area is too small to split any more...
//        }
//
//        int split = new RNG().between(minLeafSize, max); // determine where we're going to split
//
//        // create our left and right children based on the direction of the split
//        if (splitHorizontally) {
//            child1 = new Node(x, y, width, split, minLeafSize, maxLeafSize);
//            child2 = new Node(x, y + split, width, height - split, minLeafSize, maxLeafSize);
//        } else {
//            child1 = new Node(x, y, split, height, minLeafSize, maxLeafSize);
//            child2 = new Node(x + split, y, width - split, height, minLeafSize, maxLeafSize);
//        }
//        return true; // split successful!
//    }
//
//    public boolean hasSplit() {
//        return child1 != null && child2 != null;
//    }
//
//    public boolean canSplit() {
//        return width > maxLeafSize || height > maxLeafSize || Math.random() > 0.25;
//    }
//
//    public List<Node> getRooms() {
//        List<Node> innerRooms = new ArrayList();
//        if (hasSplit()) {
//            if (child1 != null) {
//                innerRooms.add(child1);
//            }
//            if (child2 != null) {
//                innerRooms.add(child2);
//            }
//        }
//        return innerRooms;
//    }
//
//    public void createRooms() {
//        // this function generates all the rooms and hallways for this Leaf and all of its children.
//        if (child1 != null || child2 != null) {
//            // this leaf has been split, so go into the children leafs
//            if (child1 != null) {
//                child1.createRooms();
//            }
//            if (child2 != null) {
//                child2.createRooms();
//            }
//            createHall();
//        } else {
//            // this Node is the ready to make a room
//            Point roomSize;
//            Point roomCoordinates;
//            // the room can be between 3 x 3 tiles to the size of the leaf - 2.
//            roomSize = new Point(new RNG().between(3, width - 2), new RNG().between(3, height - 2));
//            // place the room within the Leaf, but don't put it right 
//            // against the side of the Leaf (that would merge rooms together)
//            roomCoordinates = new Point(new RNG().between(1, width - roomSize.x - 1), new RNG().between(1, height - roomSize.y - 1));
//            room = new Room(x + roomCoordinates.x, y + roomCoordinates.y, roomSize.x, roomSize.y);
//        }
//    }
//
//    /**
//     * Returns the room wthin this {@code Node}. If no {@code Room} exists,
//     * {@code null} is returned.
//     *
//     * @return
//     */
//    public Room getRoom() {
//        // iterate all the way through these leafs to find a room, if one exists.
//        if (room != null) {
//            return room;
//        }
//
//        Room room1 = null;
//        Room room2 = null;
//        if (child1 != null) {//get the room of the child if it exists!
//            room1 = child1.getRoom();
//        }
//        if (child2 != null) {//get the room of the child if it exists!
//            room2 = child2.getRoom();
//        }
//        if (room1 == null && room2 == null) {//No rooms here nothing we can do.
//            return null;
//        } else if (room2 == null) {//No room 2 send room 1...
//            return room1;
//        } else if (room1 == null) {//No room 1 send room 2...
//            return room2;
//        } else if (Math.random() > .5) { //if both rooms are set, return a random one
//            return room1;
//        } else {
//            return room2;
//        }
//
//    }
//
//    private void createHall() {
//        if (child1 != null && child2 != null) {
//            // now we connect these two rooms together with hallways.
//            // this looks pretty complicated, but it's just trying to figure out which point is where and then either draw a straight line, or a pair of lines to make a right-angle to connect them.
//            // you could do some extra logic to make your halls more bendy, or do some more advanced things if you wanted.
//
//            halls = new List<Room>;
//            Room room1 = child1.getRoom();
//            Room room2 = child2.getRoom();
//
//            var point1
//            :Point = new Point(new RNG().between(room1.left + 1, l.right - 2), Registry.randomNumber(l.top + 1, l.bottom - 2));
//            var point2
//            :Point = new Point(Registry.randomNumber(r.left + 1, r.right - 2), Registry.randomNumber(r.top + 1, r.bottom - 2));
//
//            var w
//            :Number = point2.x - point1.x;
//            var h
//            :Number = point2.y - point1.y;
//
//            if (w < 0) {
//                if (h < 0) {
//                    if (FlxG.random() < 0.5) {
//                        halls.push(new Rectangle(point2.x, point1.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point2.x, point2.y, 1, Math.abs(h)));
//                    } else {
//                        halls.push(new Rectangle(point2.x, point2.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point1.x, point2.y, 1, Math.abs(h)));
//                    }
//                } else if (h > 0) {
//                    if (FlxG.random() < 0.5) {
//                        halls.push(new Rectangle(point2.x, point1.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point2.x, point1.y, 1, Math.abs(h)));
//                    } else {
//                        halls.push(new Rectangle(point2.x, point2.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point1.x, point1.y, 1, Math.abs(h)));
//                    }
//                } else // if (h == 0)
//                {
//                    halls.push(new Rectangle(point2.x, point2.y, Math.abs(w), 1));
//                }
//            } else if (w > 0) {
//                if (h < 0) {
//                    if (FlxG.random() < 0.5) {
//                        halls.push(new Rectangle(point1.x, point2.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point1.x, point2.y, 1, Math.abs(h)));
//                    } else {
//                        halls.push(new Rectangle(point1.x, point1.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point2.x, point2.y, 1, Math.abs(h)));
//                    }
//                } else if (h > 0) {
//                    if (FlxG.random() < 0.5) {
//                        halls.push(new Rectangle(point1.x, point1.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point2.x, point1.y, 1, Math.abs(h)));
//                    } else {
//                        halls.push(new Rectangle(point1.x, point2.y, Math.abs(w), 1));
//                        halls.push(new Rectangle(point1.x, point1.y, 1, Math.abs(h)));
//                    }
//                } else // if (h == 0)
//                {
//                    halls.push(new Rectangle(point1.x, point1.y, Math.abs(w), 1));
//                }
//            } else // if (w == 0)
//            {
//                if (h < 0) {
//                    halls.push(new Rectangle(point2.x, point2.y, 1, Math.abs(h)));
//                } else if (h > 0) {
//                    halls.push(new Rectangle(point1.x, point1.y, 1, Math.abs(h)));
//                }
//            }
//        }
//    }
//}
//
//}
