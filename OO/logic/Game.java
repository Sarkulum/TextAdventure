package OO.logic;

import OO.world.Room;

public class Game {
    private static Room currentRoom;

    public static void start(Room startingRoom) {
        currentRoom = startingRoom;
        currentRoom.enter();
    }

    public static void moveToRoom(Room nextRoom) {
        if (currentRoom != null && nextRoom != null) {
            nextRoom.setPreviousRoom(currentRoom); // Optional: Set where you came from
        }
        currentRoom = nextRoom;
        currentRoom.enter();
    }
}
