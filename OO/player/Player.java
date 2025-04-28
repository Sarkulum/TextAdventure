package OO.player;

import OO.creature.Creature;

public class Player extends Creature {
    private int userAge;
    private String userTextColor;
    private String playerWeapon;
    private int inventory;
    private int score;
    private int randomRoomIndex = 1;
    private boolean died = false;
    private boolean DEV;

    public Player(
            String name,
            int minDamage,
            int maxDamage,
            int maxHP,
            int movement,
            int range,
            int userAge,
            int userTextColor,
            int playerWeapon,
            int inventory,
            int score,
            int randomRoomIndex,
            boolean died,
            boolean DEV
    ) {
        super(name, minDamage, maxDamage, maxHP, movement, range);
    }

    public int getUserAge() {return userAge;}
    public void setUserAge(int userAge) {this.userAge = userAge;}

    public String getUserTextColor() {return userTextColor;}
    public void setUserTextColor(String userTextColor) {this.userTextColor = userTextColor;}

    public String getPlayerWeapon() {return playerWeapon;}
    public void setPlayerWeapon(String playerWeapon) {this.playerWeapon = playerWeapon;}

    public int getInventory() {return inventory;}
    public void setInventory(int inventory) {this.inventory = inventory;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public int getRandomRoomIndex() {return randomRoomIndex;}
    public void setRandomRoomIndex(int randomRoomIndex) {this.randomRoomIndex = randomRoomIndex;}

    public boolean isDied() {return died;}
    public void setDied(boolean died) {this.died = died;}

    public boolean isDEV() {return DEV;}
    public void setDEV(boolean DEV) {this.DEV = DEV;}
}
