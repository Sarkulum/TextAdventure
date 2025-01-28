package enemys;

import java.util.Random;

public class PercentBasedEnemys {
    String[] enemyArray = new String[100]; // Spawen enemys based on percent chance
    int index = 0; // Index so you dont overwrite a spot
    Random random = new Random();

    // Setter for 1 enemy and there %
    public void setEnemyArray(String enemy, int chance) {
        for(int i = 0; chance > i; i++){
            enemyArray[index++] = enemy;
        }
    }

    // Spawns 1 enemy.
    public void spawnEnemy(){
        int randomEnemy = random.nextInt(100);
        String enemy = enemyArray[randomEnemy];

        ZombieTypes.createZombie(enemy, 999);
    }

    // Reset index for new chances
    public void resetIndex(){index = 0;}
}
