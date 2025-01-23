package combat;

import enemys.Enemy;
import player.Player;

import java.util.Random;

public class Attack {
    public static Random random = new Random();

    // Void to calculate damage based on max and min damage.
    public static void attackEnemy(Player player, Enemy enemy){
        int damagePlayer = random.nextInt(player.getMaxDamage());

        // Ensure the damage is not below the minimum damage
        if (damagePlayer < player.getMinDamage()) {
            damagePlayer = player.getMinDamage();
        }
        enemy.setCurrentHP(enemy.getCurrentHP() - damagePlayer);
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You have hit the " +enemy.getEnemyName()+ " for " +damagePlayer+ " damage!" );
        System.out.println("------------------------------------------------------------------\n");
    }

    // Void to calculate damage to player based on min and max hp
    public static void attackPlayer(Player player, Enemy enemy){
        int damageEnemy = random.nextInt(enemy.getMaxDamage());

        // Ensure the damage is not below the minimum damage
        if (damageEnemy < enemy.getMinDamage()) {
            damageEnemy = enemy.getMinDamage();
        }
        player.setCurrentHP(player.getCurrentHP() - damageEnemy);
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("The " +enemy.getEnemyName()+ " hit you for " +damageEnemy+ " damage!" );
        System.out.println("------------------------------------------------------------------\n");
    }
}
