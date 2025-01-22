package player;

import text.Colores;

import java.util.Scanner;

public class UserData {
    public String userName() {
        System.out.println("Please enter your player name you want to use during the game.");
        Scanner name = new Scanner(System.in);
        return name.nextLine();
    }

    public int userAge() {
        System.out.println("Please enter your legal age.");
        Scanner age = new Scanner(System.in);
        return age.nextInt();
    }

    public String textColor() {
        System.out.println("Please enter the color the text should be. You can chose between:");
        System.out.println(Colores.BLUE+ "Blue" +Colores.RESET);
        System.out.println(Colores.CYAN+ "Cyan" +Colores.RESET);
        System.out.println(Colores.PURPLE+ "Purple" +Colores.RESET);
        System.out.println(Colores.YELLOW+ "Yellow" +Colores.RESET);

        Scanner color = new Scanner(System.in);
        String input = color.nextLine();

        return switch (input) {
            case "Blue" -> Colores.BLUE;
            case "Cyan" -> Colores.CYAN;
            case "Purple" -> Colores.PURPLE;
            case "Yellow" -> Colores.YELLOW;
            default -> null;
        };
    }
}
