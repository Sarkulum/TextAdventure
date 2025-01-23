package combat;

import enemys.Enemy;
import player.Player;

import java.util.Random;

public class Attack {
    public static Random random = new Random();

    // Void to calculate damage based on max and min damage.
    public static void attackEnemy(Player player, Enemy enemy){
        int remainingHP = enemy.getCurrentHP() - random.nextInt(player.getMaxDamage());

        if (remainingHP >= enemy.getCurrentHP() - player.getMinDamage()){
            enemy.setCurrentHP(remainingHP - player.getMinDamage());
        }else{
            enemy.setCurrentHP(remainingHP);
        }
    }
}
