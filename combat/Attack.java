package combat;

import enemys.Enemy;
import items.Weapon;
import map.GridMap;
import pathFinding.AStar;
import rooms.Shop;
import rooms.Tutorial;
import player.Player;
import text.Colors;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Attack {
    private static Attack instance; // Singleton instance
    private Player player;          // Fixed Player
    private Enemy enemy;            // Dynamic Enemy
    private static Random random = new Random(); // Random
    Scanner enterScanner = new Scanner(System.in);
    AStar aStar = new AStar();

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

    // Method to get the singleton instance and check if player is set
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
    public void attackEnemy(GridMap gridMap) {
        if (enemy == null) {return;}

        Weapon weapon = Weapon.getWeapon(Weapon.getEquippedWeaponName());
        if (aStar.isPlayerAdjacentToEnemy(gridMap, enemy, weapon.getRange())) {
            int damagePlayer = random.nextInt(player.getMinDamage() + weapon.getBonusMinDamage(), player.getMaxDamage() + weapon.getBonusMaxDamage());
            enemy.setCurrentHP(enemy.getCurrentHP() - damagePlayer);

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You have hit the " + enemy.getEnemyName() + " for " + Colors.RED + damagePlayer + " damage" + player.getUserTextColor() + "!");
            System.out.println("--------------------------->press enter to continue");
            enterScanner.nextLine();

            Enemy.cleanList();

            // Don't know if this is needed.
            if(!Enemy.specificEnemyAlive(enemy.getEnemyID())) {
                String[][] map = gridMap.getRoomMap();
                int[] enemyPosition = aStar.findEnemy(map, enemy);
                gridMap.removeEnemy(enemyPosition[0], enemyPosition[1],enemy);

                Enemy.dropGoldCoins(enemy.getEnemyName());
            }
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You are not close enough to the enemy to attack.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
        }
    }

    // Enemy attacks the player
    public void attackPlayer(GridMap gridMap) {
        String[][] map = gridMap.getRoomMap();
        // Check if player is set in instance
        if (player == null) {
            System.out.println("No player set! Use setPlayer() to specify a player.");
            System.out.println("System: Press enter to continue.");
            enterScanner.nextLine();
            return;
        }

        if (aStar.isEnemyAdjacentToPlayer(map, enemy, enemy.getRange())) {
            if (enemy != null) {
                int damageEnemy = random.nextInt(enemy.getMinDamage(), enemy.getMaxDamage());
                player.setCurrentHP(player.getCurrentHP() - damageEnemy);

                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("You where hit by " + enemy.getEnemyName() + " for " + Colors.RED + damageEnemy + " damage" + player.getUserTextColor() + "!");
                System.out.println("--------------------------->press enter to continue");
                enterScanner.nextLine();

                deathMessage(gridMap);
            }
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("The enemy is not close enough to attack you.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
        }
    }

    public void deathMessage(GridMap gridMap) {
        // If player has passed Tutuo. They respawn at the shop
        if(player.getTutorialPassed() && !player.playerAlive()){
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You have died to "+enemy.getEnemyName()+". You will now respawn at the Shop.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            String[][] map = gridMap.getRoomMap();
            gridMap.cleanMap(map.length);
            player.setCurrentHP(player.getMaxHP());
            player.setPlayerWeapon("Fist");
            Shop.buyUpgrades();
        }else if(!player.playerAlive()){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You have died to a "+enemy.getEnemyName()+". You will now respawn at the beginning.");
            System.out.println("--------------------------->press enter to continue\n");

            enterScanner.nextLine();
            String[][] map = gridMap.getRoomMap();
            gridMap.cleanMap(map.length);
            player.setCurrentHP(player.getMaxHP());
            player.setPlayerWeapon("Fist");
            Tutorial.townGate();
        }
    }

    // Methode to check if enemy is not null
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
