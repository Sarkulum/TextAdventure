package enemys;

public class Enemy {
    // Vars for all relevant Enemy stats.
    public String name = "empty";
    public int maxDamage;
    public int minDamage;
    public int maxHP;
    public int currentHP;

    // Constructor to initialize the enemy
    public Enemy(String name, int maxDamage, int minDamage, int maxHP) {
        this.name = name;             // Assign the name
        this.maxDamage = maxDamage;   // Assign maximum damage
        this.minDamage = minDamage;   // Assign minimum damage
        this.maxHP = maxHP;           // Assign maximum HP
        this.currentHP = maxHP;       // Set current HP to max HP by default
    }

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
}
