package OO.enemy;

import OO.creature.Creature;

public class Enemy extends Creature {
    public Enemy(String name, int minDamage, int maxDamage, int maxHP, int movement, int range) {
        super(name, minDamage, maxDamage, maxHP, movement, range);
    }
}
