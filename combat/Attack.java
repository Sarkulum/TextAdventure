package combat;

import enemys.Enemy;
import items.Weapon;
import map.Map;
import pathFinding.AStar;
import rooms.Shop;
import rooms.Tutorial;
import player.Player;
import text.Colors;

import java.util.Random;
import java.util.Scanner;

public class Attack {
    private static Attack instance; // Singleton instance
    private Player player;          // Fixed Player
    private Enemy enemy;            // Dynamic Enemy
    private static Random random = new Random();
    private static String enemyName;
    Scanner enterScanner = new Scanner(System.in);
    AStar aStar = new AStar();
    Map map;

    // Private constructor to prevent direct instantiation
    private Attack(Player player) {
        this.player = player;
    }

    // Method to initialize the singleton instance with a Player
    public static void initialize(Player player) {
        if (instance == null) {
            instance = new Attack(player);
        }
    }

    // Method to get the singleton instance
    public static Attack getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Attack has not been initialized with a Player!");
        }
        return instance;
    }

    // Setter to dynamically update the enemy
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    // Player attacks the enemy
    public void attackEnemy(String[][] map) {
        if (enemy == null) {return;}

        if (aStar.isPlayerAdjacentToEnemy(map, enemy)) {
            String weaponName = Weapon.getEquippedWeaponName();
            Weapon weapon = Weapon.getWeapon(weaponName);
            int damagePlayer = random.nextInt(player.getMinDamage() + weapon.getBonusMinDamage(), player.getMaxDamage() + weapon.getBonusMaxDamage());
            enemy.setCurrentHP(enemy.getCurrentHP() - damagePlayer);

            enemyName = enemy.getEnemyName();

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You have hit the " + enemyName + " for " + Colors.RED + damagePlayer + " damage" + player.getUserTextColor() + "!");
            System.out.println("--------------------------->press enter to continue");
            enterScanner.nextLine();

            Enemy.cleanList();

            // Don't know if this is needed.
            if(!Enemy.specificEnemyAlive(enemy.getEnemyID())) {
                Enemy.dropGoldCoins(enemyName);
            }
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You are not close enough to the enemy to attack.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
        }
    }

    // Enemy attacks the player
    public void attackPlayer(String[][] map) {
        if (player == null) {
            System.out.println("No player set! Use setPlayer() to specify a player.");
            System.out.println("System: Press enter to continue.");
            enterScanner.nextLine();
            return;
        }

        if (aStar.isEnemyAdjacentToPlayer(map, enemy)) {
            if (enemy != null) {
                int damageEnemy = random.nextInt(enemy.getMinDamage(), enemy.getMaxDamage());
                player.setCurrentHP(player.getCurrentHP() - damageEnemy);

                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("You where hit by " + enemy.getEnemyName() + " for " + Colors.RED + damageEnemy + " damage" + player.getUserTextColor() + "!");
                System.out.println("--------------------------->press enter to continue");
                enterScanner.nextLine();

                deathMessage();
            }
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The enemy is not close enough to attack you.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
        }
    }

    public void deathMessage() {
        if(player.getTutorialPassed() && !player.playerAlive()){
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You have died to "+enemy.getEnemyName()+". You will now respawn at the Shop.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            player.setCurrentHP(player.getMaxHP());
            player.setPlayerWeapon("Fist");
            Shop.buyUpgrades();
        }else if(!player.playerAlive()){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You have died to a "+enemy.getEnemyName()+". You will now respawn at the beginning.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            player.setCurrentHP(player.getMaxHP());
            player.setPlayerWeapon("Fist");
            Tutorial.townGate();
        }
    }

    public boolean validEnemy() {
        if (enemy == null) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("The enemy you chose is either dead or you have schizophrenia.");
            System.out.println("Please pick a different one.");

            return false;
        }
        return true;
    }
}
