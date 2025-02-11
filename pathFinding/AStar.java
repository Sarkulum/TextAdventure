package pathFinding;

import enemys.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class AStar {
    // 1D List of ints that takes a 2D char array the position if the enemy and the player
    public List<int[]> aStar(String[][] grid, int startRow, int startCol, int targetRow, int targetCol) {

        // Linked list that also takes priority and spits out the most important node
        PriorityQueue<Node> openSet = new PriorityQueue<>();

        // 2D array to marke the visited and not visited fields
        boolean[][] closedSet = new boolean[grid.length][grid[0].length];

        // Adds a node with enemy start position idk what the 0 does and then calls a function that estimates how far the enemy is from the player(heuristic)
        openSet.add(new Node(startRow, startCol, 0, heuristic(startRow, startCol, targetRow, targetCol), null));

        // 2D array of all possible movements
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        // While the set is not empty
        while (!openSet.isEmpty()) {
            // Sets Node current to the node with the lowest movement cost
            Node current = openSet.poll();

            // If the node has the same row and col as target then it found the shortest path
            if (current.row == targetRow && current.col == targetCol) {
                return reconstructPath(current); // return the shortest path
            }

            closedSet[current.row][current.col] = true; // Mark node as visited if it does not return.

            // Extracts one inner array of the 2D array in "directions" at a time and then "dir" holds said array.
            for (int[] dir : directions) {
                int newRow = current.row + dir[0]; // 0 is the first position in the extracted array dir
                int newCol = current.col + dir[1]; // 1 is the second position in the extracted array dir

                // if just checks if there is a '#' in the spot that the enemy is trying to move to
                if (isValidMove(grid, newRow, newCol, closedSet)) {
                    // Basically this just adds a new nod if movement is valid
                    int newG = current.gCost + 1; // This adds 1 to gCost as you move 1 space further
                    int newH = heuristic(newRow, newCol, targetRow, targetCol); // Calculates a new estimit for the distance to target
                    openSet.add(new Node(newRow, newCol, newG, newH, current)); // Adds a new node
                }
            }
        }
        return null; // No path found
    }

    private int heuristic(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2); // Does not allow vertical moves, math.abs is the absolute different between the 2 numbers, Calculates the distance between 2 point on the array.
    }

    private boolean isValidMove(String[][] grid, int row, int col, boolean[][] closedSet) {
        // First checks if move is in bounds and then checks if there is an obstacle
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length
                && !Objects.equals(grid[row][col], "[#]") && !closedSet[row][col];
    }

    // This is the function I actually call when wanting to move the enemy
    public void moveEnemyAStar(String[][] grid, int movementLimit, Enemy enemy) {
        int id = enemy.getEnemyID();
        int[] enemyPosition = findEnemy(grid, enemy);
        int enemyRow = enemyPosition[0];
        int enemyCol = enemyPosition[1];

        int[] playerPos = findPlayer(grid); // Finds player coordinates
        if (playerPos == null) return;

        // Find the path to the player
        List<int[]> path = aStar(grid, enemyRow, enemyCol, playerPos[0], playerPos[1]);
        if (path != null && path.size() > 1) {
            // Get the next step in the path
            int[] nextStep = path.get(1);

            // Calculate the distance between the enemy's current position and the next step
            int distance = Math.abs(nextStep[0] - enemyRow) + Math.abs(nextStep[1] - enemyCol);

            // If the movement distance is less than or equal to the movement limit, move the enemy
            if (distance <= movementLimit) {
                grid[enemyRow][enemyCol] = "[ ]"; // Clear the old position
                grid[nextStep[0]][nextStep[1]] = "["+id+"]"; // Move the enemy
            } else {
                // If the movement is greater than the limit, move the enemy only by the movement limit
                int[] limitedStep = getLimitedStep(enemyRow, enemyCol, nextStep[0], nextStep[1], movementLimit);
                grid[enemyRow][enemyCol] = "[ ]"; // Clear the old position
                grid[limitedStep[0]][limitedStep[1]] = "["+id+"]"; // Move the enemy within the movement limit
            }
        }
    }

    public boolean movePlayer(String[][] grid, int currentRow, int currentCol, int targetRow, int targetCol, int movementLimit) {
        // Calculate the distance (Manhattan distance)
        int distance = Math.abs(targetRow - currentRow) + Math.abs(targetCol - currentCol);

        // Check if the movement is within the movement limit
        if (distance <= movementLimit) {
            // If within limit, move the player
            grid[currentRow][currentCol] = "[ ]"; // Clear old position
            grid[targetRow][targetCol] = "[P]";  // Set new position (P for player)
            return true;
        } else {
            // If the movement exceeds the limit, calculate a closer position
            int[] limitedPosition = getLimitedStep(currentRow, currentCol, targetRow, targetCol, movementLimit);
            grid[currentRow][currentCol] = "[ ]"; // Clear old position
            grid[limitedPosition[0]][limitedPosition[1]] = "[P]"; // Set the limited position
            return false;
        }
    }

    private int[] getLimitedStep(int currentRow, int currentCol, int targetRow, int targetCol, int movementLimit) {
        int rowDiff = targetRow - currentRow;
        int colDiff = targetCol - currentCol;

        // Normalize the direction and apply the movement limit
        if (Math.abs(rowDiff) > Math.abs(colDiff)) {
            // Prioritize row movement (vertical)
            int step = (rowDiff != 0) ? (int) Math.signum(rowDiff) * Math.min(movementLimit, Math.abs(rowDiff)) : 0;
            targetRow = currentRow + step;
        } else {
            // Prioritize column movement (horizontal)
            int step = (colDiff != 0) ? (int) Math.signum(colDiff) * Math.min(movementLimit, Math.abs(colDiff)) : 0;
            targetCol = currentCol + step;
        }

        return new int[]{targetRow, targetCol};
    }

    // Basically don't need this, but I could revers the path lol
    private List<int[]> reconstructPath(Node node) {
        List<int[]> path = new ArrayList<>();
        while (node != null) {
            path.add(0, new int[]{node.row, node.col}); // Add to start of the list
            node = node.parent;
        }
        return path;
    }

    // Just searches the hole array for 'P' wich means player
    public int[] findPlayer(String[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (Objects.equals(grid[row][col], "[P]")) {
                    return new int[]{row, col}; // Return coordinates of player
                }
            }
        }
        return null; // Player not found (should not happen if player exists in the grid)
    }

    // Just searches the hole array for 'enemyid' wich means enemy
    public int[] findEnemy(String[][] grid, Enemy enemy) {
        int target = enemy.getEnemyID();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col].equals("[" + target + "]")) {
                    return new int[]{row, col}; // Return coordinates of enemy
                }
            }
        }
        return null; // Enemy not found (should not happen if enemy exists in the grid)
    }

    // Check if player is adjacent to enemy
    public boolean isPlayerAdjacentToEnemy(String[][] map, Enemy enemy) {
        int[] player = findPlayer(map);
        int[] enemyPosition = findEnemy(map, enemy);

        int playerRow = player[0];
        int playerCol = player[1];
        int enemyRow = enemyPosition[0];
        int enemyCol = enemyPosition[1];

        // Check if player is adjacent to the enemy (within one unit distance in any direction)
        return (Math.abs(playerRow - enemyRow) <= 1 && Math.abs(playerCol - enemyCol) <= 1);
    }

    // Check if player is adjacent to enemy
    public boolean isEnemyAdjacentToPlayer(String[][] map, Enemy enemy) {
        int[] player = findPlayer(map);
        int[] enemyPosition = findEnemy(map, enemy);

        int playerRow = player[0];
        int playerCol = player[1];
        int enemyRow = enemyPosition[0];
        int enemyCol = enemyPosition[1];

        // Check if player is adjacent to the enemy (within one unit distance in any direction)
        return (Math.abs(enemyRow - playerRow) <= 1 && Math.abs(enemyCol - playerCol) <= 1);
    }

    /*
    [ ][ ][ ][ ]
    [#][#][#][#]
    [P][#][ ][E]
    [E][P][ ][#]
     */
}
