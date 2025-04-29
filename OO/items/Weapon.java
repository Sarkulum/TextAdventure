package OO.items;

public class Weapon extends Item {
    private int minDamage;
    private int maxDamage;

    public Weapon(String name, int minDamage, int maxDamage) {
        super(name);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getMinDamage() {return minDamage;}

    public int getMaxDamage() {return maxDamage;}
}
