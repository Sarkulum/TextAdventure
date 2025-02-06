package player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import enemys.Enemy;
import map.Shop;
import map.Tutorial;
import text.Colors;

@SuppressWarnings("unused")
public class Player {
    private static Map<String, Player> players = new HashMap<>(); // Registry of all players

    Scanner enterScanner = new Scanner(System.in);

    public String userID; // Player identifier
    public String userName; // Username set by player
    public int userAge; // User Age
    public String userTextColor; // Text color the print statements get displayed in
    int maxHP; // Max possible hp
    int currentHP; // Current hp to save hp after getting damaged
    int minDamage; // Min damage the player can deal
    int maxDamage; // Max damage the player can deal
    String playerWeapon; // Name of the weapon the player has
    boolean DEV; // Is user Dev?
    int goldCoins; // Count for gold coins
    boolean tutorialPassed = false; // Has player passed the tutorial?
    boolean firstShopVisit = true; // Has the player visited the shop before
    public boolean silverRing = false; // Does player one the ring
    boolean key = false;
    int score = 0;
    boolean endless;

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

    // Getter and Setter for tutorial passed
    public boolean getTutorialPassed(){return this.tutorialPassed;}
    public void setTutorialPassed(boolean tutorialPassed){this.tutorialPassed = tutorialPassed;}

    // Getter and setter for first shop visit
    public boolean getFirstShopVisit(){return this.firstShopVisit;}
    public void setFirstShopVisit(boolean firstShopVisit){this.firstShopVisit = firstShopVisit;}

    //Setter and getter for silverRing
    public void setSilverRing(boolean owned){this.silverRing = owned;}
    public boolean getSilverRing(){return this.silverRing;}

    // Get alive status of player
    public void playerAlive(Enemy enemy){
        if(this.currentHP > 0){
            return;
        }
        if(tutorialPassed){
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You have died to "+enemy.getEnemyName()+". You will now respawn at the Shop.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            this.currentHP = this.maxHP;
            Shop.buyUpgrades();
        }else{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You have died to a "+enemy.getEnemyName()+". You will now respawn at the beginning.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            this.currentHP = this.maxHP;
            Tutorial.townGate();
        }
    }

    public void goldCoinPrint(String userID){
        Player player = getPlayer(userID);
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You now have "+ Colors.YELLOW +player.getGoldCoins()+" Gold coins"+player.getUserTextColor()+".");
        System.out.println("--------------------------->press enter to continue\n");
        enterScanner.nextLine();
    }

    //Setter and Getter for key
    public void setKey(boolean key) {this.key = key;}
    public boolean getKey() {return this.key;}

    // Setter and Getter for score
    public void setScore(int score) {this.score = score;}
    public int getScore() {return this.score;}

    // Getter and setter for endless
    public boolean isEndless() {return this.endless;}
    public void setEndless(boolean endless) {this.endless = endless;}
}