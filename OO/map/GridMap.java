package OO.map;

import OO.enemy.Enemy;
import OO.enemy.EnemyManager;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GridMap {
    String [][] room;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public GridMap(int x, int y, EnemyManager enemyManager, List<Position> obstaclePositions) {
        this.room = new String[x][y];

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

        for (Enemy enemy : enemyManager.getEnemies()) {
            boolean placed = false;

            while (!placed) {
                int xEnemy = random.nextInt(x);
                int yEnemy = random.nextInt(y);

                if (Objects.equals(this.room[xEnemy][yEnemy], "[ ]")) {
                    this.room[xEnemy][yEnemy] = "[" +enemy.getIndex()+"]";
                    placed = true;
                }
            }
        }
    }

    private void placePlayer(int x, int y) {this.room[x][y] = "[P]";}
}
