package OO.world;

import OO.enemy.Enemy;

import java.util.List;
import java.util.Scanner;

public class Room {
    private static final int PAUSE_EVERY_N_LINES = 3;
    private String roomName;
    private List<String> descriptionParts;
    private List<String> options; // List of Strings
    private List<Runnable> actions; // List of code
    private Room previousRoom;
    private Room nextRoom;

    public Room(String roomName, List<String> descriptionParts, List<String> options, List<Runnable> actions) {
        this.roomName = roomName;
        this.descriptionParts = descriptionParts;
        this.options = options;
        this.actions = actions;
        this.previousRoom = null;
        this.nextRoom = null;
    }

    public void enter() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- " + roomName + " ---");

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

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= actions.size()) {
            actions.get(choice - 1).run();
        } else {
            System.out.println("Invalid choice.");
            enter();
        }
    }

    public void reenter() {
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



    public Room getPreviousRoom() {return previousRoom;}
    public void setPreviousRoom(Room previousRoom) {this.previousRoom = previousRoom;}
}
