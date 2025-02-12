package player;

import enemys.Enemy;
import text.Colors;
import java.util.Objects;
import java.util.Scanner;

// Class for all the print statements that are needed to set up a player
public class PlayerDecision {
    static Scanner scanner = new Scanner(System.in);
    String input;

    // Function to check if player choice is withing the possibility's and also if it is a number
    public static int inputWithCheck(int decisionRange) {
        int decisionValue;

        System.out.println("--------------------------->enter a number to decide\n");
        while (true) {
            try {
                decisionValue = scanner.nextInt();
                if (decisionValue <= decisionRange) {
                    return decisionValue;
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

    // Setter using scanner to set username.
    public String setUserName() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter the player name you want to use during the game.");
        System.out.println("----------------------------------------------------------------->enter a word\n");
        Scanner name = new Scanner(System.in);

        return name.nextLine();
    }

    public boolean DEV() {
        Player player = Player.getPlayer("ID1");
        if(Objects.equals(player.getUserName(), "DEV")){
            System.out.println(player.getUserTextColor()+"\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Welcome DEV you now have the power to destroy.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            return true;
        }
        return false;
    }

    // Setter for user Age.
    public int setUserAge() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter your legal age.");

        return PlayerDecision.inputWithCheck(120);
    }

    // Setter using scanner to set text color.
    public String setTextColor() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter the color the text should be. You can chose between:");
        System.out.println(Colors.BLUE+ "Blue" + Colors.RESET);
        System.out.println(Colors.CYAN+ "Cyan" + Colors.RESET);
        System.out.println(Colors.PURPLE+ "Purple" + Colors.RESET);
        System.out.println(Colors.GRAY + "Gray" + Colors.RESET);
        System.out.println(Colors.HIGH_PURPLE+ "High Purple" +Colors.RESET);
        System.out.println("White");
        System.out.println("--------------------------->enter a word to decide\n");

        scanner.nextLine(); // Consume leftover newline from previous input
        input = scanner.nextLine(); // Now correctly captures user input

        return switch (input) { //This looks scary but its basically just a simplified if else statement.
            case "Blue" -> Colors.BLUE;
            case "Cyan" -> Colors.CYAN;
            case "Purple" -> Colors.PURPLE;
            case "Gray" -> Colors.GRAY;
            case "High Purple" -> Colors.HIGH_PURPLE;
            default -> Colors.RESET;
        };
    }

    // Methode to get player input to move
    public static int[] getPlayerInput() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("You can now input a location you would like to move to.\n");
        while (true) {
            try {
                System.out.println("Please enter a target row:\n");
                int targetRow = scanner.nextInt();

                System.out.println("\nPlease enter a target col:\n");
                int targetCol = scanner.nextInt();

                return new int[]{targetRow, targetCol};
            }catch (Exception e) {
                System.out.println("Please only enter numbers.\n");
                scanner.next();
            }
        }
    }

    public static Enemy pickEnemy() {
        boolean validEnemy = false;
        System.out.println("--------------------------->enter a number to decide\n");
        while (true){
            try {
                Enemy enemy = Enemy.getEnemy(scanner.nextInt());

                if (enemy != null && enemy.getCurrentHP() > 0) {
                    return enemy;
                }
                System.out.println("That was a invalid input. Please enter a Enemy that exists and is Alive.");
            } catch (Exception e) {
                System.out.println("Please only enter numbers.\n");
                scanner.next();
            }
        }
    }
}
