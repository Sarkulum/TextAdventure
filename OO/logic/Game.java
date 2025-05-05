package OO.logic;

import OO.creator.PlayerCreator;
import OO.player.PlayerManager;
import OO.world.Room;
import OO.world.World;

public class Game {
    private static Room currentRoom;
    private static PlayerCreator playerCreator;
    private static PlayerManager playerManager = PlayerManager.getInstance();;


    public static void start() {
        playerCreator = new PlayerCreator();
        playerManager.add(playerCreator.createPlayer());

        World.initializeWorld();
        currentRoom = World.getRoom("Intro");
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
