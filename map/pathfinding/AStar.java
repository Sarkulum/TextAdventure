package map.pathfinding;

import creator.EnemyCreator;
import enemy.Enemy;
import enemy.EnemyManager;
import map.GridMap;
import map.Position;
import player.Player;
import player.PlayerManager;

import java.util.*;

public class AStar {
    Scanner scanner = new Scanner(System.in);

    // 1D List of ints that takes a 2D char array the position if the enemy and the player
    //TODO
    public List<int[]> aStar(String[][] grid, int startRow, int startCol, int targetRow, int targetCol) {

        // Linked list that also takes priority and spits out the most important node
        PriorityQueue<Node> openSet = new PriorityQueue<>();

        // 2D array to marke the visited and not visited fields
        boolean[][] closedSet = new boolean[grid.length][grid[0].length];

        // Adds a node with enemy start position IDK what the 0 does and then calls a function that estimates how far the enemy is from the player(heuristic)
        openSet.add(new Node(startRow, startCol, 0, heuristic(startRow, startCol, targetRow, targetCol), null));

        // 2D array of all possible movements
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        // While the set is not empty
        while (!openSet.isEmpty()) {
            // Sets Node current to the node with the lowest movement cost
            Node current = openSet.poll();

            // Check if current node is adjacent to the target (Manhattan distance 1)
            if (heuristic(current.row, current.col, targetRow, targetCol) == 1) {
                return reconstructPath(current);
            }

            closedSet[current.row][current.col] = true; // Mark node as visited if it does not return.

            // Extracts one inner array of the 2D array in "directions" at a time and then "dir" holds said array.
            for (int[] dir : directions) {
                int newRow = current.row + dir[0]; // 0 is the first position in the extracted array dir
                int newCol = current.col + dir[1]; // 1 is the second position in the extracted array dir

                // if to checks if there is a '#' in the spot that the enemy is trying to move to
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

    //TODO
    private int heuristic(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2); // Does not allow vertical moves, math.abs is the absolute different between the 2 numbers, Calculates the distance between 2 point on the array.
    }

    //TODO
    private boolean isValidMove(String[][] grid, int row, int col, boolean[][] closedSet) {
        // First checks if move is in bounds and then checks if there is an obstacle
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length
                && !Objects.equals(grid[row][col], "[#]") && !Objects.equals(grid[row][col], "[P]") && !closedSet[row][col] && Objects.equals(grid[row][col], "[ ]");
    }

    //private boolean isValidMovePlay

    // This is the function I actually call when wanting to move the enemy
    //TODO
    public void moveEnemyAStar(GridMap gridMap, int index, EnemyManager enemyManager) {
        Position enemyPosition = enemyManager.getEnemyPosition(index);

        Position playerPosition = PlayerManager.getInstance().getPositionCurrentPlayer(); // Finds player coordinates
        if (playerPosition == null) return;

        // Find the path to the player
        //TODO
        List<int[]> path = aStar(GridMap, enemyRow, enemyCol, playerPos[0], playerPos[1]);
        if (path != null && path.size() > 1) {
            // Start moving the enemy step by step
            int distanceTraveled = 0; // Track how much movement we've used

            // Loop through the path and move as long as movement limit isn't reached
            for (int i = 1; i < path.size() && distanceTraveled < PlayerManager.getInstance().getCurrentPlayer().getMovement(); i++) {
                int[] currentStep = path.get(i);
                int distance = Math.abs(currentStep[0] - enemyPosition.x()) + Math.abs(currentStep[1] - enemyPosition.y());

                // Move only if we haven't exceeded the movement limit
                if (distanceTraveled + distance <= PlayerManager.getInstance().getCurrentPlayer().getMovement()) {
                    gridMap.removePosition(enemyPosition); // Clear the old position
                    Position newPosition = new Position(currentStep[0], currentStep[1]);
                    gridMap.placeEnemy(newPosition, index); // Move the enemy to the new position
                    distanceTraveled += distance; // Add distance moved
                } else {
                    break; // Stop if we've reached the movement limit
                }
            }
        }
    }

    public boolean movePlayer(GridMap gridMap, Position targetPosition) {
        // Calculate the distance (Manhattan distance)
        String[][] map = gridMap.getMap();
        PlayerManager playerManager = PlayerManager.getInstance();
        int distance = Math.abs(targetPosition.x() - playerManager.getPositionCurrentPlayer().x()) + Math.abs(targetPosition.y() - playerManager.getPositionCurrentPlayer().y());

        try {
            // Check if the movement is within the movement limit and within grid bounds
            if (distance <= playerManager.getCurrentPlayer().getMovement() && isInBounds(gridMap, targetPosition)) {
                // If the target position is empty, move the player normally
                if (map[targetPosition.x()][targetPosition.y()].equals("[ ]")) {
                    map[playerManager.getPositionCurrentPlayer().x()][playerManager.getPositionCurrentPlayer().y()] = "[ ]"; // Clear old position
                    map[targetPosition.x()][targetPosition.y()] = "[P]";  // Move player
                    return true;
                } else if (map[targetPosition.x()][targetPosition.y()].equals("[P]")){
                    return true;
                }else{
                    // If the target position is occupied, move to the closest valid adjacent square
                    movePlayerToValidSpot(gridMap, targetPosition);
                    return true;
                }
            } else if (isInBounds(gridMap, targetPosition)) {
                Position limitedPosition = getLimitedStep(targetPosition);

                // Only print message if the player is NOT reaching the exact desired target
                if (limitedPosition.x() != targetPosition.x() || limitedPosition.y() != targetPosition.y()) {
                    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("You did not have enough movement for that, so we placed you to the furthest point you could go.");
                    System.out.println("--------------------------->press enter to continue\n");
                    scanner.nextLine();
                }

                movePlayerToValidSpot(gridMap, limitedPosition);
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

    public Position getLimitedStep(Position targetPosition) {
        int rowDiff = targetPosition.x() - PlayerManager.getInstance().getPositionCurrentPlayer().x();
        int colDiff = targetPosition.y() - PlayerManager.getInstance().getPositionCurrentPlayer().y();

        // Normalize the direction and apply the movement limit, abs just removes stuff like - or so
        if (Math.abs(rowDiff) > Math.abs(colDiff)) {
            // Prioritize row movement (vertical)
            int step = (rowDiff != 0) ? (int) Math.signum(rowDiff) * Math.min(PlayerManager.getInstance().getCurrentPlayer().getMovement(), Math.abs(rowDiff)) : 0;
            return new Position(PlayerManager.getInstance().getPositionCurrentPlayer().x() + step, targetPosition.y());
        } else {
            // Prioritize column movement (horizontal)
            int step = (colDiff != 0) ? (int) Math.signum(colDiff) * Math.min(PlayerManager.getInstance().getCurrentPlayer().getMovement(), Math.abs(colDiff)) : 0;
            return new Position(targetPosition.x(), PlayerManager.getInstance().getPositionCurrentPlayer().y() + step);
        }
    }

    // Basically don't need this, but I could revers the path lol
    private List<int[]> reconstructPath(Node node) {
        List<int[]> path = new ArrayList<>();
        while (node != null) {
            path.addFirst(new int[]{node.row, node.col}); // Add to start of the list
            node = node.parent;
        }
        return path;
    }

    // Check if player is within 'units' distance of the enemy in any direction
    public boolean isPlayerAdjacentToEnemy(EnemyManager enemyManager, int index, int range) {
        int playerX = PlayerManager.getInstance().getPositionCurrentPlayer().x();
        int playerY = PlayerManager.getInstance().getPositionCurrentPlayer().y();
        int enemyX = enemyManager.getEnemyPosition(index).x();
        int enemyY = enemyManager.getEnemyPosition(index).y();

        // The check now verifies that the difference in both row and column is within the specified 'units'
        return (Math.abs(playerX - enemyX) <= range && Math.abs(playerY - enemyY) <= range);
    }

    // Check if enemy is within 'units' distance of the player in any direction
    public boolean isEnemyAdjacentToPlayer(int index, EnemyManager enemyManager) {
        Position playerPosition = PlayerManager.getInstance().getPositionCurrentPlayer();

        Enemy enemy = enemyManager.getEnemyByIndex(index);
        Position enemyPosition = enemyManager.getEnemyPosition(index);
        int range = enemy.getRange();

        int playerX = playerPosition.x();
        int playerY = playerPosition.y();
        int enemyX = enemyPosition.x();
        int enemyY = enemyPosition.y();

        // The check now verifies that the enemy is within 'units' distance of the player
        return (Math.abs(enemyX - playerX) <= range && Math.abs(enemyY - playerY) <= range);
    }

    public void movePlayerToValidSpot(GridMap gridMap, Position targetPosition) {
        int currentX = PlayerManager.getInstance().getPositionCurrentPlayer().x();
        int currentY = PlayerManager.getInstance().getPositionCurrentPlayer().y();
        String[][] map= gridMap.getMap();

        // If the target position is empty, move there
        if (map[targetPosition.x()][targetPosition.y()].equals("[ ]")) {
            map[currentX][currentY] = "[ ]"; // Clear old position
            map[targetPosition.x()][targetPosition.y()] = "[P]";   // Move player to the new position
            return;
        }

        // Define possible adjacent positions (Up, Down, Left, Right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] bestPosition = null;
        int bestDistance = Integer.MAX_VALUE;

        // Find the closest valid adjacent position
        for (int[] dir : directions) {
            int newX = targetPosition.x() + dir[0];
            int newY = targetPosition.y() + dir[1];
            Position newPosition = new Position(newX, newY);

            // Check if the new position is within bounds and empty
            if (isInBounds(gridMap, newPosition) && map[newX][newY].equals("[ ]")) {
                int distance = Math.abs(newX - currentX) + Math.abs(newY - currentY);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestPosition = new int[]{newX, newY};
                }
            }
        }

        // Move the player to the closest valid adjacent position if available
        if (bestPosition != null) {
            map[currentX][currentY] = "[ ]"; // Clear old position
            map[bestPosition[0]][bestPosition[1]] = "[P]"; // Move player
        }
    }

    // Helper function to check if a position is within the grid bounds
    private boolean isInBounds(GridMap gridMap, Position position) {
        int x = position.x();
        int y = position.y();
        String[][] map = gridMap.getMap();

        return x >= 0 && y >= 0 && x < map.length && y < map[0].length;
    }

    /*
    "[ ]""[ ]""[ ]""[ ]"
    [#][#][#][#]
    [P][#][ ][E]
    [E][P][ ][#]
     */
}
