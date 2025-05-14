package items;

public class Weapon extends Item {
    private final int minDamage;
    private final int maxDamage;
    private final int range;
    private final int armorPen;

    public Weapon(String name, int minDamage, int maxDamage, int range, int armorPen) {
        super(name);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.range = range;
        this.armorPen = armorPen;
    }

    public int getMinDamage() {return minDamage;}

    public int getMaxDamage() {return maxDamage;}
}
