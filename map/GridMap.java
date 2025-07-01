package map;

import enemy.Enemy;
import enemy.EnemyManager;
import player.Player;
import player.PlayerManager;
import text.TextColor;
import java.util.*;

public class GridMap {
    String [][] map;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    EnemyManager enemyManager;

    public GridMap(int x, int y, EnemyManager enemyManager, List<Position> obstaclePositions, List<Position> playerPositions) {
        this.map = new String[x][y];
        this.enemyManager = enemyManager;

        // Fills the map with empty squares
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.map[i][j] = "[ ]";
            }
        }

        // For Loop to place obstacles. Using a List of Records which just save x and y
        for (Position position : obstaclePositions) {
            int obstacleX = position.x();
            int obstacleY = position.y();

            this.map[obstacleX][obstacleY] = "[#]";
        }

        // Places players
        for (Position position : playerPositions) {
            int playerX = position.x();
            int playerY = position.y();

            this.map[playerX][playerY] = "[P]";
        }

        // Places the enemys
        for (Map.Entry<Enemy, Position> entry : enemyManager.getEnemies().entrySet()) {
            Enemy enemy = entry.getKey();
            Position enemyPosition = entry.getValue();

            boolean placed = false;

            while (!placed) {
                int xEnemy = random.nextInt(x);
                int yEnemy = random.nextInt(y);

                if (Objects.equals(this.map[xEnemy][yEnemy], "[ ]")) {
                    this.map[xEnemy][yEnemy] = "[" +enemy.getIndex()+"]";
                    entry.setValue((new Position(xEnemy, yEnemy)));
                    placed = true;
                }
            }
        }
    }

    public void printMap() {
        Player player = PlayerManager.getInstance().getCurrentPlayer();
        int index = 0;

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[ ] = Empty Field, [#] = Obstacle, ['Number'] = Enemy, [P] = Player");

        // Print x coordinates
        System.out.print("  "); // Extra space for alignment
        for (int i = 0; i < this.map.length; i++) {
            System.out.print(" " + (i % 10) + " "); // Ensures proper spacing for alignment
        }

        for (int y = 0; y < this.map.length; y++) {
            // This prints the numbers at the beginning of each line
            System.out.print(" " + (y % 10) + " ");
            for (int x = 0; x < this.map.length; x++) {
                String cell = this.map[x][y];

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

    public void removePosition(Position position) {this.map[position.x()][position.y()] = "[ ]";}

    private void placePlayer(int x, int y) {this.map[x][y] = "[P]";}

    public void placeEnemy(Position position, int index) {this.map[position.x()][position.y()] = "["+index+"]";}

    public String checkPosition(Position position) {return this.map[position.x()][position.y()];}

    private void removeEnemy(int index) {
        Position position = enemyManager.getEnemyPosition(index);

        if (position != null) {
            int x = position.x();
            int y = position.y();

            map[x][y] = "[ ]";
        }
    }

    public String[][] getMap() {return this.map;}
}
