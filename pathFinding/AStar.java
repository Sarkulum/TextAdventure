package pathFinding;

import enemys.Enemy;
import java.util.*;

public class AStar {
    Scanner scanner = new Scanner(System.in);

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

            /*
            // If the node has the same row and col as target then it found the shortest path
            if (current.row == targetRow && current.col == targetCol) {
                return reconstructPath(current); // return the shortest path
            }
            */

            // Check if current node is adjacent to the target (Manhattan distance 1)
            if (heuristic(current.row, current.col, targetRow, targetCol) == 1) {
                return reconstructPath(current);
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
                && !Objects.equals(grid[row][col], "[#]") && !Objects.equals(grid[row][col], "[P]") && !closedSet[row][col];
    }

    //private boolean isValidMovePlay

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
            // Start moving the enemy step by step
            int distanceTraveled = 0; // Track how much movement we've used

            // Loop through the path and move as long as movement limit isn't reached
            for (int i = 1; i < path.size() && distanceTraveled < movementLimit; i++) {
                int[] currentStep = path.get(i);
                int distance = Math.abs(currentStep[0] - enemyRow) + Math.abs(currentStep[1] - enemyCol);

                // Move only if we haven't exceeded the movement limit
                if (distanceTraveled + distance <= movementLimit) {
                    grid[enemyRow][enemyCol] = "[ ]"; // Clear the old position
                    enemyRow = currentStep[0];
                    enemyCol = currentStep[1];
                    grid[enemyRow][enemyCol] = "["+id+"]"; // Move the enemy to the new position
                    distanceTraveled += distance; // Add distance moved
                } else {
                    break; // Stop if we've reached the movement limit
                }
            }
        }
    }

    public boolean movePlayer(String[][] grid, int currentRow, int currentCol, int targetRow, int targetCol, int movementLimit) {
        // Calculate the distance (Manhattan distance)
        int distance = Math.abs(targetRow - currentRow) + Math.abs(targetCol - currentCol);

        try {
            // Check if the movement is within the movement limit and within grid bounds
            if (distance <= movementLimit && isInBounds(grid, targetRow, targetCol)) {
                // If the target position is empty, move the player normally
                if (grid[targetRow][targetCol].equals("[ ]")) {
                    grid[currentRow][currentCol] = "[ ]"; // Clear old position
                    grid[targetRow][targetCol] = "[P]";  // Move player
                    return true;
                } else if (grid[targetRow][targetCol].equals("[P]")){
                    return true;
                }else{
                    // If the target position is occupied, move to the closest valid adjacent square
                    movePlayerToValidSpot(grid, currentRow, currentCol, targetRow, targetCol);
                    return true;
                }
            } else if (isInBounds(grid, targetRow, targetCol)) {
                // If the movement exceeds the limit, move as far as possible within the limit
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("You did not have enough movement for that so we placed you to the furthest point you could go.");
                System.out.println("--------------------------->press enter to continue\n");
                scanner.nextLine();
                int[] limitedPosition = getLimitedStep(currentRow, currentCol, targetRow, targetCol, movementLimit);
                movePlayerToValidSpot(grid, currentRow, currentCol, limitedPosition[0], limitedPosition[1]);
                return true;
            } else {
                System.out.println("You stupid fuck! You tried to move outside the grid.");
                System.out.println("Try again.");
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You stupid fuck! You tried to move outside the grid.");
            System.out.println("Try again.");
            return false;
        }
    }

    private int[] getLimitedStep(int currentRow, int currentCol, int targetRow, int targetCol, int movementLimit) {
        int rowDiff = targetRow - currentRow;
        int colDiff = targetCol - currentCol;

        // Normalize the direction and apply the movement limit, abs just removes stuff like - or so
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

    public void movePlayerToValidSpot(String[][] grid, int currentRow, int currentCol, int targetRow, int targetCol) {
        // If the target position is empty, move there
        if (grid[targetRow][targetCol].equals("[ ]")) {
            grid[currentRow][currentCol] = "[ ]"; // Clear old position
            grid[targetRow][targetCol] = "[P]";   // Move player to the new position
            return;
        }

        // Define possible adjacent positions (Up, Down, Left, Right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] bestPosition = null;
        int bestDistance = Integer.MAX_VALUE;

        // Find the closest valid adjacent position
        for (int[] dir : directions) {
            int newRow = targetRow + dir[0];
            int newCol = targetCol + dir[1];

            // Check if the new position is within bounds and empty
            if (isInBounds(grid, newRow, newCol) && grid[newRow][newCol].equals("[ ]")) {
                int distance = Math.abs(newRow - currentRow) + Math.abs(newCol - currentCol);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestPosition = new int[]{newRow, newCol};
                }
            }
        }

        // Move the player to the closest valid adjacent position if available
        if (bestPosition != null) {
            grid[currentRow][currentCol] = "[ ]"; // Clear old position
            grid[bestPosition[0]][bestPosition[1]] = "[P]"; // Move player
        }
    }

    // Helper function to check if a position is within the grid bounds
    private boolean isInBounds(String[][] grid, int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }

    public int findDistanceBetween(String[][] grid, Enemy enemy) {
        int[] enemyPos = findEnemy(grid, enemy);
        int[] playerPos = findPlayer(grid);

        if (enemyPos == null || playerPos == null) {
            System.out.println("Enemy or player not found on the grid.");
            return -1; // Return -1 to indicate an error
        }

        return heuristic(enemyPos[0], enemyPos[1], playerPos[0], playerPos[1]);
    }

    /*
    [ ][ ][ ][ ]
    [#][#][#][#]
    [P][#][ ][E]
    [E][P][ ][#]
     */
}
