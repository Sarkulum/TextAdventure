package map;

import combat.Attack;
import enemys.Enemy;
import enemys.ZombieTypes;
import java.util.Random;
import java.util.Scanner;

public class RandomRooms {
    public int index;
    public int attackIndex = -1;
    Random random = new Random();
    Scanner enterScanner = new Scanner(System.in);

    public void setRandomRoom(int maxEnemy){
        index = maxEnemy;
        for(int i = 0; maxEnemy > i; i++){
            ZombieTypes.createZombie("Shambler", i);
        }
        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System: You have entered a room with "+index+" enemy's inside.");
        System.out.println("------------------------------------------------------------------\n");

        while (Enemy.anyEnemyAlive(maxEnemy)){
            for (int i = 0; i < index; i++) {
                if (!Enemy.specificEnemyAlive(i)){
                    index--;
                    attackIndex--;
                }
            }
            // Changed some shit here if no worky set i = -1 back to i = 0
            for (int i = 0; i < maxEnemy; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                combat.setEnemy(currentEnemy);
                combat.attackPlayer();
            }
            // Make a for loop so every enemy gets printed by name
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("System: There are "+index+" in front of you. Who do you want to attack?");
            System.out.println("Enemy status:");

            for (int i = 0; i < maxEnemy; i++) {
                int j = i + 1;
                if (Enemy.specificEnemyAlive(i)){
                    System.out.println("Enemy: " + j + " = Alive");
                }else {
                    System.out.println("Enemy: " + j + " = DEAD");
                }
            }
            System.out.println("--------------------------->enter a number to decide");
            int target = enterScanner.nextInt() -1;

            Enemy currentEnemy = Enemy.getEnemy(target);
            combat.setEnemy(currentEnemy);
            combat.attackEnemy();
        }
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System: You have cleared this room and will now move to the next one.");
        System.out.println("--------------------------->press enter to continue");
        enterScanner.nextLine();
        Enemy.removeAllEntrys();
    }
}
