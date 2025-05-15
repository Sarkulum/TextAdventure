package logic;

import creator.PlayerCreator;
import player.PlayerManager;
import world.Room;
import world.World;

public class Game {
    private static Room currentRoom;
    private static PlayerCreator playerCreator;
    private static final PlayerManager playerManager = PlayerManager.getInstance();

    public static void start() {
        playerCreator = new PlayerCreator();
        playerManager.add(PlayerCreator.createPlayer());

        GameModeSelector gameModeSelector = new GameModeSelector();
    }

    public static void moveToRoom(Room nextRoom) {
        if (currentRoom != null && nextRoom != null) {
            nextRoom.setPreviousRoom(currentRoom); // Optional: Set where you came from
        }
        currentRoom = nextRoom;
        currentRoom.enter();
    }
}
