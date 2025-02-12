package rooms;

import combat.Attack;
import enemys.Enemy;
import enemys.ZombieTypes;
import map.GridMap;
import pathFinding.AStar;
import player.Player;
import player.PlayerDecision;
import text.Colors;

import java.util.Scanner;

public class Room2 {
    static Scanner scanner = new Scanner(System.in);
    static Player player = Player.getPlayer("ID1");
    static int choice;
    static GridMap gridMap = GridMap.makeMap(5);
    static AStar aStar = new AStar();

    public static void subwaySandwich() {
        if (player.getKey()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You find yourself standing in front of a old metal door.");
            System.out.println("\nOn the door you can make out the words:");
            System.out.println("'Forgotten Treasures.'\n");
            System.out.println("You use the Key you found to unlock the door.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You find yourself standing in front of a old metal door.");
            System.out.println("\nOn the door you can make out the words:");
            System.out.println("'Forgotten Treasures.'\n");
            System.out.println("You try to open the door but it does not move.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Suddenly you hear a roaring behind you.");
            System.out.println("As you turn around you see a dozen zombies walking towards you.");
            System.out.println("You get ready to fight ...");
            System.out.println("all of a sudden you feel something falling on your head as you fall to the ground and your vision fades to black.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                 You Died !!!                            ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------\n");

            Shop.buyUpgrades();
        }

        if (player.isFirstSandwich()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You step into what used to be a bustling subway tunnel.");
            System.out.println("The air here is colder and the faint sound of dripping water echoes all around you.");
            System.out.println("The tracks are covered in debris and the walls are adorned with strange symbols and phrases scrawled in what looks like chalk or ...");
            System.out.println("something darker!!!");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            player.setFirstSandwich(false);
        }

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("A broken vending machine flickers faintly in the corner.");
        if (!player.isZombieFought()) {
            System.out.println("Among the rubble, a faint sound catches my attention.");
            System.out.println("It´s a low, guttural growl, its close but I can´t tell from which direction it comes.");
        }
        System.out.println("1. Inspect the vending machine.");
        System.out.println("2. Investigate the makings on the wall");
        if (!player.isZombieFought()) {
            System.out.println("3. Follow the growl.");
        }
        System.out.println("4. Ignore the noise and move forward.");

        choice = PlayerDecision.inputWithCheck(4);

        if (choice == 1 && player.isSoda()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The vending machine looks barely functional, but there is a chance something useful could be inside.");
            System.out.println("You find an unopened can of soda.");
            System.out.println("System:");
            System.out.println("You "+Colors.GREEN+"heal 1 HP"+player.getUserTextColor()+".");
            System.out.println("--------------------------->press enter to continue\n");

            player.setCurrentHP(player.getCurrentHP() +1);
            scanner.nextLine();
            player.setSoda(false);
            subwaySandwich();
        } else if (choice == 1 && !player.isSoda()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The vending machine sparks and breaks completely.");
            System.out.println("Nothing else can be retrieved.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            subwaySandwich();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The symbols are strange,almost ritualistic.");
            System.out.println("You feel uneasy looking at them for too long.");
            System.out.println("\nOne of the phrases reads:");
            System.out.println("'The fog devours,but the light guides.'\n");
            System.out.println("Maybe this is a clue for what lies ahead.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
            subwaySandwich();
        } else if (choice == 3 && !player.isZombieFought()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You toughen up and follow the sound.");
            System.out.println("Suddenly, a zombie lunges at you from behind a pile of rubble!");
            System.out.println("--------------------------->press enter to continue\n");

            if (!player.isZombieCreated()) {
                ZombieTypes.createZombie("Ghoul", 0);
                player.setZombieCreated(true);
            }

            Enemy zombie = Enemy.getEnemy(0);
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Your " + Colors.GREEN + "HP: " + player.getCurrentHP() + player.getUserTextColor());
            System.out.println("Monster HP: " + zombie.getCurrentHP());
            System.out.println("1: Fight");
            System.out.println("2: Run");

            choice = PlayerDecision.inputWithCheck(2);

            if (choice == 1) {
                fight();
            } else if (choice == 2) {
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("You run like the little bitch you are!");
                System.out.println("--------------------------->press enter to continue\n");

                scanner.nextLine();
                subwaySandwich();
            }

        } else if (choice == 3 && player.isZombieFought()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You see the now even more lifeless body of the zombie.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
        } else if (choice == 4) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You decide not to risk whatever made that sound and move on.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
        }
    }
    
    public static void fight() {
        Enemy zombie = Enemy.getEnemy(0);
        Attack combat = Attack.getInstance();
        combat.setEnemy(zombie);

        if (!player.isZombieFought()) {
            while (Enemy.specificEnemyAlive(0)) {
                gridMap.printMap(gridMap.getRoomMap(), false);
                aStar.moveEnemyAStar(gridMap.getRoomMap(), zombie.getMovement(), zombie);
                combat.attackPlayer(gridMap);

                gridMap.printMap(gridMap.getRoomMap(), false);
                int[] playerMove = PlayerDecision.getPlayerInput();
                int[] playerLocation = aStar.findPlayer(gridMap.getRoomMap());
                aStar.movePlayer(gridMap.getRoomMap(), playerLocation[0], playerLocation[1], playerMove[0], playerMove[1], player.getMovementSpeed());
                gridMap.printMap(gridMap.getRoomMap(), true);

                // Just gets ignored because there is only 1 enemy anyway.
                Enemy enemy = PlayerDecision.pickEnemy();

                combat.attackEnemy(gridMap);
            }
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The zombie falls over and dies.");
            System.out.println("--------------------------->press enter to continue\n");

            scanner.nextLine();
        }
    }
}
