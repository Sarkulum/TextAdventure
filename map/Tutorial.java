package map;

import combat.Attack;
import enemys.ZombieTypes;
import player.Player;
import player.PlayerDecision;
import enemys.Enemy;
import text.Colors;

import java.util.Objects;
import java.util.Scanner;

public class Tutorial {
    static Scanner enterScanner = new Scanner(System.in); // Scanner to check if the player wants to proceed.
    public static int choice; // Int for player choice.
    static Enemy firstEnemy = Enemy.getEnemy(ZombieTypes.createZombie("Shambler", 0)); // Make an enemy
    public static boolean mission = false;
    public static boolean medKit = true;

    // Starting room.
    public static void townGate() {
        Player player = Player.getPlayer("ID1");
        System.out.println(player.userTextColor+ "\n------------------------------------------------------------------");
        System.out.println("A thick, smoky wall blocks a narrow passage leading further into the city.");
        System.out.println("A lone figure sits in front of it, casually exhaling smoke into the already heavy air.");
        System.out.println("\nWhat will you do?\n");
        System.out.println("1. Talk to the person");
        System.out.println("2. Smack them");
        System.out.println("3. Do nothing");
        if (mission) {
            System.out.println("4. Go north to the crossroad");
        }
        System.out.println("--------------------------->enter a number to decide");

        choice = PlayerDecision.inputWithCheck(4);

        if(choice == 1) {
            if (player.getSilverRing() || player.DEV()) {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("Person: 'Thanks a lot, mate. Here, now you can go further.'");
                System.out.println("The person inhales the smoke as if it is nothing, and the smoky wall dissipates.");
                System.out.println("--------------------------->press enter to continue");

                enterScanner.nextLine();
            }else {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("Person: Welcome, nice to see another survivor.");
                System.out.println("If you bring me a pack of cigarettes, "+player.getUserName()+", I’ll let you through that smoky wall.");
                System.out.println("--------------------------->press enter to continue");

                mission = true;
                enterScanner.nextLine();
                townGate();
            }
        }else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Person: 'Hey what’s wrong with you?'");
            System.out.println("The person bonks you on the head.");
            System.out.println("For some reason, you feel like picking a fight isn’t the best idea.");
            System.out.println("\nSystem:");
            System.out.println("(You receive "+Colors.RED+"1 damage"+ player.getUserTextColor()+".");
            System.out.println("Your"+Colors.GREEN+" HP: " + player.getCurrentHP() + player.getUserTextColor());
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            townGate();
        }else if (choice == 4 && mission){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You decide to leave the smoky wall and the strange person behind, heading toward Kröpke.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            crossRoad();
        }else{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You stand there awkwardly, but the person ignores you.");
            System.out.println("Nothing happens.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
        }
    }

    public static void crossRoad(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You stand in the heart of the city, but it feels nothing like it used to.");
        System.out.println("Once a bustling square filled with life, Kröpke is now eerily silent.");
        System.out.println("Four paths lie before you:");
        System.out.println("1: Go north (To the abandoned pharmacy.)");
        System.out.println("2: Go east (To the old fast-food stand.)");
        System.out.println("3: Go south (To the smoky wall.)");
        System.out.println("4: Go west (To the abandoned kiosk.)");
        System.out.println("--------------------------->enter a number to decide");

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

    public static void river(){
        Player player = Player.getPlayer("ID1");

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You step into what used to be a pharmacy.");
        System.out.println("The shelves are mostly empty, some toppled over, and shattered pill bottles crunch under your feet.");
        System.out.println("A faint smell of disinfectant lingers in the air.");
        System.out.println("The place has been ransacked, but maybe there’s still something useful left.\n");
        System.out.println("1. Look around the pharmacy.");
        System.out.println("2. Leave the pharmacy.");
        System.out.println("3. Check behind the counter.");
        System.out.println("--------------------------->enter a number to decide");

        choice = PlayerDecision.inputWithCheck(3);

        if (choice == 1) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You take a moment to examine the ruined pharmacy.");
            System.out.println("Broken shelves, dried bloodstains on the floor, and a faint buzzing sound from a flickering light overhead.");
            System.out.println("You wonder who came here before you – and if they made it out alive.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            river();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You step outside, ready to move on.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            crossRoad();
        } else if (choice == 3 && medKit) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You spot an old first-aid kit behind the counter.");
            System.out.println("Some of the items inside are still usable.");
            System.out.println("You patch yourself up as best as you can.");
            System.out.println("Your "+Colors.GREEN+"HP"+player.getUserTextColor()+" have recovered.");

            player.setCurrentHP(player.getMaxHP());
            medKit = false;

            System.out.println("Your "+Colors.GREEN+"HP: " + player.getCurrentHP()+player.getUserTextColor());
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            river();
        } else if (choice == 3 && !medKit) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You glance behind the counter, but the first-aid kit is empty.");
            System.out.println("No more supplies left.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            river();
        }
    }

    public static void forest(){
        Player player = Player.getPlayer("ID1");

        System.out.println("\n------------------------------------------------------------------");
        System.out.print("You enter what used to be a small fast-food stand.");
        System.out.println("The air is stale, and the floor is sticky with old grease.");
        System.out.println("Chairs are knocked over, ketchup stains cover the counter, and a rotten burger sits half-eaten on a tray.");
        System.out.println("The smell of decay lingers.");
        System.out.println("1. Look around the stand.");
        System.out.println("2. Eat the rotten burger.");
        System.out.println("3. Leave the fast-food stand and return to Kröpke.");
        System.out.println("--------------------------->enter a number to decide");

        choice = PlayerDecision.inputWithCheck(3);

        if (choice == 1 && Objects.equals(player.getPlayerWeapon(), "Fist")) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You step behind the counter, searching for anything useful.");
            System.out.println("As you rummage through a drawer, your fingers touch something cold and metallic ...");
            System.out.println("a kitchen knife!!!");
            System.out.println("It’s not in the best condition, but it’s better than nothing.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();

            System.out.println("\n------------------------------------------------------------------");
            System.out.println("System:");
            System.out.println("You replaced your weapon 'Fist' with 'Knife'("+Colors.RED+"+1 min damage"+player.getUserTextColor()+" & "+Colors.RED+"+5 max damage"+player.getUserTextColor()+")");
            System.out.println("--------------------------->press enter to continue");

            player.setMinDamage(2);
            player.setMaxDamage(10);
            enterScanner.nextLine();
            forest();
        } else if (choice == 1 && !Objects.equals(player.getPlayerWeapon(), "Fist")) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You already searched here. Nothing else useful remains.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            forest();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You pick up the rotten burger, your stomach turning as you take a bite.");
            System.out.println("It tastes awful, and something feels wrong.");
            System.out.println("You feel your stomach churn, and your head spins.");
            System.out.println("System:");
            System.out.println("You "+Colors.GREEN+"lose 1 HP"+player.getUserTextColor()+".");
            System.out.println("--------------------------->press enter to continue");

            player.setCurrentHP(player.getCurrentHP() -1);
            enterScanner.nextLine();
        } else if (choice == 3) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You turn away from the abandoned stand and head back to Kröpke.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            crossRoad();
        }
    }

    public static void goblinCave(){
        Player player = Player.getPlayer("ID1");


        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You step into a ransacked kiosk. Shelves are toppled, shattered glass crunches underfoot, and the air reeks of stale beer and decay.");

        if (!player.getSilverRing()) {
            System.out.println("Behind the counter, a hunched figure twitches.");
            System.out.println("Once a shopkeeper, now a zombie.");
            System.out.println("Its head jerks toward you, and with a guttural growl it lunges!");
            System.out.println("1. Fight the kiosk zombie.");
            System.out.println("2. Run away");
            System.out.println("--------------------------->enter a number to decide");

        }else{
            System.out.println("1. Look around the kiosk.");
            System.out.println("2. Leave the kiosk and return to Kröpke.");

        }
        choice = PlayerDecision.inputWithCheck(2);

        if (choice == 1 && !player.getSilverRing()) {
            fight();
        } else if (choice == 1 && player.getSilverRing()) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Now that the zombie is no longer a threat, you take a moment to search the kiosk.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            goblinCave();
        } else if (choice == 2 && !player.getSilverRing()) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Panic takes over, and you sprint back to Kröpke.");
            System.out.println("The zombie snarls but doesn’t chase you.");
            System.out.println("The kiosk remains dangerous.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            crossRoad();
        } else if (choice == 2 && player.getSilverRing()) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You step over the body and make your way back to Kröpke, the pack of cigarettes tucked safely in your pocket.");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            crossRoad();
        }
    }

    public static void fight(){
        Player player = Player.getPlayer("ID1"); // Get player with ID1

        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        // Set goblin as enemy
        combat.setEnemy(firstEnemy);

        if(Enemy.specificEnemyAlive(0)) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Your "+Colors.GREEN+"HP: " + player.getCurrentHP() + player.getUserTextColor());
            System.out.println("Monster HP: " + firstEnemy.getCurrentHP());
            System.out.println("1: Attack");
            System.out.println("2: Run");
            System.out.println("--------------------------->enter a number to decide");

            choice = PlayerDecision.inputWithCheck(2);

            if (choice == 1 && Enemy.specificEnemyAlive(0)) {
                combat.attackPlayer();
                combat.attackEnemy();
                fight();
            } else if (choice == 2) {
                crossRoad();
            } else if (choice == 0) {
                fight();
            }
        }else{
            player.setSilverRing(true);
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("The zombie gurgles one last time before collapsing:");
            System.out.println("'H-heute ... nur Malboro im Angebot ... '");
            System.out.println("As it twitches on the floor, something falls from its pocket ...");
            System.out.println("a pack of cigarettes!!!");
            System.out.println("--------------------------->press enter to continue");

            enterScanner.nextLine();
            goblinCave();
        }
    }
}
