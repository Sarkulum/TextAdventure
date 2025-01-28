package player;

import java.util.HashMap;
import java.util.Map;

import enemys.Enemy;
import map.Shop;
import map.Tutorial;

public class Player {
    private static Map<String, Player> players = new HashMap<>(); // Registry of all players

    public String userID;
    public String userName;
    public int userAge;
    public String userTextColor;
    int maxHP;
    int currentHP;
    int minDamage;
    int maxDamage;
    String playerWeapon;
    boolean DEV;
    int goldCoins;
    boolean tutorialPassed;
    boolean firstShopVisit;
    public boolean silverRing;

    // Constructor (private to encourage using the factory method)
    private Player(String userID ,String userName, int userAge, String userTextColor, int maxHP, int minDamage, int maxDamage, String playerWeapon, boolean DEV, int goldCoins) {
        this.userID = userID;
        this.userName = userName;
        this.userAge = userAge;
        this.userTextColor = userTextColor;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.playerWeapon = playerWeapon;
        this.DEV = DEV;
        this.goldCoins = goldCoins;
    }

    // Factory method to create or retrieve a player
    public static Player createPlayer(
            String userID,
            String userName,
            boolean DEV,
            int userAge,
            String userTextColor,
            int maxHP,
            int minDamage,
            int maxDamage,
            String playerWeapon,
            int goldCoins
    ) {
        if (!players.containsKey(userID)) {
            Player player = new Player(userID, userName, userAge, userTextColor, maxHP, minDamage, maxDamage, playerWeapon, DEV, goldCoins);
            players.put(userID, player);
        }
        return players.get(userID);
    }

    // Method to retrieve a player by name
    public static Player getPlayer(String userID) {
        return players.get(userID);
    }

    // Getter for username.
    public String getUserName(){return this.userName;}

    // Getter and Setter for DEV
    public void setDEV(boolean inputDEV) {this.DEV = inputDEV;}
    public boolean DEV(){return this.DEV;}

    // Getter for user age.
    public int getUserAge() {
        return this.userAge;
    }

    // Getter for text color.
    public String getUserTextColor() {
        return this.userTextColor;
    }

    // Setter and getter for player max damage.
    public void setMaxDamage(int inputMaxDamage){this.maxDamage = inputMaxDamage;}
    public int getMaxDamage(){return this.maxDamage;}

    // Setter and getter for player min damage.
    public void setMinDamage(int inputMinDamage){this.minDamage = inputMinDamage;}
    public int getMinDamage(){return this.minDamage;}

    // Setter and getter for player hp.
    public void setMaxHP(int inputHP){this.maxHP = inputHP;}
    public int getMaxHP(){return this.maxHP;}

    // Setter and getter for current hp.
    public void setCurrentHP(int inputHP){this.currentHP = inputHP;}
    public int getCurrentHP(){return this.currentHP;}

    // Setter and getter for playerWeapon.
    public void setPlayerWeapon(String input){this.playerWeapon = input;}
    public String getPlayerWeapon(){return this.playerWeapon;}

    // Getter and Setter for gold coins
    public int getGoldCoins(){return this.goldCoins;}
    public void setGoldCoins(int goldCoins){this.goldCoins = goldCoins;}

    // Get alive status of player
    public void playerAlive(Enemy enemy){
        if(this.currentHP > 0){
            return;
        }
        if(tutorialPassed){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You have died to "+enemy.getEnemyName()+". You will now respawn at the Shop.");
            System.out.println("------------------------------------------------------------------\n");
            Shop.buyUpgrades();
        }else{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You have died to "+enemy.getEnemyName()+". You will now respawn at the beginning.");
            System.out.println("------------------------------------------------------------------\n");
            Tutorial.townGate();
        }
    }

    // Getter and Setter for tutorial passed
    public boolean getTutorialPassed(){return this.tutorialPassed;}
    public void setTutorialPassed(boolean tutorialPassed){this.tutorialPassed = tutorialPassed;}

    // Getter and setter for first shop visit
    public boolean getFirstShopVisit(){return this.firstShopVisit;}
    public void setFirstShopVisit(boolean firstShopVisit){this.firstShopVisit = firstShopVisit;}

    //Setter and getter for silverRing
    public void setSilverRing(boolean owned){this.silverRing = owned;}
    public boolean getSilverRing(){return this.silverRing;}
}