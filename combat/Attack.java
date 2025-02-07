package combat;

import enemys.Enemy;
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
    public void attackEnemy() {
        if (enemy == null) {
            System.out.println("No enemy set! Use setEnemy() to specify an enemy.");
            System.out.println("System: Press enter to continue.");
            enterScanner.nextLine();

            return;
        }


        int damagePlayer = random.nextInt(player.getMinDamage(), player.getMaxDamage());
        /* This SHOULD all not be needed, but maybe I am wrong, so I am keeping it.
        if (damagePlayer < player.getMinDamage()) {
            damagePlayer = player.getMinDamage();
        }
        */
        enemy.setCurrentHP(enemy.getCurrentHP() - damagePlayer);

        enemyName = enemy.getEnemyName();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You have hit the " + enemyName + " for " +Colors.RED +damagePlayer+" damage"+player.getUserTextColor()+"!");
        System.out.println("--------------------------->press enter to continue");
        enterScanner.nextLine();

        Enemy.cleanList();

        // Don't know if this is needed.
        if(!Enemy.specificEnemyAlive(enemy.getEnemyID())) {
            Enemy.dropGoldCoins(enemyName);
        }
    }

    // Enemy attacks the player
    public void attackPlayer() {
        if (player == null) {
            System.out.println("No player set! Use setPlayer() to specify a player.");
            System.out.println("System: Press enter to continue.");
            enterScanner.nextLine();
            return;
        }

        if(enemy != null) {
            int damageEnemy = random.nextInt(enemy.getMinDamage(), enemy.getMaxDamage());
            /* Same here, this SHOULD not be needed, but maybe I am stupid.
            if (damageEnemy < enemy.getMinDamage()) {
                damageEnemy = enemy.getMinDamage();
            }
             */
            player.setCurrentHP(player.getCurrentHP() - damageEnemy);

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You where hit by " + enemy.getEnemyName() + " for " + Colors.RED + damageEnemy + " damage" + player.getUserTextColor() + "!");
            System.out.println("--------------------------->press enter to continue");
            enterScanner.nextLine();

            player.playerAlive(this.enemy);
        }
    }
}
