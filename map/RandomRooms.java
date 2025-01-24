package map;

import combat.Attack;
import enemys.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomRooms {
    Random random = new Random();

    public void setRandomRoom(int maxEnemy, int maxDamage, int minDamage, int maxHP){
        int enemys = random.nextInt(maxEnemy);
        boolean enemyAlive = true;
        List<Enemy> enemies = new ArrayList<>();

        for (int i = 0; enemys > i; i++){
            Enemy enemy = new Enemy("Enemy " + (i + 1), random.nextInt(maxDamage), random.nextInt(minDamage), random.nextInt(maxHP));  // Create an enemy with unique name
            enemies.add(enemy); // Add the enemy to the list
        }

        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        while (enemyAlive){
            for (Enemy enemy : enemies) {
                combat.setEnemy(enemy);
                combat.attackPlayer();
            }
        }
    }
}
