package combat;

import enemy.Enemy;
import enemy.EnemyManager;
import items.Weapon;
import map.pathfinding.AStar;
import map.GridMap;
import map.Position;
import player.Player;
import player.PlayerManager;
import java.util.Scanner;

public class CombatManager {

    private final PlayerManager playerManager;
    private final EnemyManager enemyManager;
    private final GridMap gridMap;
    private final AStar aStar;
    private final Scanner scanner;

    public CombatManager(EnemyManager enemyManager, GridMap gridMap) {
        this.playerManager = PlayerManager.getInstance();
        this.enemyManager = enemyManager;
        this.gridMap = gridMap;
        this.aStar = new AStar();
        this.scanner = new Scanner(System.in);
    }

    public boolean startCombat() {
        while (playerManager.getCurrentPlayer().getCurrentHP() > 0 && !enemyManager.getEnemies().isEmpty()) {
            playerTurn();
            if (enemyManager.getEnemies().isEmpty()) {
                break; // Player wins
            }
            enemyTurn();
        }

        if (playerManager.getCurrentPlayer().getCurrentHP() > 0) {
            System.out.println("You have won the battle!");
            return true; // Player wins
        } else {
            System.out.println("You have been defeated.");
            return false; // Player loses
        }
    }

    private void playerTurn() {
        gridMap.printMap();
        System.out.println("Player's Turn:");
        System.out.println("1. Move");
        System.out.println("2. Attack");

        // TODO check if the -1 causes issues
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
        }

        switch (choice) {
            case 1:
                handlePlayerMove();
                break;
            case 2:
                handlePlayerAttack();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                playerTurn(); // Retry the turn
                break;
        }
    }

    private void handlePlayerMove() {
        System.out.println("Enter target X coordinate:");
        int x = scanner.nextInt();
        System.out.println("Enter target Y coordinate:");
        int y = scanner.nextInt();

        Position targetPosition = new Position(x, y);
        aStar.movePlayer(gridMap, targetPosition);
    }

    private void handlePlayerAttack() {
        System.out.println("Choose an enemy to attack:");
        for (Enemy enemy : enemyManager.getEnemies().keySet()) {
            System.out.println(enemy.getIndex() + ": " + enemy.getName());
        }

        int enemyIndex = scanner.nextInt();
        Enemy targetEnemy = enemyManager.getEnemyByIndex(enemyIndex);

        if (targetEnemy != null) {
            Player player = playerManager.getCurrentPlayer();
            Weapon equippedWeapon = player.getEquippedWeapon();

            int weaponMinDamage = 0;
            int weaponMaxDamage = 0;

            if (equippedWeapon != null) {
                weaponMinDamage = equippedWeapon.getMinDamage();
                weaponMaxDamage = equippedWeapon.getMaxDamage();
            }

            Attack attack = new Attack(player.getMinDamage(), player.getMaxDamage(), weaponMinDamage, weaponMaxDamage);
            targetEnemy.takeDamage(attack.execute());

            if (targetEnemy.getCurrentHP() <= 0) {
                enemyManager.remove(targetEnemy);
                gridMap.removePosition(enemyManager.getEnemyPosition(enemyIndex));
                System.out.println(targetEnemy.getName() + " has been defeated!");
            }
        } else {
            System.out.println("Invalid enemy selected.");
        }
    }

    private void enemyTurn() {
        for (Enemy enemy : enemyManager.getEnemies().keySet()) {
            aStar.moveEnemyAStar(gridMap, enemy.getIndex(), enemyManager);

            if (aStar.isEnemyAdjacentToPlayer(enemy.getIndex(), enemyManager)) {
                Attack attack = new Attack(enemy.getMinDamage(), enemy.getMaxDamage(), 0, 0);
                playerManager.getCurrentPlayer().takeDamage(attack.execute());
            }
        }
    }
}
