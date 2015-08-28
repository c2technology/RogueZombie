//package net.c2technology.roguezombie.old;
//
//import java.util.LinkedList;
//
///**
// *
// * @author cryan
// */
//public class Map {
//
//    private final int width;
//    private final int height;
//    private final int minRoomSize;
//    private final int maxRoomSize;
//
//    /**
//     * Generates a Map of {@code width} and {@code height} containing rooms
//     * bound to the limits of {@code minRoomSize} and {@code maxRoomSize}. The
//     * room size limit applies to both width and height.
//     *
//     * @param width width of the map
//     * @param height height of the map
//     * @param minRoomSize minimum width and height of any room
//     * @param maxRoomSize maximum width and height of any room
//     */
//    public Map(int width, int height, int minRoomSize, int maxRoomSize) {
//        this.width = width;
//        this.height = height;
//        this.minRoomSize = minRoomSize;
//        this.maxRoomSize = maxRoomSize;
//    }
//
//    public void generate() {
//        LinkedList<Node> nodes = new LinkedList();
//
//        // Create the one node to rule them all
//        Node dungeon = new Node(0, 0, width, height, minRoomSize, maxRoomSize);
//        nodes.push(dungeon);
//
//        boolean splitting = true;
//        /**
//         * Look at every node we have (starting with the root node) and split
//         * it, if possible. Each node split is then also processed for
//         * splitting. The end result is a List of all split Nodes.
//         */
//        while (splitting) {
//            //be optimistic about being done!
//            splitting = false;
//            for (Node room : nodes) {
//                if (!room.hasSplit() && room.canSplit()) {
//                    if (room.split()) {
//                        // if we did split, push all of the rooms into the list for further processing
//                        for (Node childRoom : room.getRooms()) {
//                            nodes.push(childRoom);
//                        }
//                        //Still splitting...
//                        splitting = true;
//                    }
//                }
//            }
//        }
//        // next, iterate through each Leaf and create a room in each one.
//        dungeon.createRooms();
//    }
//}
