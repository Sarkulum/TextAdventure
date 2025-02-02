package map;

import player.Player;
import player.PlayerDecision;
import text.Colors;

import java.util.Objects;
import java.util.Scanner;

public class Empty1 {
    static Scanner scanner = new Scanner(System.in);
    public static void outsideHBF() {
        boolean firstVisit2 = true;
        Player player = Player.getPlayer("ID1");

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System:");
        System.out.println("The cold night air hits me as I step outside into the eerie silence.");
        System.out.println("The once bastling plaza in front of the station is now lifeless.");
        System.out.println("Ernst August Statue looms in the center,its bronze surface darkened \nwith something that look like ... dried blood.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System:");
        System.out.println("Broken bicycles and overturned trash cans are scattered across the \ncobblestones.");
        System.out.println("The faint hum and flickering  streetlights adds an ominous soundtrack\n to the stillness.");
        System.out.println("In the west across the street is the Ernst-August-Galerie – a shopping\n enter that used to be full of life.");
        System.out.println("Its glass entrance doors are shattered and inside,the dim glow of \nemergency lights casts long shadows on the tiled floors.");
        System.out.println("--------------------------->press enter to continue");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System:");
        System.out.println("1. Inspect the statue.");
        System.out.println("2. Investigate the bicycles");
        System.out.println("3. Enter Ernst-August-Galerie");
        System.out.println("------------------------------------------------------------------\n");
        
        int choice = PlayerDecision.inputWithCheck(3);
        
        if(choice == 1){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You approach the statue carefully.");
            System.out.println("On its pedestal, someone has carved the words:");
            System.out.println("'Follow the fog, trust the light.'");

            if(!Objects.equals(player.getPlayerWeapon(), "Crowbar")) {
                System.out.println("You also notice a crowbar leaning against the base of the statue.");
                System.out.println("System:");
                System.out.println("You have obtained weapon: 'Crowbar'");

                player.setPlayerWeapon("Crowbar");
            }
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();

            outsideHBF();
        } else if (choice == 2) {
            if(firstVisit2) {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("Rummaging through the broken bicycles,");
                System.out.println("you find a backpack with a half-full water bottle inside.");
                System.out.println("It’s stale but drinkable.");
                System.out.println("System:");
                System.out.println("You heal for " + Colors.GREEN + "1hp" + player.getUserTextColor());
                System.out.println("--------------------------->press enter to continue");
            }else{
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("You have already found everything here.");
                System.out.println("--------------------------->press enter to continue");
            }
            player.setCurrentHP(player.getCurrentHP()+1);
            scanner.nextLine();
            firstVisit2 = false;

            outsideHBF();
        } else if (choice == 3) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You step carefully through the shattered entrance of the Galerie.");
            System.out.println("The silence is almost deafening,");
            System.out.println("broken only by the occasional drip of water or the distant sound of ... something moving.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            eagGround();
        }
    }

    public static void eagGround() {

    }
}
