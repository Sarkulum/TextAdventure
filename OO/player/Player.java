package OO.player;

import OO.combat.Attackable;
import OO.combat.Damage;
import OO.creature.Creature;
import OO.items.Item;
import OO.items.Weapon;
import OO.text.TextColor;

import java.util.ArrayList;
import java.util.List;

public class Player extends Creature implements Attackable {
    private int userAge;
    private TextColor userTextColor;
    private List<Item> inventory = new ArrayList<Item>(); // List to save Item object in.
    private int score;
    private int randomRoomIndex = 1;
    private boolean died = false;
    private boolean DEV;
    private Weapon equippedWeapon;
    private int playerID;

    public Player(
            String name,
            int minDamage,
            int maxDamage,
            int maxHP,
            int movement,
            int range,
            int userAge,
            TextColor userTextColor,
            int score
    ) {
        super(name, minDamage, maxDamage, maxHP, movement, range);
        this.userAge = userAge;
        this.userTextColor = userTextColor;
        this.score = score;

        // Automatically set DEV mode if name is "DEV"
        this.DEV = name.equalsIgnoreCase("DEV");
    }

    // Adds an entry to the inventory List
    public void addItem(Item item) {
        inventory.add(item);
    }

    // A way to equip a Weapon.
    public void equipWeapon(String weaponName) {
        // Iterates over the Inventory List
        for (Item item : inventory) {
            // Takes the Weapon object with the name equal to the input String
            if (item instanceof Weapon && item.getName().equals(weaponName)) {
                equippedWeapon = (Weapon) item;
                System.out.println(name + " equipped the " + weaponName + "!");
                return;
            }
        }
        System.out.println("Weapon not found in inventory.");
    }

    // A way to get the equip Weapon to calculate damage.
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    // Way to check if player has a certain Item (story item etc.)
    public boolean playerHasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void takeDamage(Damage damage) {
        currentHP -= damage.getAmount();
        System.out.println("Player took " + damage.getAmount() + " damage!");
    }

    public int getUserAge() {return userAge;}
    public void setUserAge(int userAge) {this.userAge = userAge;}

    public TextColor getUserTextColor() {return userTextColor;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public int getRandomRoomIndex() {return randomRoomIndex;}
    public void setRandomRoomIndex(int randomRoomIndex) {this.randomRoomIndex = randomRoomIndex;}

    public boolean isDied() {return died;}
    public void setDied(boolean died) {this.died = died;}

    public boolean isDEV() {return DEV;}
    public void setDEV(boolean DEV) {this.DEV = DEV;}
}
