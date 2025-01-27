package map;

import combat.Attack;
import items.StoryItems;
import player.Player;
import player.PlayerDecision;
import enemys.Enemy;

import java.util.Objects;
import java.util.Scanner;

public class Tutorial {
    // Scanner to check if the player wants to proceed.
    static Scanner enterScanner = new Scanner(System.in);
    // Int for player choice.
    public static int choice;

    // Starting room.
    public static void townGate(StoryItems storyItems) {
        Player player = Player.getPlayer("ID1");
        System.out.println(player.userTextColor+ "\n------------------------------------------------------------------");
        System.out.println("You are at the gate of the Great Village of Laatzen-Ost.");
        System.out.println("A golden armored guard with a longsword is standing in front of you!");
        System.out.println("");
        System.out.println("What do you want to do?");
        System.out.println("");
        System.out.println("1: Talk to the guard");
        System.out.println("2: Attack the guard");
        System.out.println("3: Go north to the crossroad");
        System.out.println("------------------------------------------------------------------\n");

        choice = PlayerDecision.inputWithCheck(3);

        if(choice == 1) {
            if (storyItems.getSilverRing() || player.DEV()) {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("Guard: Oh! You killed that goblin!?? Great!");
                System.out.println("It seems you are a trustworthy and sexy guy. Here is my phone number.");
                System.out.println("Welcome to our Great Village of Laatzen-Ost!");
                System.out.println("\n\n           THE END                    ");
                System.out.println("------------------------------------------------------------------\n");

                RandomRooms randomRoom = new RandomRooms();
                randomRoom.setRandomRoom(10, 10, 1, 10);
            }else {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("Guard: Hello there, stranger. So your name is " + player.getUserName() + "? \nSorry but we cannot let stranger enter our town.");
                System.out.println("--------------------------->press enter to continue");
                enterScanner.nextLine();
                townGate(storyItems);
            }
        }else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
            System.out.println("Your HP: " + player.getCurrentHP());
            System.out.println("--------------------------->press enter to continue");
            enterScanner.nextLine();
            townGate(storyItems);
        }else if (choice == 3) {
            crossRoad(storyItems);
        }else if (choice == 0){
            townGate(storyItems);
        }
    }

    public static void crossRoad(StoryItems storyItems){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
        System.out.println("1: Go north");
        System.out.println("2: Go east");
        System.out.println("3: Go south");
        System.out.println("4: Go west");
        System.out.println("------------------------------------------------------------------\n");

        choice = PlayerDecision.inputWithCheck(4);

        if(choice==1){
            river(storyItems);
        }else if(choice==2){
            forest(storyItems);
        }else if(choice==3){
            townGate(storyItems);
        }else if(choice==4){
            goblinCave(storyItems);
        }else if(choice == 0){
            crossRoad(storyItems);
        }
    }

    public static void river(StoryItems storyItems){
        Player player = Player.getPlayer("ID1");

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("There is a river. You drink the water and rest at the riverside.");
        System.out.println("Your HP is recovered.");

        player.setCurrentHP(player.getMaxHP());

        System.out.println("Your HP: " + player.getCurrentHP());
        System.out.println("\n\n1: Go back to the crossroad");
        System.out.println("------------------------------------------------------------------\n");

        choice = PlayerDecision.inputWithCheck(1);

        if(choice==1){
            crossRoad(storyItems);
        }else if(choice == 0) {
            river(storyItems);
        }
    }

    public static void forest(StoryItems storyItems){
        Player player = Player.getPlayer("ID1");

        System.out.println("\n------------------------------------------------------------------");
        System.out.print("You walked into a forest ");

        if (Objects.equals(player.getPlayerWeapon(), "Knife")) {
            System.out.println("and found a Sword!");
            player.setPlayerWeapon("Sword");
            player.setMinDamage(2);
            player.setMaxDamage(10);
        }

        System.out.println("\nYour Weapon: "+ player.getPlayerWeapon());
        System.out.println("\n\n1: Go back to the crossroad");
        System.out.println("------------------------------------------------------------------\n");

        choice = PlayerDecision.inputWithCheck(1);

        if(choice==1){
            crossRoad(storyItems);
        }else if (choice == 0){
            forest(storyItems);
        }
    }

    public static void goblinCave(StoryItems storyItems){
        if(!storyItems.silverRing) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You are at a cave. You encounter a GOBLIN!\n");
            System.out.println("1: Fight");
            System.out.println("2: Run");
            System.out.println("------------------------------------------------------------------\n");

            choice = PlayerDecision.inputWithCheck(2);

            if (choice == 1) {
                fight(storyItems);
            } else if (choice == 2) {
                crossRoad(storyItems);
            } else if (choice == 0) {
                goblinCave(storyItems);
            }
        }else{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You are at a cave. You see the corps of the Goblin you have just killed.\n");
            System.out.println("1: Go back to the crossroad");
            System.out.println("------------------------------------------------------------------\n");

            choice = PlayerDecision.inputWithCheck(1);

            if (choice == 1){
                crossRoad(storyItems);
            }else{
                goblinCave(storyItems);
            }
        }
    }

    public static void fight(StoryItems storyItems){
        Player player = Player.getPlayer("ID1");
        Enemy goblin = Enemy.createEnemy(1,"Goblin", 5, 1, 10);

        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        // Set goblin as enemy
        combat.setEnemy(goblin);

        if(goblin.getCurrentHP() > 0) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Your HP: " + player.getCurrentHP());
            System.out.println("Monster HP: " + goblin.getCurrentHP());
            System.out.println("\n1: Attack");
            System.out.println("2: Run");
            System.out.println("------------------------------------------------------------------\n");

            choice = PlayerDecision.inputWithCheck(2);

            if (choice == 1) {
                combat.attackPlayer();
                combat.attackEnemy();
                fight(storyItems);
            } else if (choice == 2) {
                crossRoad(storyItems);
            } else if (choice == 0) {
                fight(storyItems);
            }
        }else{
            storyItems.setSilverRing(true);
            System.out.println("\nYou have killed the Goblin and gained a Silver Ring.");
            goblin.removeEntrys();
            goblinCave(storyItems);
        }
    }
}
