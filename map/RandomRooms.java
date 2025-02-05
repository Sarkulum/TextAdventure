package map;

import combat.Attack;
import enemys.Enemy;
import enemys.PercentBasedEnemy;
import enemys.ZombieTypes;
import player.Player;
import player.PlayerDecision;
import text.Colors;
import java.util.Scanner;

public class RandomRooms {
    public int index;
    public int attackIndex = -1;
    Scanner enterScanner = new Scanner(System.in);
    Player player = Player.getPlayer("ID1");
    int choice;

    public void setRandomRoom(int maxEnemy){
        index = maxEnemy;
        for(int i = 0; maxEnemy > i; i++){
            PercentBasedEnemy.spawnEnemy();
        }
        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: You have entered a room with "+index+" enemy's inside.");
        System.out.println("--------------------------->press enter to continue\n");

        enterScanner.nextLine();

        while (Enemy.anyEnemyAlive(maxEnemy)){
            for (int i = 0; i < index; i++) {
                if (!Enemy.specificEnemyAlive(i)){
                    index--;
                    attackIndex--;
                }
            }
            // Changed some shit here if no worky set i = -1 back to i = 0
            for (int i = 0; i < maxEnemy; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                combat.setEnemy(currentEnemy);
                combat.attackPlayer();
            }
            // Make a for loop so every enemy gets printed by name
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("System: There are "+index+" in front of you. Who do you want to attack?");
            System.out.println("Enemy status:");

            for (int i = 0; i < maxEnemy; i++) {
                int j = i + 1;
                if (Enemy.specificEnemyAlive(i)){
                    System.out.println("Enemy: " + j + " = "+Colors.GREEN+"Alive"+player.getUserTextColor());
                }else {
                    System.out.println("Enemy: " + j + " = "+Colors.RED+"DEAD"+player.getUserTextColor());
                }
            }
            choice = PlayerDecision.inputWithCheck(maxEnemy) -1;

            Enemy currentEnemy = Enemy.getEnemy(choice);
            combat.setEnemy(currentEnemy);
            combat.attackEnemy();
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: You have cleared this room and will now move to the next one.");
        System.out.println("--------------------------->press enter to continue\n");
        enterScanner.nextLine();
        Enemy.removeAllEntrys();
    }
}
