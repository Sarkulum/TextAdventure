package enemys;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Enemy {
    private static Map<Integer, Enemy> enemys = new HashMap<>(); // Registry of all enemys

    // Vars for all relevant Enemy stats.
    public Integer enemyID;
    public String name = "empty";
    public int maxDamage;
    public int minDamage;
    public int maxHP;
    public int currentHP;

    // Constructor to initialize the enemy
    private Enemy(Integer enemyID, String name, int maxDamage, int minDamage, int maxHP) {
        this.enemyID = enemyID;
        this.name = name;             // Assign the name
        this.maxDamage = maxDamage;   // Assign maximum damage
        this.minDamage = minDamage;   // Assign minimum damage
        this.maxHP = maxHP;           // Assign maximum HP
        this.currentHP = maxHP;       // Set current HP to max HP by default
    }

    // Factory method to create or retrieve a player
    public static Enemy createEnemy(Integer enemyID , String name, int maxDamage, int minDamage, int maxHP) {
        if (!enemys.containsKey(enemyID)) {
            Enemy enemy = new Enemy(enemyID, name, maxDamage, minDamage, maxHP);
            enemys.put(enemyID, enemy);
        }
        return enemys.get(enemyID);
    }

    // Method to retrieve a enemy by id
    public static Enemy getEnemy(Integer enemyID) {
        return enemys.get(enemyID);
    }

    // Getter for Hash Map
    public Map<Integer, Enemy> getHashMap() {return enemys;}

    // Getter for id
    public Integer getEnemyID() {return this.enemyID;}

    // Getter and Setter for name
    public void setName(String inputName) { this.name = inputName; }
    public String getEnemyName() { return this.name; }

    // Getter and Setter for max damage
    public void setMaxDamage(int inputMaxDamage) { this.maxDamage = inputMaxDamage; }
    public int getMaxDamage() { return this.maxDamage; }

    // Getter and Setter for min damage
    public void setMinDamage(int inputMinDamage) { this.minDamage = inputMinDamage; }
    public int getMinDamage() { return this.minDamage; }

    // Getter and Setter for max HP
    public void setMaxHP(int inputHP) { this.maxHP = inputHP; }
    public int getMaxHP() { return this.maxHP; }

    // Getter and Setter for current HP
    public void setCurrentHP(int inputHP) { this.currentHP = inputHP; }
    public int getCurrentHP() { return this.currentHP; }

    // Boolean to check if any enemy on the Map still has more than 0 hp
    public static boolean enemyAlive() {
        int nummberOfEnemys = enemys.size();
        for(int i = 0; i < nummberOfEnemys; i++) {
            Enemy currentEnemy = Enemy.getEnemy(i);
            int enemyCurrentHP = currentEnemy.getCurrentHP();
            if ( enemyCurrentHP > 0) {
                return true;
            }
        }
        return false;
    }

    // Function to remove enemys from Map after fight
    public void removeEntrys() {
        int nummberOfEnemys = enemys.size();
        for(int i = 0; i < nummberOfEnemys; i++) {
            Enemy currentEnemy = Enemy.getEnemy(i);
            enemys.remove(currentEnemy);
        }
    }

    // Boolean to see if the id is still available
    public static boolean isIdFree(Integer id) {
        for(int i = 0; i < enemys.size(); i++) {
            Enemy currentEnemy = Enemy.getEnemy(i);
            Integer currentID = currentEnemy.getEnemyID();
            if(Objects.equals(currentID, id)) {
                return false;
            }
        }
        return true;
    }
}
