package player;

import text.Colores;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

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

    // Constructor (private to encourage using the factory method)
    private Player(String userID ,String userName, int userAge, String userTextColor, int maxHP, int minDamage, int maxDamage, String playerWeapon, boolean DEV) {
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
    }

    // Factory method to create or retrieve a player
    public static Player createPlayer(String userID , String userName, boolean DEV, int userAge, String userTextColor, int maxHP, int minDamage, int maxDamage, String playerWeapon) {
        if (!players.containsKey(userID)) {
            Player player = new Player(userID, userName, userAge, userTextColor, maxHP, minDamage, maxDamage, playerWeapon, DEV);
            players.put(userID, player);
        }
        return players.get(userID);
    }

    // Method to retrieve a player by name
    public static Player getPlayer(String userID) {
        return players.get(userID);
    }

    /*
    // Setter using scanner to set username.
    public void setUserName() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Please enter your player name you want to use during the game.");
        System.out.println("------------------------------------------------------------------\n");
        Scanner name = new Scanner(System.in);
        this.userName = name.nextLine();

        if (Objects.equals(this.userName, "DEV")){
            this.DEV = true;
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Welcome DEV you now have the power to destroy.");
            System.out.println("------------------------------------------------------------------\n");
        }
    }
     */

    // Getter for username.
    public String getUserName(){return this.userName;}

    // Getter for DEV
    public boolean DEV(){return this.DEV;}

    /*
    // Setter for user Age.
    public void setUserAge() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Please enter your legal age.");
        System.out.println("------------------------------------------------------------------\n");
        Scanner age = new Scanner(System.in);
        this.userAge = age.nextInt();
    }

     */

    // Getter for user age.
    public int getUserAge() {
        return this.userAge;
    }

    /*
    // Setter using scanner to set text color.
    public void setTextColor() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Please enter the color the text should be. You can chose between:");
        System.out.println(Colores.BLUE+ "Blue" +Colores.RESET);
        System.out.println(Colores.CYAN+ "Cyan" +Colores.RESET);
        System.out.println(Colores.PURPLE+ "Purple" +Colores.RESET);
        System.out.println(Colores.YELLOW+ "Yellow" +Colores.RESET);
        System.out.println(Colores.Gray + "Gray" +Colores.RESET);
        System.out.println("White");
        System.out.println("------------------------------------------------------------------\n");

        Scanner color = new Scanner(System.in);
        String input = color.nextLine();

        switch (input) { //This looks scary but its basically just a simplified if else statement.
            case "Blue" -> this.userTextColor = Colores.BLUE;
            case "Cyan" -> this.userTextColor = Colores.CYAN;
            case "Purple" -> this.userTextColor = Colores.PURPLE;
            case "Yellow" -> this.userTextColor = Colores.YELLOW;
            case "White" -> this.userTextColor = "";
            default -> this.userTextColor = "";
        };
    }

     */

    // Getter for text color.
    public String getUserTextColor() {
        return this.userTextColor;
    }

    // Setter for player max damage.
    public void setMaxDamage(int inputMaxDamage){this.maxDamage = inputMaxDamage;}

    // Getter for Max Damage.
    public int getMaxDamage(){return this.maxDamage;}

    // Setter for player min damage.
    public void setMinDamage(int inputMinDamage){this.minDamage = inputMinDamage;}

    // Getter for min damage.
    public int getMinDamage(){return this.minDamage;}

    // Setter for player hp.
    public void setMaxHP(int inputHP){this.maxHP = inputHP;}

    // Getter for hp
    public int getMaxHP(){return this.maxHP;}

    // Setter for current hp.
    public void setCurrentHP(int inputHP){this.currentHP = inputHP;}

    // Getter for current hp.
    public int getCurrentHP(){return this.currentHP;}

    // Setter for playerWeapon.
    public void setPlayerWeapon(String input){this.playerWeapon = input;}

    // Getter for playerWeapon.
    public String getPlayerWeapon(){return this.playerWeapon;}
}