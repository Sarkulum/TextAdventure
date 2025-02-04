package map;

import combat.Attack;
import enemys.Enemy;
import enemys.ZombieTypes;
import player.Player;
import player.PlayerDecision;
import text.Colors;

import java.util.Scanner;

public class Room2 {
    static Scanner scanner = new Scanner(System.in);
    static Player player = Player.getPlayer("ID1");
    static int choice;
    static boolean soda = true;
    static boolean firstSandwich = true;
    static boolean zombieFought = false;
    static boolean zombieCreated = false;

    public static void subwaySandwich() {
        if (player.getKey()) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You find yourself standing in front of a old metal door.");
            System.out.println("On the door you can make out the words:");
            System.out.println("'Forgotten Treasures.'");
            System.out.println("You use the Key you found to unlock the door.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
        }else{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You find yourself standing in front of a old metal door.");
            System.out.println("On the door you can make out the words:");
            System.out.println("'Forgotten Treasures.'");
            System.out.println("You try to open the door but it does not move.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Suddenly you hear a roaring behind you.");
            System.out.println("As you turn around you see a dozen zombies walking towards you.");
            System.out.println("You get ready to fight ...");
            System.out.println("all of a sudden you feel something falling on your head as you fall to the ground and your vision fades to black.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("                          You Died !!!                            ");
            System.out.println("------------------------------------------------------------------\n");

            Shop.buyUpgrades();
        }

        if (firstSandwich) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You step into what used to be a bustling subway tunnel.");
            System.out.println("The air here is colder and the faint sound of dripping water echoes all around you.");
            System.out.println("The tracks are covered in debris and the walls are adorned with strange markings-symbols and phrases hastily scrawled in what looks like chalk or ...");
            System.out.println("something darker!!!");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            firstSandwich = false;
        }

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("A broken vending machine flickers faintly in the corner.");
        if (!zombieFought) {
            System.out.println("Among the rubble, a faint sound catches my attention.");
            System.out.println("It´s a low, guttural growl, its close but I can´t tell from which direction it comes.");
        }
        System.out.println("1. Inspect the vending machine.");
        System.out.println("2. Investigate the makings on the wall");
        if (!zombieFought) {
            System.out.println("3. Follow the growl.");
        }
        System.out.println("4. Ignore the noise and move forward.");

        choice = PlayerDecision.inputWithCheck(4);

        if (choice == 1 && soda) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("The vending machine looks barely functional, but there is a chance something useful could be inside.");
            System.out.println("You find an unopened can of soda.");
            System.out.println("System:");
            System.out.println("You "+Colors.GREEN+"heal 1 HP"+player.getUserTextColor()+".");
            System.out.println("--------------------------->press enter to continue");

            player.setCurrentHP(player.getCurrentHP() +1);
            scanner.nextLine();
            subwaySandwich();
        } else if (choice == 1 && !soda) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("The vending machine sparks and breaks completely.");
            System.out.println("Nothing else can be retrieved.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            subwaySandwich();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("The symbols are strange,almost ritualistic.");
            System.out.println("You feel uneasy looking at them for too long.");
            System.out.println("One of the phrases reads:");
            System.out.println("'The fog devours,but the light guides.'");
            System.out.println("Maybe this is a clue for what lies ahead.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
            subwaySandwich();
        } else if (choice == 3 && !zombieFought) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You toughen up and follow the sound.");
            System.out.println("Suddenly, a zombie lunges at you from behind a pile of rubble!");
            System.out.println("--------------------------->press enter to continue");
            
            scanner.nextLine();
        } else if (choice == 4) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You decide not to risk whatever made that sound and move on.");
            System.out.println("--------------------------->press enter to continue");

            scanner.nextLine();
        }
    }
    
    public void fight() {
        if (!zombieCreated) {
            ZombieTypes.createZombie("Ghoul", 0);
            zombieCreated = true;
        }
        Enemy zombie = Enemy.getEnemy(0);
        Attack combat = Attack.getInstance();
        combat.setEnemy(zombie);

        if (!zombieFought) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Your " + Colors.GREEN + "HP: " + player.getCurrentHP() + player.getUserTextColor());
            System.out.println("Monster HP: " + zombie.getCurrentHP());
            System.out.println("1: Attack");
            System.out.println("2: Run");

            choice = PlayerDecision.inputWithCheck(2);

            if (choice == 1) {
                combat.attackPlayer();
                combat.attackEnemy();
                if (!Enemy.specificEnemyAlive(0)){
                    zombieFought = true;
                }
                fight();
            } else if (choice == 2) {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("You run like the little bitch you are!");
                System.out.println("--------------------------->press enter to continue");

                scanner.nextLine();
                subwaySandwich();
            }
        }
    }
}
