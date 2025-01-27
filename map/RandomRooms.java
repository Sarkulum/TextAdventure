package map;

import combat.Attack;
import enemys.Enemy;

import java.util.Random;
import java.util.Scanner;

public class RandomRooms {
    Random random = new Random();
    Scanner enterScanner = new Scanner(System.in);

    public void setRandomRoom(int maxEnemy, int maxDamage, int minDamage, int maxHP){
        int enemys = random.nextInt(maxEnemy) + 1;

        for (int i = 0; enemys > i; i++){
            Enemy enemy = Enemy.createEnemy(
                    i,
                    "Empty",
                    random.nextInt(maxDamage),
                    random.nextInt(minDamage),
                    random.nextInt(maxHP)
            );  // Create an enemy with unique id
        }

        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        while (Enemy.enemyAlive()){
            for (int i = 0; i < enemys; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                combat.setEnemy(currentEnemy);
                combat.attackPlayer();
                enterScanner.nextLine();
            }
            System.out.println("There are "+enemys+" in front of you. Who do you want to attack?");
            int target = enterScanner.nextInt();

            Enemy currentEnemy = Enemy.getEnemy(target);
            combat.setEnemy(currentEnemy);
            combat.attackEnemy();
        }
    }
}
