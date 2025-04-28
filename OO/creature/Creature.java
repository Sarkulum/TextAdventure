package OO.creature;

public class Creature {
    private String name;
    private int maxDamage;
    private int minDamage;
    private int maxHP;
    private int currentHP;
    private int movement;
    private int range;
    private int goldCoins;

    public Creature(String name, int minDamage, int maxDamage, int maxHP, int movement, int range) {
        this.name = name;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.maxHP = maxHP;
        this.currentHP = maxHP; // Set current HP to max HP by default
        this.movement = movement;
        this.range = range;
        this.goldCoins = 0; // Initialize gold coins to 0
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getMaxDamage() {return maxDamage;}
    public void setMaxDamage(int maxDamage) {this.maxDamage = maxDamage;}

    public int getMinDamage() {return minDamage;}
    public void setMinDamage(int minDamage) {this.minDamage = minDamage;}

    public int getMaxHP() {return maxHP;}
    public void setMaxHP(int maxHP) {this.maxHP = maxHP;}

    public int getCurrentHP() {return currentHP;}
    public void setCurrentHP(int currentHP) {this.currentHP = currentHP;}

    public int getMovement() {return movement;}
    public void setMovement(int movement) {this.movement = movement;}

    public int getRange() {return range;}
    public void setRange(int range) {this.range = range;}

    public int getGoldCoins() {return goldCoins;}
    public void setGoldCoins(int goldCoins) {this.goldCoins = goldCoins;}
}