package enemy;

import map.Position;
import java.util.HashMap;
import java.util.Map;

public class EnemyManager {
    Map<Enemy, Position> enemys = new HashMap<>();
    private Enemy currentEnemy;
    int Index = 0;

    public EnemyManager() {

    }

    public void add(Enemy enemy) {
        this.enemys.put(enemy, null);
        enemy.setIndex(Index++);
        if (this.currentEnemy == null) {
            this.currentEnemy = enemy;
        }
    }

    public void remove(Enemy enemy) {
        this.enemys.remove(enemy);
        if (this.currentEnemy == enemy) {
            this.currentEnemy = null;
        }
    }

    public Enemy getEnemyByIndex(int index) {
        for (Map.Entry<Enemy, Position> entry : enemys.entrySet()) {
            Enemy enemy = entry.getKey();

            if (enemy.getIndex() == index) {
                return enemy;
            }
        }
        return null;
    }

    public Enemy getCurrentEnemy() {
        return this.currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public Map<Enemy, Position> getEnemies() {
        return this.enemys;
    }

    public Position getEnemyPosition(int index) {
        for (Map.Entry<Enemy, Position> entry : enemys.entrySet()) {
            Enemy enemy = entry.getKey();

            if (enemy.getIndex() == index) {
                return entry.getValue();
            }
        }
        return null;
    }
}