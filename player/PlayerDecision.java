package player;

import text.Colors;
import java.util.Objects;
import java.util.Scanner;

// Class for all the print statements that are needed to set up a player
public class PlayerDecision {
    static Scanner scanner = new Scanner(System.in);

    // Function to check if player choice is withing the possibility's and also if it is a number
    public static int inputWithCheck(int decisionRange) {
        int decisionValue;

        System.out.println("--------------------------->enter a number to decide\n");

        try {
            decisionValue = scanner.nextInt();
            if (decisionValue <= decisionRange){
                return decisionValue;
            }else{
                System.out.println("Please only enter a number within the decision range.");
            }
        } catch (Exception e) {
            System.out.println("Please only enter numbers.");
        }
        return 1;
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
            System.out.println(player.getUserTextColor());
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
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
        System.out.println(Colors.Gray + "Gray" + Colors.RESET);
        System.out.println("White");
        System.out.println("--------------------------->enter a word to decide\n");

        String input = scanner.nextLine();

        return switch (input) { //This looks scary but its basically just a simplified if else statement.
            case "Blue" -> Colors.BLUE;
            case "Cyan" -> Colors.CYAN;
            case "Purple" -> Colors.PURPLE;
            case "Gray" -> Colors.Gray;
            default -> Colors.RESET;
        };
    }
}
