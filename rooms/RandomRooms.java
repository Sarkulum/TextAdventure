package rooms;

import combat.Attack;
import enemys.Enemy;
import enemys.PercentBasedEnemy;
import map.GridMap;
import pathFinding.AStar;
import player.Player;
import player.PlayerDecision;

import java.util.Random;
import java.util.Scanner;

public class RandomRooms {
    public int index;
    Scanner enterScanner = new Scanner(System.in);
    Player player = Player.getPlayer("ID1");
    GridMap gridMap = GridMap.makeMap(10);
    Random random = new Random();
    AStar aStar = new AStar();

    public void setRandomRoom(int maxEnemy){
        gridMap.placePlayer(9, 4);
        index = maxEnemy;
        for(int i = 0; maxEnemy > i; i++){
            int id = PercentBasedEnemy.spawnEnemy();
            Enemy enemy = Enemy.getEnemy(id);
            boolean placed = false;

            while (!placed) {
                placed = gridMap.placeEnemy(random.nextInt(10), random.nextInt(10), enemy);
            }
        }
        // Access the singleton instance of Attack
        Attack combat = Attack.getInstance();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: You have entered a room with "+index+" enemy's inside.");
        System.out.println("--------------------------->press enter to continue\n");

        enterScanner.nextLine();


        while (Enemy.anyEnemyAlive(maxEnemy)){
            gridMap.printMap(false);

            // this seems a bit fucking weird
            for (int i = 0; i < index; i++) {
                if (!Enemy.specificEnemyAlive(i)){
                    index--;
                }
            }

            // Changed some shit here if no worky set i = -1 back to i = 0
            for (int i = 0; i < maxEnemy; i++) {
                Enemy currentEnemy = Enemy.getEnemy(i);
                if (currentEnemy != null) {
                    aStar.moveEnemyAStar(gridMap.getRoomMap(), currentEnemy.getMovement(), currentEnemy);
                    combat.setEnemy(currentEnemy);
                    combat.attackPlayer(gridMap);
                }
            }

            gridMap.printMap(true);
            int[] playerMove = PlayerDecision.getPlayerInput();
            int[] playerLocation = aStar.findPlayer(gridMap.getRoomMap());
            aStar.movePlayer(gridMap.getRoomMap(), playerLocation[0], playerLocation[1], playerMove[0], playerMove[1], player.getMovementSpeed());
            gridMap.printMap(true);

            do {
                Enemy actuallyEnemy = PlayerDecision.pickEnemy();
                combat.setEnemy(actuallyEnemy);
            } while (!combat.validEnemy());

            combat.attackEnemy(gridMap);
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: You have cleared this room and will now move to the next one.");
        System.out.println("--------------------------->press enter to continue\n");
        enterScanner.nextLine();
        Enemy.removeAllEntrys();
        String[][] map = gridMap.getRoomMap();
        gridMap.cleanMap(map.length);
    }
}
