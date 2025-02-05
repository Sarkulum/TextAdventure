package enemys;

import player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
public class Enemy {
    private static Map<Integer, Enemy> enemys = new HashMap<>(); // Registry of all enemy's

    static Random random = new Random();

    // Vars for all relevant Enemy stats.
    public Integer enemyID;
    public String name;
    public int maxDamage;
    public int minDamage;
    public int maxHP;
    public int currentHP;
    public static int idIndex = 0; // Index to know witch id I am at.

    // Constructor to initialize the enemy
    private Enemy(Integer enemyID, String name, int maxDamage, int minDamage, int maxHP) {
        this.enemyID = enemyID;       // Assign the ID
        this.name = name;             // Assign the name
        this.maxDamage = maxDamage;   // Assign maximum damage
        this.minDamage = minDamage;   // Assign minimum damage
        this.maxHP = maxHP;           // Assign maximum HP
        this.currentHP = maxHP;       // Set current HP to max HP by default
    }

    // Factory method to create or retrieve an enemy
    public static Enemy createEnemy(Integer enemyID , String name, int maxDamage, int minDamage, int maxHP) {
        if (!enemys.containsKey(enemyID)) {
            Enemy enemy = new Enemy(enemyID, name, maxDamage, minDamage, maxHP);
            enemys.put(enemyID, enemy);
        }
        return enemys.get(enemyID);
    }

    // Method to retrieve an enemy by id
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
    public static boolean anyEnemyAlive(int index) {
        // Changed it so that I use index and not enemy's.size also made the for loop start from -1 and check for <= IDK why this fixed it, but it did.
        for(int i = -1; i <= index; i++) {
            Enemy currentEnemy = Enemy.getEnemy(i);
            if (currentEnemy != null) {
                int enemyCurrentHP = currentEnemy.getCurrentHP();
                if (enemyCurrentHP > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean specificEnemyAlive(Integer enemyID){
        try {
            Enemy enemy = getEnemy(enemyID);
            return enemy.getCurrentHP() > 0;
        }catch (NullPointerException e){
            return false;
        }
    }

    // clean list to remove all dead enemy's
    public static void cleanList() {
        // Use an iterator to safely remove elements while iterating
        enemys.entrySet().removeIf(entry -> entry.getValue().getCurrentHP() <= 0);// Clears all dead enemies in one step because hash maps are fucking stupid

    }

    // Function to remove enemy's from Map after fight
    public static void removeAllEntrys() {
        // Use an iterator to safely remove elements while iterating
        enemys.clear(); // Clears all enemies in one step because hash maps are fucking stupid
        idIndex = 0;
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

    public static void removeEnemyById(int enemyID) {
        if (enemys.containsKey(enemyID)) {
            System.out.println("Removing enemy ID " + enemyID);
            enemys.remove(enemyID); // Remove from map
        }
    }

    public static void dropGoldCoins(String name){
        Player player = Player.getPlayer("ID1");

        switch (name){
            case "Shambler" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(2,5));
                player.goldCoinPrint("ID1");
            }
            case "Rotter" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(3,8));
                player.goldCoinPrint("ID1");
            }
            case "Crawler" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(2,6));
                player.goldCoinPrint("ID1");
            }
            case "Ghoul", "Burnt Zombie" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(5,10));
                player.goldCoinPrint("ID1");
            }
            case "Bloater" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(8,15));
                player.goldCoinPrint("ID1");
            }
            case "Lurker" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(6,12));
                player.goldCoinPrint("ID1");
            }
            case "Spitter" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(7,12));
                player.goldCoinPrint("ID1");
            }
            case "Brute" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(10,20));
                player.goldCoinPrint("ID1");
            }
            case "Mutant Zombie" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(15,25));
                player.goldCoinPrint("ID1");
            }
            case "Necrofiend" -> {
                player.setGoldCoins(player.getGoldCoins()+ random.nextInt(20,30));
                player.goldCoinPrint("ID1");
            }
            default -> {
                player.setGoldCoins(player.getGoldCoins()+1);
                player.goldCoinPrint("ID1");
            }
        }
    }

    // Setter and getter for idIndex
    public static void setIdIndex(int id) {idIndex = idIndex + id;}
    public static int getIdIndex() {return idIndex;}
}
