package map;

import enemys.Enemy;

import java.util.Random;

public class RandomRooms {
    Random random = new Random();

    public void setRandomRoom(int maxEnemy, int maxDamage, int minDamage, int maxHP){
        int enemys = random.nextInt(maxEnemy);
        boolean enemyAlive = true;

        for (int i = 0; enemys > i; i++){
            Enemy randomEnemy = new Enemy();

            randomEnemy.setName("Enemy"+i);
            randomEnemy.setCurrentHP(random.nextInt(maxHP));
            randomEnemy.setMaxDamage(random.nextInt(maxDamage));
            randomEnemy.setMinDamage(minDamage);
        }

        while (enemyAlive){

        }
    }
}
