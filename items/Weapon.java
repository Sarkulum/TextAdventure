package items;

import player.Player;

import java.util.HashMap;
import java.util.Map;

public class Weapon {
    private static Map<String, Weapon> weapons = new HashMap<>(); // Registry of all weapons the player has

    String name;
    int amourPen;
    int bonusMaxDamage;
    int bonusMinDamage;
    int range;
    boolean equipped;

    private Weapon(String name, int amourPen, int bonusMinDamage, int bonusMaxDamage, int range) {
        this.name = name;
        this.amourPen = amourPen;
        this.bonusMinDamage = bonusMinDamage;
        this.bonusMaxDamage = bonusMaxDamage;
        this.range = range;
    }

    public static Weapon creatWeapon(String name, int amourPen, int bonusMinDamage, int bonusMaxDamage, int range) {
        if (!weapons.containsKey(name)) {
            Weapon weapon = new Weapon(name, amourPen, bonusMinDamage, bonusMaxDamage, range);
            weapons.put(name, weapon);
        }
        return weapons.get(name);
    }

    public static void equipWeapon(String weaponName) {
        for (Weapon weapon : weapons.values()) {
            weapon.setEquipped(weapon.getName().equals(weaponName));
        }
    }

    public static String getEquippedWeaponName() {
        for (Weapon weapon : weapons.values()) {
            if (weapon.isEquipped()) {
                return weapon.getName();
            }
        }
        return null; // No weapon is equipped
    }

    public static Weapon getWeapon(String name) {
        return weapons.get(name);
    }

    public static Map<String, Weapon> getWeapons() {return weapons;}

    public static boolean hasWeapon(String name) {return weapons.containsKey(name);}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public int getAmourPen() {return this.amourPen;}
    public void setAmourPen(int amourPen) {this.amourPen = amourPen;}

    public int getBonusMaxDamage() {return this.bonusMaxDamage;}
    public void setBonusMaxDamage(int bonusMaxDamage) {this.bonusMaxDamage = bonusMaxDamage;}

    public int getBonusMinDamage() {return this.bonusMinDamage;}
    public void setBonusMinDamage(int bonusMinDamage) {this.bonusMinDamage = bonusMinDamage;}

    public int getRange() {return this.range;}
    public void setRange(int range) {this.range = range;}

    public boolean isEquipped() {return this.equipped;}
    public void setEquipped(boolean equipped) {this.equipped = equipped;}
}
