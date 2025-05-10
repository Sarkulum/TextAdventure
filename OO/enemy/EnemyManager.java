package OO.enemy;

import OO.player.Player;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemys = new ArrayList<>();
    private Enemy currentEnemy;

    public EnemyManager() {

    }

    public void add(Enemy enemy) {
        this.enemys.add(enemy);
        enemy.setIndex(this.enemys.indexOf(enemy));
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

    public Enemy get(int index) {
        for (Enemy enemy : this.enemys) {
            if (enemy.getIndex() == index) {
                return enemy;
            }
        }
        return null;
    }

    public Enemy getCurrentEnemy() {return this.currentEnemy;}

    public void setCurrentEnemy(Enemy currentEnemy) {this.currentEnemy = currentEnemy;}

    public Enemy getEnemyByIndex(int index) {
        for (Enemy enemy : this.enemys) {
            if (enemy.getIndex() == index) {
                return enemy;
            }
        }
        return null;
    }

    public List<Enemy> getEnemies() {return this.enemys;}
}