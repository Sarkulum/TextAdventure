package player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import text.Colors;

@SuppressWarnings("unused")
public class Player {
    private static Map<String, Player> players = new HashMap<>(); // Registry of all players

    Scanner enterScanner = new Scanner(System.in);

    // Constructor vars
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
    int score = 0;
    int movementSpeed; // How far player can move in 1 turn
    int randomRoom = 1;
    boolean died = false;

    // pre game var
    boolean endless; // Check for if player is playing endless mode

    // Vars from tuto
    boolean tutorialPassed = false; // Has player passed the tutorial?
    boolean firstShopVisit = true; // Has the player visited the shop before
    public boolean silverRing = false; // Does player one the ring
    boolean mission = false; // Boolean for mission from person in tuto
    boolean medKit = true; // Check if med kit in tuto was taken or not
    boolean burgerEaten = false;

    //Vars from room 1
    boolean firstVisit2 = true;  //boolean to check if bottle was found
    boolean firstVisit3 = true;
    boolean firstVisitMedKit = true;
    boolean firstVisitBoots = true;
    boolean firstVisitFood = true;//boolean to check if first visit to outsideHBF
    boolean key = false; // Checks for the key you get in room 1 at statue

    // Vars from room 2
    boolean soda = true;
    boolean firstSandwich = true;
    boolean zombieFought = false;
    boolean zombieCreated = false;

    // Constructor (private to encourage using the factory method)
    private Player(String userID ,String userName, int userAge, String userTextColor, int maxHP, int minDamage, int maxDamage, String playerWeapon, boolean DEV, int goldCoins, int movementSpeed) {
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
        this.movementSpeed = movementSpeed;
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
            int goldCoins,
            int movementSpeed
    ) {
        if (!players.containsKey(userID)) {
            Player player = new Player(userID, userName, userAge, userTextColor, maxHP, minDamage, maxDamage, playerWeapon, DEV, goldCoins, movementSpeed);
            players.put(userID, player);
        }
        return players.get(userID);
    }

    public void goldCoinPrint(String userID){
        Player player = getPlayer(userID);
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You now have "+ Colors.YELLOW +player.getGoldCoins()+" Gold coins"+player.getUserTextColor()+".");
        System.out.println("--------------------------->press enter to continue\n");
        enterScanner.nextLine();
    }

    public void resetOnDeath() {
        // Reset only the necessary variables
        this.currentHP = this.maxHP; // Restore HP

        // Tutorial and room-related booleans
        this.mission = false;
        this.medKit = true;
        this.burgerEaten = false;

        this.firstVisit2 = true;
        this.firstVisit3 = true;
        this.firstVisitMedKit = true;
        this.firstVisitBoots = true;
        this.firstVisitFood = true;
        this.key = false;

        this.soda = true;
        this.firstSandwich = true;
        this.zombieFought = false;
        this.zombieCreated = false;

        // Add any other booleans you want to reset
    }


    // Method to retrieve a player by name
    public static Player getPlayer(String userID) {
        return players.get(userID);
    }

    // Getter for username (setter is in constructor)
    public String getUserName(){return this.userName;}

    // Getter and Setter for DEV
    public void setDEV(boolean inputDEV) {this.DEV = inputDEV;}
    public boolean DEV(){return this.DEV;}

    // Getter for user age (setter is in constructor)
    public int getUserAge() {
        return this.userAge;
    }

    // Getter for text color (setter is in constructor)
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
    public boolean playerAlive(){return this.currentHP > 0;}

    //Setter and Getter for key
    public void setKey(boolean key) {this.key = key;}
    public boolean getKey() {return this.key;}

    // Setter and Getter for score
    public void setScore(int score) {this.score = score;}
    public int getScore() {return this.score;}

    // Getter and setter for endless
    public boolean isEndless() {return this.endless;}
    public void setEndless(boolean endless) {this.endless = endless;}

    //Getter and Setter for med kit
    public  boolean isMedKit() {return this.medKit;}
    public void setMedKit(boolean medKit) {this.medKit = medKit;}

    // Getter and setter for Mission in tuto
    public  boolean isMission() {return this.mission;}
    public  void setMission(boolean mission) {this.mission = mission;}

    // Getter and setter for FirstVisit2
    public boolean isFirstVisit2() {return this.firstVisit2;}
    public void setFirstVisit2(boolean firstVisit2) {this.firstVisit2 = firstVisit2;}

    // Getter and setter for FirstVisit3
    public boolean isFirstVisit3() {return this.firstVisit3;}
    public void setFirstVisit3(boolean firstVisit3) {this.firstVisit3 = firstVisit3;}

    // Getter and setter for FirstVisitMedKit
    public boolean isFirstVisitMedKit() {return this.firstVisitMedKit;}
    public void setFirstVisitMedKit(boolean firstVisitMedKit) {this.firstVisitMedKit = firstVisitMedKit;}

    // Getter and setter for FirstVisitBoots
    public boolean isFirstVisitBoots() {return this.firstVisitBoots;}
    public void setFirstVisitBoots(boolean firstVisitBoots) {this.firstVisitBoots = firstVisitBoots;}

    // Getter and setter for FirstVisitFood
    public boolean isFirstVisitFood() {return this.firstVisitFood;}
    public void setFirstVisitFood(boolean firstVisitFood) {this.firstVisitFood = firstVisitFood;}

    // Getter ans setter for Soda
    public boolean isSoda() {return this.soda;}
    public void setSoda(boolean soda) {this.soda = soda;}

    // Getter and setter for FirstSandwich
    public boolean isFirstSandwich() {return this.firstSandwich;}
    public void setFirstSandwich(boolean firstSandwich) {this.firstSandwich = firstSandwich;}

    // Getter and setter for ZombieFought
    public boolean isZombieFought() {return this.zombieFought;}
    public void setZombieFought(boolean zombieFought) {this.zombieFought = zombieFought;}

    // Getter and setter for ZombieCreated
    public boolean isZombieCreated() {return this.zombieCreated;}
    public void setZombieCreated(boolean zombieCreated) {this.zombieCreated = zombieCreated;}

    // Getter and setter for BurgerEaten
    public boolean isBurgerEaten() {return this.burgerEaten;}
    public void setBurgerEaten(boolean burgerEaten) {this.burgerEaten = burgerEaten;}

    // Getter and setter for movement speed
    public int getMovementSpeed() {return this.movementSpeed;}
    public void setMovementSpeed(int movementSpeed) {this.movementSpeed = movementSpeed;}

    public int getRandomRoom() {return this.randomRoom;}
    public void setRandomRoom(int randomRoom) {this.randomRoom = randomRoom;}

    public boolean isDied() {return this.died;}
    public void setDied(boolean died) {this.died = died;}
}