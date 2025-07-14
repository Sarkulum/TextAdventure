package logic;

import enums.RoomNames;
import world.Room;
import world.World;
import java.util.Scanner;

public class GameModeSelector {
    public static Room room;
    public GameModeSelector() {
        while (true) {
            System.out.println("\nSelect a Game Mode:");
            for (int i = 0; i < GameMode.values().length; i++) {
                System.out.println((i + 1) + ". " + GameMode.values()[i].getDisplayName());
            }
            System.out.println(" ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt() -1;

            if (choice < 0 || choice >= GameMode.values().length +1) {
                System.out.println("Invalid Choice");
                System.out.println("Try again");
            }else{
                switch (GameMode.values()[choice]) {
                    case STORY:
                        World.initializeWorld();
                        room = World.getRoom(RoomNames.INTRODUCTION);
                        room.enter();
                        break;
                    case ENDLESS:
                        // handle endless mode
                        break;
                    case LOCAL_MULTIPLAYER:
                        // handle local multiplayer
                        break;
                }
            }
        }

    }
}
