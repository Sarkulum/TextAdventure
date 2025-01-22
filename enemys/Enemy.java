package enemys;

public class Enemy {
    public String name = "empty";
    public int maxDamage;
    public int minDamage;
    public int hp;

    public Enemy() { };

    public String getName() {
        return name;
    }

    // Setter for enemy name.
    public void setName(String inputName){this.name = inputName;}

    // Setter for enemy max damage.
    public void setMaxDamage(int inputMaxDamage){this.maxDamage = inputMaxDamage;}

    // Setter for enemy min damage.
    public void setMinDamage(int inputMinDamage){this.minDamage = inputMinDamage;}

    // Setter for enemy hp.
    public void setHp(int inputHP){this.hp = inputHP;}
}
