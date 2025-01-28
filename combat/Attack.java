package combat;

import enemys.Enemy;
import player.Player;

import java.util.Random;

public class Attack {
    private static Attack instance; // Singleton instance
    private Player player;          // Fixed Player
    private Enemy enemy;            // Dynamic Enemy
    private static Random random = new Random();

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
            return;
        }

        int damagePlayer = random.nextInt(player.getMaxDamage());
        if (damagePlayer < player.getMinDamage()) {
            damagePlayer = player.getMinDamage();
        }
        enemy.setCurrentHP(enemy.getCurrentHP() - damagePlayer);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You have hit the " + enemy.getEnemyName() + " for " + damagePlayer + " damage!");
        System.out.println("------------------------------------------------------------------\n");
    }

    // Enemy attacks the player
    public void attackPlayer() {
        if (player == null) {
            System.out.println("No enemy set! Use setPlayer() to specify an player.");
            return;
        }

        int damageEnemy = random.nextInt(enemy.getMaxDamage());
        if (damageEnemy < enemy.getMinDamage()) {
            damageEnemy = enemy.getMinDamage();
        }
        player.setCurrentHP(player.getCurrentHP() - damageEnemy);

        player.playerAlive(this.enemy);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You have died to "+enemy.getEnemyName()+". You will now respawn at your latest checkpoint.");
        System.out.println("------------------------------------------------------------------\n");
    }
}
