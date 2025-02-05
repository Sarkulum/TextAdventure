package enemys;

import java.util.Random;

@SuppressWarnings("unused")
public class PercentBasedEnemy {
    static String[] enemyArray = new String[100]; // Spawn enemy's based on percent chance
    static int index = 0; // Index so you don't overwrite a spot
    static Random random = new Random();
    public static String enemy;

    // Setter for 1 enemy and there %
    public static void setEnemyArray(String enemy, int chance) {
        for(int i = 0; chance > i; i++){
            enemyArray[index++] = enemy;
        }
    }

    // Spawns 1 enemy.
    public static void spawnEnemy(){
        int randomEnemy = random.nextInt(100);
       enemy = enemyArray[randomEnemy];

        ZombieTypes.createZombie(enemy, Enemy.getIdIndex());
        Enemy.setIdIndex(1);
    }

    // Reset index for new chances
    public static void resetIndex(){index = 0;}
}
