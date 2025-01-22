package player;

import text.Colores;

import java.util.Scanner;

public class UserData {
    // Takes user input for the player name that they want to us in the game.
    public String userName() {
        System.out.println("Please enter your player name you want to use during the game.");
        Scanner name = new Scanner(System.in);
        return name.nextLine();
    }

    // Function that takes user input for there age and then returns it.
    public int userAge() {
        System.out.println("Please enter your legal age.");
        Scanner age = new Scanner(System.in);
        return age.nextInt();
    }

    // Function that takes a user input for the color that the text is supposed to be and then returns a String based on that.
    public String textColor() {
        System.out.println("Please enter the color the text should be. You can chose between:");
        System.out.println(Colores.BLUE+ "Blue" +Colores.RESET);
        System.out.println(Colores.CYAN+ "Cyan" +Colores.RESET);
        System.out.println(Colores.PURPLE+ "Purple" +Colores.RESET);
        System.out.println(Colores.YELLOW+ "Yellow" +Colores.RESET);

        Scanner color = new Scanner(System.in);
        String input = color.nextLine();

        return switch (input) { //This looks scary but its basically just a simplified if else statement.
            case "Blue" -> Colores.BLUE;
            case "Cyan" -> Colores.CYAN;
            case "Purple" -> Colores.PURPLE;
            case "Yellow" -> Colores.YELLOW;
            default -> null;
        };
    }
}
