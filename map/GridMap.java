package map;

import enemy.Enemy;
import enemy.EnemyManager;
import player.Player;
import player.PlayerManager;
import text.TextColor;
import java.util.*;

public class GridMap {
    String [][] room;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    EnemyManager enemyManager;

    public GridMap(int x, int y, EnemyManager enemyManager, List<Position> obstaclePositions, List<Position> playerPositions) {
        this.room = new String[x][y];
        this.enemyManager = enemyManager;

        // Fills the map with empty squares
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.room[i][j] = "[ ]";
            }
        }

        // For Loop to place obstacles. Using a List of Records which just save x and y
        for (Position position : obstaclePositions) {
            int obstacleX = position.x();
            int obstacleY = position.y();

            this.room[obstacleX][obstacleY] = "[#]";
        }

        // Places players
        for (Position position : playerPositions) {
            int playerX = position.x();
            int playerY = position.y();

            this.room[playerX][playerY] = "[P]";
        }

        // Places the enemys
        for (Map.Entry<Enemy, Position> entry : enemyManager.getEnemies().entrySet()) {
            Enemy enemy = entry.getKey();
            Position enemyPosition = entry.getValue();

            boolean placed = false;

            while (!placed) {
                int xEnemy = random.nextInt(x);
                int yEnemy = random.nextInt(y);

                if (Objects.equals(this.room[xEnemy][yEnemy], "[ ]")) {
                    this.room[xEnemy][yEnemy] = "[" +enemy.getIndex()+"]";
                    entry.setValue((new Position(xEnemy, yEnemy)));
                    placed = true;
                }
            }
        }
    }

    private void printMap() {
        Player player = PlayerManager.getInstance().getCurrentPlayer();
        int index = 0;

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[ ] = Empty Field, [#] = Obstacle, ['Number'] = Enemy, [P] = Player");

        // Print x coordinates
        System.out.print("  "); // Extra space for alignment
        for (int i = 0; i < this.room.length; i++) {
            System.out.print(" " + (i % 10) + " "); // Ensures proper spacing for alignment
        }

        for (int y = 0; y < this.room.length; y++) {
            // This prints the numbers at the beginning of each line
            System.out.print(" " + (y % 10) + " ");
            for (int x = 0; x < this.room.length; x++) {
                String cell = this.room[x][y];

                switch (cell) {
                    case "[ ]" -> System.out.print("[ ]");
                    case "[P]" -> System.out.print(TextColor.GREEN + "[P]" + player.getUserTextColor());
                    case "[#]" -> System.out.print(TextColor.GRAY + "[#]" + player.getUserTextColor());
                    case null, default ->  System.out.print(TextColor.RED + cell + player.getUserTextColor());
                }
            }

            try {
                Enemy enemy = enemyManager.getEnemyByIndex(index);

                if (enemy.getCurrentHP() > 0) {
                    System.out.println("Enemy: " +enemy.getName()+ " ID: " +enemy.getIndex()+ " HP: " +enemy.getCurrentHP());
                }
            } catch (Exception e) {

            }

        }

    }

    private void placePlayer(int x, int y) {this.room[x][y] = "[P]";}

    // TODO
    private void removeEnemy(int index) {
        Position position = enemyManager.getEnemyPosition(index);


    }
}
