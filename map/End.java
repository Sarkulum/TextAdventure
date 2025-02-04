package map;

import gameLogic.StoryGame;
import player.Player;
import player.PlayerDecision;
import player.Score;

import java.util.Scanner;

public class End {
    static Scanner scanner = new Scanner(System.in);
    static Player player = Player.getPlayer("ID1");
    static int choice;

    public static void emptyEnd() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("After solving the riddle you step forward into what looks like an abandoned platform—a forgotten stop on an underground rail line.");
        System.out.println("The air is thick with dust, and the faint sound of dripping water echoes around you.");
        System.out.println("Old posters and signs in faded German lettering cling to the walls, their colors long since eroded by time.");
        System.out.println("Above, a single flickering sign reads: ‘Zug Ankunft – 2 Minuten’ (Train Arrival – 2 Minutes).");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("As you look around, you notice that the tracks ahead vanish into a pitch-black tunnel.");
        System.out.println("The faint rumble of something massive begins to vibrate through the ground.");
        System.out.println("A distant light appears, growing brighter with every second.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("The silence is broken by the unmistakable sound of screeching metal and the low hum of a train approaching.");
        System.out.println("But ... how?");
        System.out.println("The station looked abandoned, the world in ruins.");
        System.out.println("What train could still be running?");
        System.out.println("And who ... or what is on board?");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("The train bursts from the darkness, its headlights cutting through the gloom.");
        System.out.println("It screeches to a halt in front of you, its exterior battered and rusted but still intact.");
        System.out.println("The lights inside flicker eerily, casting strange shadows across the platform.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("The train’s doors slide open with a loud hiss, inviting you aboard.");
        System.out.println("A cold wind rushes out from inside, carrying with it the faint scent of oil and ... something else.");
        System.out.println("Something you can’t quite place.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("A mechanical voice crackles over the intercom:");
        System.out.println("'Endstation. Bitte nicht einsteigen. (Final stop. Please don't board.'");
        System.out.println("\n You realize this is the end of the line, literally and figuratively.");
        System.out.println("The train seems to be your only way out of this nightmare—or perhaps the beginning of a new one.");
        System.out.println("You step closer, hesitating as the rumble of the train reverberates through your chest.");
        System.out.println("1. Board the train.");
        System.out.println("2. Stay behind.");

        choice = PlayerDecision.inputWithCheck(2);

        if (choice == 1) {
            Score.savePlayer();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You take a deep breath and step onto the train.");
            System.out.println("The doors close behind you with a deafening clang.");
            System.out.println("The train jolts forward, plunging into the darkness of the tunnel.");
            System.out.println("You grip a nearby pole for balance as the flickering lights illuminate the empty seats around you.");
            System.out.println("The intercom crackles again, the voice speaking one last cryptic phrase:");
            System.out.println("\n'Zurücksetzen der Welt. Reise abgeschlossen. (Resetting the world. Journey complete.)'");
            System.out.println("\nThe train picks up speed, and the world outside the windows becomes a blur of light and shadow.");
            System.out.println("Whatever lies ahead, you’ll soon find out.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            StoryGame.StartGame();
        } else if (choice == 2) {
            Score.savePlayer();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You step back from the train, shaking your head.");
            System.out.println("Whatever is on that train, it doesn’t feel safe.");
            System.out.println("The doors close with a loud hiss, and the train pulls away, its headlights disappearing into the darkness of the tunnel.");
            System.out.println("Silence falls over the platform, and you turn away, trying to figure out your next move.");
            System.out.println("But then you hear it—a low, guttural growl, followed by the shuffling of footsteps.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You whip around, your flashlight beam cutting through the gloom.");
            System.out.println("Dozens of figures emerge from the shadows, their eyes gleaming with a feral hunger.");
            System.out.println("The zombies have found you.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You grab your "+player.getPlayerWeapon()+", your breath quickening.");
            System.out.println("It’s no use ... they’re too many.");
            System.out.println("You swing once, twice, but the overwhelming horde drags you down.");
            System.out.println("Your screams echo through the station, drowned out by the sound of tearing flesh.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();


            System.out.println("\n------------------------------------------------------------------");
            System.out.println("                          You Died !!!                            ");
            System.out.println("                            The End                               \n");
            System.out.println("                          Presented by:                           ");
            System.out.println("                            Valerie                               ");
            System.out.println("                              Anna                                ");
            System.out.println("                             Ashley                               ");
            System.out.println("------------------------------------------------------------------\n");
        }
    }
}
