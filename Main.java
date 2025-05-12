import logic.Game;
import text.TextColor;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("This is a little text based game made for school.");
        System.out.println(TextColor.HIGH_PURPLE.getAnsiCode()+"Code by Sarkulum aka. Valerie"+TextColor.RESET.getAnsiCode());
        System.out.println(TextColor.HIGH_RED.getAnsiCode()+"Tutorial story and documentation by LeLean aka. Ashley"+TextColor.RESET.getAnsiCode());
        System.out.println(TextColor.BLUE.getAnsiCode()+"Story for room 1/2 and puzzle by Anna"+TextColor.RESET.getAnsiCode());
        System.out.println("--------------------------->press enter to continue\n");
        scanner.nextLine();

        Game game = new Game();
        game.start();
    }
}