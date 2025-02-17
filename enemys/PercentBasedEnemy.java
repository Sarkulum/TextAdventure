package enemys;

import java.util.Random;

// This is just an array with a length of 100 that is getting filled with the names of enemy's to then call the zombieTypes class to spawn them based on name
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
    public static int spawnEnemy(){
        int randomEnemy = random.nextInt(100);
        enemy = enemyArray[randomEnemy];
        int id = Enemy.getIdIndex();
        ZombieTypes.createZombie(enemy, id);
        Enemy.setIdIndex(1);
        return id;
    }

    // Reset index for new chances
    public static void resetIndex(){index = 0;}

    public static void adjustEnemySpawnRates(int waveCount) {
        //resetIndex(); // Clear old enemy chances

        if (waveCount < 5) {  // Early waves
            setEnemyArray("Walker", 50);
            setEnemyArray("Shambler", 30);
            setEnemyArray("Crawler", 20);
        } else if (waveCount < 10) { // Mid-waves
            setEnemyArray("Walker", 30);
            setEnemyArray("Shambler", 25);
            setEnemyArray("Crawler", 15);
            setEnemyArray("Bloater", 15);
            setEnemyArray("Brute", 10);
            setEnemyArray("Ghoul", 5);
        } else if (waveCount < 15) { // Late game
            setEnemyArray("Shambler", 20);
            setEnemyArray("Bloater", 20);
            setEnemyArray("Brute", 20);
            setEnemyArray("Necrofiend", 20);
            setEnemyArray("Mutant Zombie", 10);
            setEnemyArray("Lurker", 10);
        } else { // Extreme difficulty
            setEnemyArray("Necrofiend", 30);
            setEnemyArray("Mutant Zombie", 30);
            setEnemyArray("Brute", 20);
            setEnemyArray("Ghoul", 10);
            setEnemyArray("Spitter", 10);
        }
    }

}
