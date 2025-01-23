package enemys;

public class Enemy {
    // Vars for all relevant Enemy stats.
    public String name = "empty";
    public int maxDamage;
    public int minDamage;
    public int maxHP;
    public int currentHP;

    // Setter for enemy name.
    public void setName(String inputName){this.name = inputName;}

    // Getter for enemy name.
    public String getEnemyName() {
        return this.name;
    }

    // Setter for enemy max damage.
    public void setMaxDamage(int inputMaxDamage){this.maxDamage = inputMaxDamage;}

    // Getter for Max Damage.
    public int getMaxDamage(){return this.maxDamage;}

    // Setter for enemy min damage.
    public void setMinDamage(int inputMinDamage){this.minDamage = inputMinDamage;}

    // Getter for min damage.
    public int getMinDamage(){return this.minDamage;}

    // Setter for enemy hp.
    public void setMaxHP(int inputHP){this.maxHP = inputHP;}

    // Getter for hp
    public int getMaxHP(){return this.maxHP;}

    // Setter for current hp.
    public void setCurrentHP(int inputHP){this.currentHP = inputHP;}

    // Getter for current hp.
    public int getCurrentHP(){return this.currentHP;}
}
