package map;

import combat.Attack;
import enemys.Enemy;
import enemys.ZombieTypes;
import java.util.Random;
import java.util.Scanner;

public class RandomRooms {
    Random random = new Random();
    Scanner enterScanner = new Scanner(System.in);

    public void setRandomRoom(int maxEnemy){
        for(int i = 0; maxEnemy > i; i++){
            ZombieTypes.createZombie("Shambler", i);
        }
        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System: You have entered a room with "+maxEnemy+" enemy's inside.");
        System.out.println("------------------------------------------------------------------\n");

        while (Enemy.anyEnemyAlive()){
            for (int i = 0; i < maxEnemy; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                combat.setEnemy(currentEnemy);
                combat.attackPlayer();
            }
            // Make a for loop so every enemy gets printed by name
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("System: There are "+maxEnemy+" in front of you. Who do you want to attack?");
            System.out.println("--------------------------->press enter to continue");
            int target = enterScanner.nextInt();

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
