package map;

import java.util.Scanner;

public class Intro {
    static Scanner scanner = new Scanner(System.in);

    public static void intro() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("I woke up in a pitch-black alleyway. My head is pounding, and I can’t remember how I got here.");
        System.out.println("I reach for my phone, but it’s dead. Great. Just great.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("The air is damp, filled with the distant hum of flickering streetlights.");
        System.out.println("Trash bins are overturned, and the stench of decay lingers.");
        System.out.println("As I push myself up, my eyes adjust to the dim surroundings.");
        System.out.println("The neon glow of Kröpke’s empty streets is visible ahead – but something feels off.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();
    }
}
