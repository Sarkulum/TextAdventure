package map;

import player.PlayerDecision;

import java.util.Scanner;

public class Puzzle {
    static Scanner scanner = new Scanner(System.in);
    static int choice;

    public static void intro() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You’ve carefully navigated the dark, eerie subway tunnel.");
        System.out.println("The guttural growls and faint echoes still make your heart race, but you’ve made it this far.");
        System.out.println(" Just as you think you’ve reached the next area, you stumble upon something unexpected.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("A large metal door blocks the way forward.");
        System.out.println("The door is covered in the same strange markings as the walls of the subway, symbols that seem ancient and ritualistic.");
        System.out.println("But at its center is something different—a mechanical panel with glowing letters.");
        System.out.println("As you approach, the panel comes to life, flickering dimly.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("A robotic voice speaks from a nearby speaker:");
        System.out.println("'Answer my riddle, and the path will open. Fail, and remain trapped in the shadows.'");
        System.out.println("The screen on the panel lights up, displaying the following.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();
        Puzzle.puzzle1();
    }

    public static void puzzle1() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("A ticket machine at Hannover Hauptbahnhof isn´t printing tickets.");
        System.out.println("Passengers are getting frustrated.");
        System.out.println("The screen displays an error:”Printer Connection Lost.");
        System.out.println("--------------------------->press enter to continue");

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You have the following choices:");
        System.out.println("1. Check if the printer cables are securely connected.");
        System.out.println("2.Reboot the entire ticket machine.");
        System.out.println("------------------------------------------------------------------\n");

        choice = PlayerDecision.inputWithCheck(2);

        if(choice == 1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Good Call!");
            System.out.println("You reconnect the loose printer cable and the machine starts working.");
            System.out.println("Passengers are happy!");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();

            puzzle2();
        }else if(choice == 2) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You restart the machine but the problem persists.");
            System.out.println("Looks like the issue wasn´t with the system itself.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            puzzle1();
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("IDK what you have done but that is not allowed.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            puzzle1();
        }
    }

    public static void puzzle2() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("A train is experiencing overcrowding and you need to determine whether it´s safe to allow more passengers onboard.");
        System.out.println("\nThe train has 8 cars,each with a maximum capacity of 100 passengers.");
        System.out.println("Currently,the train is carrying 650 passengers.\n");
        System.out.println("How many more passengers can safely board the train?");

        choice = PlayerDecision.inputWithCheck(999);

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        if(choice == 800 || choice == 150) {
            System.out.println("Good job! That was the right answer.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            puzzle3();
        }else {
            System.out.println("I am sorry but that answer is wrong please try again.");
            System.out.println("--------------------------->press enter to continue\n");

            puzzle2();
        }
    }

    public static void puzzle3() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("The departure  board at HBF is showing incorrect train times.");
        System.out.println("The train database seems out of sync with the display system.");
        System.out.println("--------------------------->press enter to continue\n");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You have the following options:");
        System.out.println("1. Synchronise the display system with the central database.");
        System.out.println("2. Restart the display system.");
        System.out.println("3. Manually update the train times on the display.");

        choice = PlayerDecision.inputWithCheck(3);

        if(choice == 1){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Success!");
            System.out.println("You synced the display system and the departure board now shows accurate train times");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
        }else if(choice == 2){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("The system reboots but the error remains.");
            System.out.println("Passengers are still confused.");
            System.out.println("Try again!!!");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            puzzle3();
        }else if(choice == 3){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("This would work temporarily but it´s not a sustainable solution");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            puzzle3();
        }
    }
}
