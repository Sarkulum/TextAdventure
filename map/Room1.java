package map;

import player.Player;
import player.PlayerDecision;
import text.Colors;

import java.util.Objects;
import java.util.Scanner;

public class Room1 {
    static Scanner scanner = new Scanner(System.in);
    static int choice;
    static Player player = Player.getPlayer("ID1");

    public static void outsideHBF() {
        if(player.isFirstVisit3()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The cold night air hits me as I step outside into the eerie silence.");
            System.out.println("The once bastling plaza in front of the station is now lifeless.");
            System.out.println("Ernst August Statue looms in the center,its bronze surface darkened with something that look like ... dried blood.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Broken bicycles and overturned trash cans are scattered across the cobblestones.");
            System.out.println("The faint hum and flickering  streetlights adds an ominous soundtrack to the stillness.");
            System.out.println("In the west across the street is the Ernst-August-Galerie a shopping enter that used to be full of life.");
            System.out.println("Its glass entrance doors are shattered and inside,the dim glow of emergency lights casts long shadows on the tiled floors.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            player.setFirstVisit3(false);
        }

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System:");
        System.out.println("1. Inspect the statue.");
        System.out.println("2. Investigate the bicycles");
        System.out.println("3. Enter Ernst-August-Galerie");
        
        int choice = PlayerDecision.inputWithCheck(3);
        
        if(choice == 1){
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You approach the statue carefully.");
            System.out.println("\nOn its pedestal, someone has carved the words:");
            System.out.println("'Follow the fog, trust the light.'\n");

            if(!Objects.equals(player.getPlayerWeapon(), "Crowbar")) {
                System.out.println("You also notice "+Colors.HIGH_RED+"a crowbar"+player.getUserTextColor()+" leaning against the base of the statue.");
                System.out.println("System:");
                System.out.println("You have obtained weapon: 'Crowbar'");
                System.out.println("It is not noticeably better than the kitchen knife.");

                player.setPlayerWeapon("Crowbar");
            }
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            outsideHBF();
        } else if (choice == 2) {
            if(player.isFirstVisit2()) {
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Rummaging through the broken bicycles,");
                System.out.println("you find a backpack with a half-full water bottle inside.");
                System.out.println("It's stale but drinkable.");
                System.out.println("\nSystem:");
                System.out.println("You heal for " + Colors.GREEN + "1hp" + player.getUserTextColor());
                System.out.println("--------------------------->press enter to continue\n");
                if (player.getCurrentHP() < player.getMaxHP()) {
                    player.setCurrentHP(player.getCurrentHP() + 1);
                }
            }else{
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("You have already found everything here.");
                System.out.println("--------------------------->press enter to continue\n");
            }
            scanner.nextLine();
            player.setFirstVisit2(false);

            outsideHBF();
        } else if (choice == 3) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You step carefully through the shattered entrance of the Galerie.");
            System.out.println("The silence is almost deafening,");
            System.out.println("broken only by the occasional drip of water or the distant sound of ... something moving.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            eagGround();
        }
    }

    public static void eagGround() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("The ground floor of Ernst-August-Galerie is a desolate ruin. ");
        System.out.println("Broken storefronts, shattered glass, and the faint stench of decay fill the air.");
        System.out.println("Emergency lights flicker above, casting unsettling shadows across the debris-covered floor.");
        System.out.println("Among the chaos, you notice a strange trail,");
        System.out.println("a faint smear of red leading from the Food Court toward what looks like a service door at the far end of the mall.");
        System.out.println("--------------------------->press enter to continue\n");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System:");
        System.out.println("1. Investigate the service door");
        System.out.println("2. Search the fashion section");
        System.out.println("3. Explore the Food Court");
        System.out.println("4. Return to the plaza outside Hauptbahnhof");

        choice = PlayerDecision.inputWithCheck(4);

        if (choice == 1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You follow the trail to the service door.");
            System.out.println("The door is looked.");

            if (Objects.equals(player.getPlayerWeapon(), "Crowbar") && player.isFirstVisitMedKit()) {
                System.out.println("But you are able to open it using the crowbar you picked up.");
                System.out.println("Inside you find a supply room with a MedKit.");
                System.out.println("System:");
                System.out.println("You "+Colors.GREEN+"heal to full health"+player.getUserTextColor()+".");

                player.setCurrentHP(player.getMaxHP());

                player.setFirstVisitMedKit(false);
            }
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            eagGround();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The Fashion section is a mess, with clothes scattered everywhere.");

            if (player.isFirstVisitBoots()) {
                System.out.println("Among the wreckage, you find a pair of sturdy boots.");
                System.out.println("They're a bit too big, but they'll do.");
                System.out.println("System:");
                System.out.println("You can now take more damage("+Colors.GREEN+"+1 max hp"+player.getUserTextColor()+").");

                if (player.getCurrentHP() == player.getMaxHP()) {
                    player.setCurrentHP(player.getCurrentHP() +1);
                }
                player.setMaxHP(player.getMaxHP() +1);

                player.setFirstVisitBoots(false);
            }

            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            eagGround();
        } else if (choice == 3) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The Food Court is eerily silent.");

            if (player.isFirstVisitFood()) {
                System.out.println("Most of the food has spoiled, but behind an overturned kiosk, you find an unopened bottle of soda.");
                System.out.println("System:");
                System.out.println("You "+Colors.GREEN+"heal for +1hp"+player.getUserTextColor()+".");

                if (player.getCurrentHP() < player.getMaxHP()) {
                    player.setCurrentHP(player.getCurrentHP() +1);
                }

                player.setFirstVisitFood(false);
            }

            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            eagGround();
        } else if (choice == 4) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You decide you've seen enough of the mall for now.");
            System.out.println("You head back out into the cold night air.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            eaStatue();
        }
    }

    public static void eaStatue() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You return to the statue of Ernst-August.");
        System.out.println("The shadows seem darker now, and the once-silent plaza feels... alive, as if something is watching you.");
        System.out.println("You notice a faint glimmer in the base of the statue, something you didn't see before.");
        System.out.println("--------------------------->press enter to continue\n");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("As you get closer, you realize there's a small hidden compartment built into the pedestal.");
        System.out.println("A carved inscription reads: 'Seek and you shall find.'");
        System.out.println("--------------------------->press enter to continue\n");

        scanner.nextLine();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System:");
        if (!player.getKey()) {
            System.out.println("1. Inspect the statue again");
            System.out.println("2. Open the hidden compartment");
            System.out.println("3. Enter the HBF");
        }else{
            System.out.println("1. Inspect the statue again");
            System.out.println("2. Enter the HBF");
        }

        choice = PlayerDecision.inputWithCheck(3);

        if (choice == 2 && !player.getKey()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Inside the compartment, you find "+Colors.HIGH_RED+"a small key"+player.getUserTextColor()+" with a tag attached.");
            System.out.println("The tag reads: 'Forgotten Treasures.'");
            System.out.println("You feel a chill run down your spine as you realize this must be the key to something nearby.");
            System.out.println("--------------------------->press enter to continue\n");

            player.setKey(true);
            scanner.nextLine();
            eaStatue();
        } else if (choice == 1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You carefully examine the statue once more.");
            System.out.println("Aside from the strange writing and the hidden compartment, nothing else seems unusual.");
            System.out.println("Whatever ‘Forgotten Treasures' means, it's not here.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            eaStatue();
        } else if (choice == 3) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You enter the HBF and think:");
            System.out.println("'Maybe you can escape using the U-Bahn...'");
            System.out.println("Before you can finish your thought you see a Person behind a makeshift counter and go towards them.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
        }
    }
}
