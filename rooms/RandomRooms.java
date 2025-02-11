package rooms;

import combat.Attack;
import enemys.Enemy;
import enemys.PercentBasedEnemy;
import map.Map;
import pathFinding.AStar;
import player.Player;
import player.PlayerDecision;
import text.Colors;

import java.util.Random;
import java.util.Scanner;

public class RandomRooms {
    public int index;
    public int attackIndex = -1;
    Scanner enterScanner = new Scanner(System.in);
    Player player = Player.getPlayer("ID1");
    int choice;
    Map map = Map.makeMap(10);
    Random random = new Random();
    AStar aStar = new AStar();

    public void setRandomRoom(int maxEnemy){
        map.placePlayer(9, 4);
        index = maxEnemy;
        for(int i = 0; maxEnemy > i; i++){
            int id = PercentBasedEnemy.spawnEnemy();
            Enemy enemy = Enemy.getEnemy(id);
            boolean placed = false;

            while (!placed) {
                placed = map.placeEnemy(random.nextInt(10), random.nextInt(10), enemy);
            }
        }
        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: You have entered a room with "+index+" enemy's inside.");
        System.out.println("--------------------------->press enter to continue\n");

        enterScanner.nextLine();


        while (Enemy.anyEnemyAlive(maxEnemy)){
            map.printMap(map.getRoomMap());
            int[] playerMove = PlayerDecision.getPlayerInput();
            int[] playerLocation = aStar.findPlayer(map.getRoomMap());
            aStar.movePlayer(map.getRoomMap(), playerLocation[0], playerLocation[1], playerMove[0], playerMove[1], player.getMovementSpeed());

            // this seems a bit fucking weird
            for (int i = 0; i < index; i++) {
                if (!Enemy.specificEnemyAlive(i)){
                    index--;
                    attackIndex--;
                }
            }

            // Changed some shit here if no worky set i = -1 back to i = 0
            for (int i = 0; i < maxEnemy; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                aStar.moveEnemyAStar(map.getRoomMap(), 4, currentEnemy);
                combat.setEnemy(currentEnemy);
                combat.attackPlayer(map.getRoomMap());
            }

            // Make a for loop so every enemy gets printed by name
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("System: There are "+index+" enemy's in front of you. Who do you want to attack?");
            System.out.println("Enemy status:");

            for (int i = 0; i < maxEnemy; i++) {
                int j = i + 1;
                Enemy currentEnemy = Enemy.getEnemy(i);
                if (Enemy.specificEnemyAlive(i)){
                    System.out.println(currentEnemy.getEnemyName()+": " + j + " = "+Colors.GREEN+"Alive("+currentEnemy.getCurrentHP()+")"+player.getUserTextColor());
                }else {
                    System.out.println(currentEnemy.getEnemyName()+": " + j + " = "+Colors.RED+"DEAD"+player.getUserTextColor());
                }
            }

            choice = PlayerDecision.inputWithCheck(maxEnemy) - 1;

            Enemy currentEnemy = Enemy.getEnemy(choice);
            combat.setEnemy(currentEnemy);

            while (!combat.validEnemy()) {
                choice = PlayerDecision.inputWithCheck(maxEnemy) - 1;

                Enemy actuallyEnemy = Enemy.getEnemy(choice);
                combat.setEnemy(actuallyEnemy);
            }
            combat.attackEnemy(map.getRoomMap());
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: You have cleared this room and will now move to the next one.");
        System.out.println("--------------------------->press enter to continue\n");
        enterScanner.nextLine();
        Enemy.removeAllEntrys();
        map.cleanMap();
    }
}
