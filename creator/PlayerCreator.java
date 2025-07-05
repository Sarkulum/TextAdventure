package creator;

import player.Player;
import text.TextColor;

import java.util.Scanner;

public class PlayerCreator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int minDamage = 5;
    private static final int maxDamage = 10;
    private static final int maxHealth = 20;
    private static final int movement = 3;
    private static final int range = 1;
    private static final int score = 0;


    public static Player createPlayer() {
        return new Player(name(), minDamage, maxDamage, maxHealth, movement, range, age(), selectTextColor(), score);
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

    private static TextColor selectTextColor() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter the color the text should be. You can choose between:\n");

        for (TextColor color : TextColor.values()) {
            System.out.println(color.toString()); // shows colorized name
        }

        System.out.println("--------------------------->enter a word to decide\n");

        while (true) {
            Scanner colorScanner = new Scanner(System.in);
            String input = colorScanner.nextLine().trim();

            for (TextColor color : TextColor.values()) {
                if (color.name().equalsIgnoreCase(input)) {
                    return color;
                }
            }

            System.out.println("Invalid color. Try again.");
        }
    }
}
