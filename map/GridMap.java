package map;

import enemys.Enemy;
import pathFinding.AStar;
import player.Player;
import text.Colors;

import java.util.*;

public class GridMap {
    String[][] roomMap;
    Player player = Player.getPlayer("ID1");
    Scanner scanner = new Scanner(System.in);
    AStar AStar = new AStar();

    private GridMap(int size){
        this.roomMap = new String[size][size];

        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                this.roomMap[x][y] = "[ ]";
            }
        }
    }
    //Fill the 2D Array with # for empty fields and make it 10x10
    public static GridMap makeMap(int size) {
        return new GridMap(size);
    }

    public void placeObstical(int row, int col) {
        this.roomMap[row][col] = "[#]";
    }

    public boolean placeEnemy(int row, int col, Enemy enemy) {
        if (Objects.equals(this.roomMap[row][col], "[ ]")) {
            this.roomMap[row][col] = "["+enemy.getEnemyID()+"]" ;
            return true;
        }else{
            return false;
        }
    }

    public void placePlayer(int row, int col) {
        this.roomMap[row][col] = "[P]";
    }

    public String[][] getRoomMap() {
        return this.roomMap;
    }

    public void cleanMap() {
        this.roomMap = new String[10][10];

        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                this.roomMap[x][y] = "[ ]";
            }
        }
    }

    //A function to print the 2D Array in the console. Don't have a clue how it works.
    public void printMap(String[][] map, boolean isAttack){
        int rows = this.roomMap.length;
        int cols = this.roomMap[0].length;;

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[ ] = Empty Field, [#] = Obstacle, ['number'] = Enemy, [P] = Player");

        // Print column headers
        System.out.print("  "); // Extra space for alignment
        for (int col = 0; col < cols; col++) {
            System.out.print(" " + (col % 10) + " "); // Ensures proper spacing for alignment
        }
        System.out.println();

        // Get all enemies from the HashMap
        HashMap<Integer, Enemy> enemyMap = (HashMap<Integer, Enemy>) Enemy.getHashMap();
        String[] enemyInfo = new String[100];
        int index = 0;
        int enemysFound = 0;
        int enemyID = 0;

        for (int i = 0; i < enemyMap.size(); i++) {

            // Extract enemy ID
            try {
                //int enemyID = Integer.parseInt(cell.replace("[", "").replace("]", ""));

                // Check if enemy exists in the HashMap
                if (enemyMap.get(enemyID) != null) {
                    Enemy enemy = Enemy.getEnemy(enemyID);
                    String status = enemy.getCurrentHP() > 0 ? "Alive" : "Dead";
                    enemyInfo[enemysFound] = enemy.getEnemyName() + ": " + enemyID + " = " + status + " (" + enemy.getCurrentHP() + "HP)";
                    enemysFound++;
                    enemyID++;
                }
            } catch (NullPointerException e) {
                // Ignore if parsing fails
            }
        }

        // Print rows with headers and map content
        for (int row = 0; row < rows; row++) {
            System.out.print((row % 10) + " "); // Print row numbers for alignment
            for (int col = 0; col < cols; col++) {
                String cell = roomMap[row][col];

                if (Objects.equals(cell, "[ ]")) {
                    System.out.print("[ ]");
                } else if (Objects.equals(cell, "[P]")) {
                    System.out.print(Colors.GREEN + "[P]" + player.getUserTextColor());
                } else if (Objects.equals(cell, "[#]")) {
                    System.out.print(Colors.GRAY + "[#]" + player.getUserTextColor());
                } else { // Enemy found (formatted as "[X]")
                    System.out.print(Colors.RED + cell + player.getUserTextColor());
                }
            }

            if (enemyInfo[index] != null) {
                System.out.print("  " + enemyInfo[index]);
                index++;
            }

            System.out.println();
        }
        if (!isAttack) {
            System.out.println("--------------------------->press enter to continue\n");
            scanner.nextLine();
        }
    }

    public void removeEnemy(int row, int col, Enemy enemy) {
        // Ensure the position contains the correct enemy before removing
        if (Objects.equals(this.roomMap[row][col], "[" + enemy.getEnemyID() + "]")) {
            this.roomMap[row][col] = "[ ]"; // Reset to empty field
        }
    }
}
