package unused;

import java.util.HashMap;
import java.util.Map;

public class PlayerOption {
    private static Map<String, PlayerOption> players = new HashMap<>(); // Registry of all players

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
    private PlayerOption(String userName, int userAge, String userTextColor, int maxHP, int minDamage, int maxDamage, String playerWeapon, boolean DEV) {
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
    public static PlayerOption createPlayer(String userName, int userAge, String userTextColor, int maxHP, int minDamage, int maxDamage, String playerWeapon, boolean DEV) {
        if (!players.containsKey(userName)) {
            PlayerOption player = new PlayerOption(userName, userAge, userTextColor, maxHP, minDamage, maxDamage, playerWeapon, DEV);
            players.put(userName, player);
        }
        return players.get(userName);
    }

    // Method to retrieve a player by name
    public static PlayerOption getPlayer(String userName) {
        return players.get(userName);
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    // idk what this does
    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", maxHP=" + maxHP +
                ", currentHP=" + currentHP +
                ", minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +
                '}';
    }
}
