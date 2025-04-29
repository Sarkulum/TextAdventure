package OO.creator;

import OO.player.Player;

import java.util.Scanner;

public class PlayerCreator {
    private static final Scanner scanner = new Scanner(System.in);

    public static Player createPlayer() {




    }

    private static String name() {

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter the player name you want to use during the game.");
        System.out.println("----------------------------------------------------------------->enter a word\n");

        return scanner.nextLine().trim();
    }

    private static int age(){
        int age;

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter your legal age.");
        System.out.println("--------------------------->enter a number to decide\n");

        while (true) {
            try {
                age = scanner.nextInt();
                if (age <= 120) {
                    return age;
                } else {
                    System.out.println("Please only enter a number within the decision range.\n");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("Please only enter numbers.\n");
                scanner.next();
            }
        }
    }

    private static
}
