package world;

import combat.CombatManager;
import enemy.Enemy;
import enemy.EnemyManager;
import map.GridMap;
import map.Position;
import logic.GameEvent;
import player.PlayerManager;

import java.util.List;

public class CombatRoom extends Room {

    private final int gridWidth;
    private final int gridHeight;
    private final List<String> enemyTypes;
    private final List<Position> obstaclePositions;
    private final GameEvent victoryEvent;
    private final String victoryMessage;

    public CombatRoom(String roomName, List<String> descriptionParts, int gridWidth, int gridHeight, List<String> enemyTypes, List<Position> obstaclePositions, GameEvent victoryEvent, String victoryMessage) {
        super(roomName, descriptionParts, List.of("Start Battle"), List.of());
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.enemyTypes = enemyTypes;
        this.obstaclePositions = obstaclePositions;
        this.victoryEvent = victoryEvent;
        this.victoryMessage = victoryMessage;
    }

    @Override
    public void enter() {
        super.enter();

        EnemyManager enemyManager = new EnemyManager();
        for (String enemyType : enemyTypes) {
            enemyManager.add(Enemy.createEnemyByName(enemyType));
        }

        GridMap gridMap = new GridMap(gridWidth, gridHeight, enemyManager, obstaclePositions, List.of(new Position(0, 0))); // Assuming player starts at (0,0)

        CombatManager combatManager = new CombatManager(enemyManager, gridMap);
        boolean playerWon = combatManager.startCombat();

        if (playerWon) {
            if (victoryEvent != null) {
                PlayerManager.getInstance().getCurrentPlayer().markDone(victoryEvent);
            }
            if (victoryMessage != null) {
                System.out.println(victoryMessage);
            }

            // Move to the next room if one is defined
            if (getNextRoom() != null) {
                getNextRoom().enter();
            } else {
                System.out.println("You have cleared the area.");
            }
        } else {
            // Go to the death room
            World.getRoom("You Died").enter();
        }
    }
}
