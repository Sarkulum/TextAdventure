package rooms;

import combat.Attack;
import enemys.ZombieTypes;
import items.Weapon;
import map.GridMap;
import pathFinding.AStar;
import player.Player;
import player.PlayerDecision;
import enemys.Enemy;
import text.Colors;
import java.util.Scanner;

public class Tutorial {
    static Scanner enterScanner = new Scanner(System.in); // Scanner to check if the player wants to proceed.
    static Enemy firstEnemy = Enemy.getEnemy(ZombieTypes.createZombie("Shambler", 0)); // Make an enemy
    static Player player = Player.getPlayer("ID1");
    static int choice; // Int for player choice.
    static GridMap gridMap = GridMap.makeMap(5); // Make the map used in the kiosk fight
    static AStar aStar =  new AStar(); // Make a new pathfinding instance

    // Starting room.
    public static void townGate() {
        System.out.println(player.userTextColor);
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("A thick, smoky wall blocks a narrow passage leading further into the city.");
        System.out.println("A lone figure sits in front of it, casually exhaling smoke into the already heavy air.");
        System.out.println("\nWhat will you do?\n");
        System.out.println("1. Talk to the person");
        System.out.println("2. Smack them");
        System.out.println("3. Do nothing");
        if (player.isMission()) {
            System.out.println("4. Go north to the crossroad");
        }

        choice = PlayerDecision.inputWithCheck(4);

        if(choice == 1) {
            if (player.getSilverRing() || player.DEV()) {
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Person:");
                System.out.println("'Thanks a lot, mate. Here, now you can go further.'\n");
                System.out.println("The person inhales the smoke as if it is nothing, and the smoky wall dissipates.");
                System.out.println("--------------------------->press enter to continue\n");

                player.setTutorialPassed(true);
                enterScanner.nextLine();
            }else {
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Person:");
                System.out.println("'Welcome, nice to see another survivor.'");
                System.out.println("If you bring me a pack of cigarettes, "+player.getUserName()+", I'll let you through that smoky wall.");
                System.out.println("--------------------------->press enter to continue\n");

                player.setMission(true);
                enterScanner.nextLine();
                townGate();
            }
        }else if (choice == 2) {
            if (player.getCurrentHP() > 1) {
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Person: 'Hey what's wrong with you?'");
                System.out.println("The person bonks you on the head.");
                System.out.println("For some reason, you feel like picking a fight isn't the best idea.");
                System.out.println("\nSystem:");
                System.out.println("You receive " + Colors.RED + "1 damage" + player.getUserTextColor() + ".");
                player.setCurrentHP(player.getCurrentHP() - 1);
                System.out.println("Your" + Colors.GREEN + " HP: " + player.getCurrentHP() + player.getUserTextColor());
                System.out.println("--------------------------->press enter to continue\n");

                enterScanner.nextLine();
                townGate();
            }else{
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Person: 'Hey what's wrong with you?'");
                System.out.println("The person bonks you on the head.");
                System.out.println("For some reason, you feel like picking a fight isn't the best idea.");
                System.out.println("\nSystem:");
                System.out.println("You receive " + Colors.RED + "1 damage" + player.getUserTextColor() + ".\n");
                System.out.println("You have died to the Person. You will now respawn.");
                System.out.println("--------------------------->press enter to continue\n");

                enterScanner.nextLine();
                player.setCurrentHP(player.getMaxHP());
                townGate();
            }
        }else if (choice == 4 && player.isMission()){
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You decide to leave the smoky wall and the strange person behind, heading toward Kröpke.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            crossRoad();
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You stand there awkwardly, but the person ignores you.");
            System.out.println("Nothing happens.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            townGate();
        }
    }

    // Kröpke crossRoad
    public static void crossRoad(){
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You stand in the heart of the city, but it feels nothing like it used to.");
        System.out.println("Once a bustling square filled with life, Kröpke is now eerily silent.\n");
        System.out.println("Four paths lie before you:");
        System.out.println("1: Go north (To the abandoned pharmacy.)");
        System.out.println("2: Go east (To the old fast-food stand.)");
        System.out.println("3: Go south (To the smoky wall.)");
        System.out.println("4: Go west (To the abandoned kiosk.)");

        choice = PlayerDecision.inputWithCheck(4);

        if(choice==1){
            river();
        }else if(choice==2){
            forest();
        }else if(choice==3){
            townGate();
        }else if(choice==4){
            goblinCave();
        }else if(choice == 0){
            crossRoad();
        }
    }

    // Name river from old version of the program. Is now a pharmacy
    public static void river(){
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You step into what used to be a pharmacy.");
        System.out.println("The shelves are mostly empty, some toppled over, and shattered pill bottles crunch under your feet.");
        System.out.println("A faint smell of disinfectant lingers in the air.");
        System.out.println("The place has been ransacked, but maybe there's still something useful left.\n");
        System.out.println("1. Look around the pharmacy.");
        System.out.println("2. Leave the pharmacy.");
        System.out.println("3. Check behind the counter.");

        choice = PlayerDecision.inputWithCheck(3);

        if (choice == 1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You take a moment to examine the ruined pharmacy.");
            System.out.println("Broken shelves, dried bloodstains on the floor, and a faint buzzing sound from a flickering light overhead.");
            System.out.println("You wonder who came here before you and if they made it out alive.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            river();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You step outside, ready to move on.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            crossRoad();
        } else if (choice == 3 && player.isMedKit()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You spot an old first-aid kit behind the counter.");
            System.out.println("Some of the items inside are still usable.");
            System.out.println("You patch yourself up as best as you can.");
            System.out.println("Your "+Colors.GREEN+"HP"+player.getUserTextColor()+" have recovered.");

            player.setCurrentHP(player.getMaxHP());
            player.setMedKit(false);

            System.out.println("Your "+Colors.GREEN+"HP: " + player.getCurrentHP()+player.getUserTextColor());
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            river();
        } else if (choice == 3 && !player.isMedKit()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You glance behind the counter, but the first-aid kit is empty.");
            System.out.println("No more supplies left.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            river();
        }
    }

    // Still named forest from old version of the program. Is now a fast-food stand
    public static void forest(){
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("You enter what used to be a small fast-food stand.");
        System.out.println("The air is stale, and the floor is sticky with old grease.");
        System.out.println("Chairs are knocked over, ketchup stains cover the counter, and a rotten burger sits half-eaten on a tray.");
        System.out.println("The smell of decay lingers.\n");
        System.out.println("1. Look around the stand.");
        System.out.println("2. Eat the rotten burger.");
        System.out.println("3. Leave the fast-food stand and return to Kröpke.");

        choice = PlayerDecision.inputWithCheck(3);

        if (choice == 1 && !Weapon.hasWeapon("Knife")) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You step behind the counter, searching for anything useful.");
            System.out.println("As you rummage through a drawer, your fingers touch something cold and metallic ...");
            System.out.println("a kitchen knife!!!");
            System.out.println("It's not in the best condition, but it's better than nothing.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("System:");
            System.out.println("You replaced your weapon 'Fist' with 'Knife'("+Colors.RED+"+1 min damage"+player.getUserTextColor()+" & "+Colors.RED+"+5 max damage"+player.getUserTextColor()+")");
            System.out.println("--------------------------->press enter to continue\n");

            Weapon.creatWeapon("Knife", 0, 1, 5, 1);
            Weapon.equipWeapon("Knife");
            enterScanner.nextLine();
            forest();
        } else if (choice == 1 && Weapon.hasWeapon("Knife")) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You already searched here. Nothing else useful remains.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            forest();
        } else if (choice == 2 && !player.isBurgerEaten() && player.getCurrentHP() > 1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You pick up the rotten burger, your stomach turning as you take a bite.");
            System.out.println("It tastes awful, and something feels wrong.");
            System.out.println("You feel your stomach churn, and your head spins.\n");
            System.out.println("System:");
            System.out.println("You "+Colors.RED+"lose 1 HP"+player.getUserTextColor()+".");
            System.out.println("--------------------------->press enter to continue\n");

            player.setCurrentHP(player.getCurrentHP() -1);
            player.setBurgerEaten(true);
            enterScanner.nextLine();
            forest();
        } else if (choice == 2 && player.isBurgerEaten() && player.getCurrentHP() > 1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Except for the burger you have foolishly eaten there is nothing else here.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            forest();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You feel your stomach churn, and your head spins as you fall over and you vision turns black.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            player.setBurgerEaten(true);
            player.setCurrentHP(player.getMaxHP());

        } else if (choice == 3) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You turn away from the abandoned stand and head back to Kröpke.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            crossRoad();
        }
    }

    // Still named goblinCave from old version. Is now a Kiosk, but I still contains the fight
    public static void goblinCave(){
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");

        if (!player.getSilverRing()) {
            System.out.println("You step into a ransacked kiosk. Shelves are toppled, shattered glass crunches underfoot, and the air reeks of stale beer and decay.");
            System.out.println("Behind the counter, a hunched figure twitches.");
            System.out.println("Once a shopkeeper, now a zombie.");
            System.out.println("Its head jerks toward you, and with a guttural growl it lunges!\n");
            System.out.println("1. Fight the kiosk zombie.");
            System.out.println("2. Run away");
        }else{
            System.out.println("1. Look around the kiosk.");
            System.out.println("2. Leave the kiosk and return to Kröpke.");

        }
        choice = PlayerDecision.inputWithCheck(2);

        if (choice == 1 && !player.getSilverRing()) {
            buildMapKiosk();
            fight();
        } else if (choice == 1 && player.getSilverRing()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Now that the zombie is no longer a threat, you take a moment to search the kiosk.");
            System.out.println("But you can't find anything of interest.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            goblinCave();
        } else if (choice == 2 && !player.getSilverRing()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Panic takes over, and you sprint back to Kröpke.");
            System.out.println("The zombie snarls but doesn't chase you.");
            System.out.println("The kiosk remains dangerous.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            crossRoad();
        } else if (choice == 2 && player.getSilverRing()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You step over the body and make your way back to Kröpke, the pack of cigarettes tucked safely in your pocket.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            crossRoad();
        }
    }

    // Methode for the combat with the zombie in goblinCave
    public static void fight(){

        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        // Set goblin as enemy
        combat.setEnemy(firstEnemy);

        if(Enemy.specificEnemyAlive(0)) {
            while (Enemy.specificEnemyAlive(0)) {
                gridMap.printMap(false);
                aStar.moveEnemyAStar(gridMap.getRoomMap(), firstEnemy.getMovement(), firstEnemy);
                combat.attackPlayer(gridMap);

                gridMap.printMap(false);
                int[] playerMove = PlayerDecision.getPlayerInput();
                int[] playerLocation = aStar.findPlayer(gridMap.getRoomMap());
                aStar.movePlayer(gridMap.getRoomMap(), playerLocation[0], playerLocation[1], playerMove[0], playerMove[1], player.getMovementSpeed());
                gridMap.printMap(true);

                // Just gets ignored because there is only 1 enemy anyway.
                PlayerDecision.pickEnemy();

                combat.attackEnemy(gridMap);
            }
            fight();
        }else{
            player.setSilverRing(true);
            String[][] map = gridMap.getRoomMap();
            gridMap.cleanMap(map.length);
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The zombie gurgles one last time before collapsing:");
            System.out.println("'H-heute ... nur Malboro im Angebot ... '");
            System.out.println("As it twitches on the floor, something falls from its pocket ...");
            System.out.println(Colors.HIGH_RED+"a pack of cigarettes!!!"+player.getUserTextColor());
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            goblinCave();
        }
        /*This is the map of the kiosk
         1  2  3  4  5
        [ ][ ][ ][E][ ] 1
        [ ][ ][#][#][#] 2
        [ ][ ][ ][ ][ ] 3
        [ ][ ][ ][ ][ ] 4
        [ ][ ][P][ ][ ] 5
         */
    }

    // Method to make the map for fight and place Obstetrical
    public static void buildMapKiosk() {
        gridMap.placeEnemy(0, 3, firstEnemy);
        gridMap.placeObstical(1,4);
        gridMap.placeObstical(1,3);
        gridMap.placeObstical(1,2);
        gridMap.placePlayer(4, 2);

        // Initialize the Attack singleton with the player
        Attack.initialize(player);
    }
}
