package world;

import player.PlayerManager;

import java.util.List;
import java.util.Scanner;

public class Room {
    private static final int PAUSE_EVERY_N_LINES = 3;
    private final Enum roomName;
    private final List<String> descriptionParts;
    private final List<String> options; // List of Strings
    private final List<Runnable> actions; // List of code
    private boolean isCombatRoom;

    public Room(Enum roomName, List<String> descriptionParts, List<String> options, List<Runnable> actions) {
        this.roomName = roomName;
        this.descriptionParts = descriptionParts;
        this.options = options;
        this.actions = actions;
        this.isCombatRoom = false;
    }

    public void enter() {
        // TODO find out what the fuck this is even used for
        PlayerManager.getInstance().getCurrentPlayer().setLastroom(this);
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- " + roomName.toString() + " ---");

        int lineCount = 0;
        for (String part : descriptionParts) {
            System.out.println(part);
            lineCount++;

            if (lineCount % PAUSE_EVERY_N_LINES == 0 || part.equals(descriptionParts.get(descriptionParts.size() - 1))) {
                System.out.println("---------------------------> press Enter to continue\n");
                scanner.nextLine(); // wait after every 3rd line
            }
        }

        System.out.println("\nOptions:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }
        System.out.println(" ");

        int choice = scanner.nextInt();

        System.out.println(" ");
        if (choice >= 1 && choice <= options.size() && !isCombatRoom) {
            actions.get(choice - 1).run();
        } else if (!isCombatRoom) {
            System.out.println("Invalid choice.");
            enter();
        }
    }

    public void reenter() {
        PlayerManager.getInstance().getCurrentPlayer().setLastroom(this);
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- " + roomName + " ---");

        System.out.println("\nOptions:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= actions.size()) {
            actions.get(choice - 1).run();
        } else {
            System.out.println("Invalid choice.");
            enter();
        }
    }

    public boolean isCombatRoom() {return isCombatRoom;}
    public void setCombatRoom(boolean combatRoom) {isCombatRoom = combatRoom;}
}
