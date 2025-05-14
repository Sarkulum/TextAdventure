package enemy;

import combat.Attackable;
import combat.Damage;
import creature.Creature;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class Enemy extends Creature implements Attackable {
    private static final Random random = new Random();
    private static int index;

    private static final Map<String, Supplier<Enemy>> enemyCreators = new HashMap<>();

    static { // This adds all the enemy's into a Map of Strings, so I don't need to use the case switch.
        enemyCreators.put("Shambler", () -> new Enemy(
                "Shambler",
                1,
                3,
                random.nextInt(5, 7),
                1,
                1
        ));

        enemyCreators.put("Rotter", () -> new Enemy(
                "Rotter",
                3,
                6,
                random.nextInt(6, 8),
                2,
                1
        ));

        enemyCreators.put("Crawler", () -> new Enemy(
                "Crawler",
                1,
                4,
                random.nextInt(4, 6),
                2,
                1
        ));

        enemyCreators.put("Ghoul", () -> new Enemy(
                "Ghoul",
                4,
                7,
                random.nextInt(10, 14),
                3,
                1
        ));

        enemyCreators.put("Bloater", () -> new Enemy(
                "Bloater",
                3,
                6,
                random.nextInt(12, 18),
                1,
                1
        ));

        enemyCreators.put("Lurker", () -> new Enemy(
                "Lurker",
                4,
                8,
                random.nextInt(8, 12),
                3,
                1
        ));

        enemyCreators.put("Spitter", () -> new Enemy(
                "Spitter",
                5,
                9,
                random.nextInt(6, 10),
                0,
                3
        ));

        enemyCreators.put("Brute", () -> new Enemy(
                "Brute",
                6,
                10,
                random.nextInt(15, 20),
                2,
                1
        ));

        enemyCreators.put("Burnt Zombie", () -> new Enemy(
                "Burnt Zombie",
                4,
                6,
                random.nextInt(10, 14),
                3,
                1
        ));

        enemyCreators.put("Mutant Zombie", () -> new Enemy(
                "Mutant Zombie",
                7,
                12,
                random.nextInt(18, 22),
                4,
                1
        ));

        enemyCreators.put("Necrofiend", () -> new Enemy(
                "Necrofiend",
                8,
                15,
                random.nextInt(20, 25),
                5,
                1
        ));

        enemyCreators.put("Walker", () -> new Enemy(
                "Walker",
                2,
                5,
                random.nextInt(8, 10),
                1,
                1
        ));
    }

    @Override
    public void takeDamage(Damage damage) {
        currentHP -= damage.getAmount();
        System.out.println("Enemy took " + damage.getAmount() + " damage!");
    }

    public Enemy(String name, int minDamage, int maxDamage, int maxHP, int movement, int range) {
        super(name, minDamage, maxDamage, maxHP, movement, range);
    }

    public static Enemy createEnemyByName(String name) {
        Supplier<Enemy> creator = enemyCreators.get(name);
        if (creator != null) {
            return creator.get();
        } else {
            return null; // or throw new IllegalArgumentException("Unknown enemy type: " + name);
        }
    }

    public void setIndex(int index) { Enemy.index = index;}
    public int getIndex() { return index; }
}
