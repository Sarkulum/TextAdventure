/*
Text Adventure
12.02.25
Version 0.0.2
Valerie, Ashley, Anna

Made for JDK 23
*/

import gameLogic.StoryGame;
import text.Colors;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("This is a little text based game made for school.");
        System.out.println(Colors.HIGH_PURPLE+"Code by Sarkulum aka. Valerie"+Colors.RESET);
        System.out.println(Colors.HIGH_RED+"Tutorial story and documentation by LeLean aka. Ashley"+Colors.RESET);
        System.out.println(Colors.BLUE+"Story for room 1/2 and puzzle by Anna"+Colors.RESET);
        System.out.println("--------------------------->press enter to continue\n");
        scanner.nextLine();

        StoryGame.initialSetUp();
    }
}