package map;

import combat.Attack;
import enemys.Enemy;
import enemys.ZombieTypes;

import java.util.HashMap;
import java.util.Map;
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

        while (Enemy.enemyAlive()){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You have entered a room with "+maxEnemy+" enemy's inside.");
            System.out.println("------------------------------------------------------------------\n");
            for (int i = 0; i < maxEnemy; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                combat.setEnemy(currentEnemy);
                combat.attackPlayer();
                enterScanner.nextLine();
            }
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("There are "+maxEnemy+" in front of you. Who do you want to attack?");
            System.out.println("------------------------------------------------------------------\n");
            int target = enterScanner.nextInt();

            Enemy currentEnemy = Enemy.getEnemy(target);
            combat.setEnemy(currentEnemy);
            combat.attackEnemy();
        }
    }
}
