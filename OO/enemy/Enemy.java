package OO.enemy;

import OO.creature.Creature;

import java.util.Random;

public class Enemy extends Creature {
    public Enemy(String name, int minDamage, int maxDamage, int maxHP, int movement, int range) {
        super(name, minDamage, maxDamage, maxHP, movement, range);
    }

    public Enemy creatEnemyByList(String enemyName) {
        Random random = new Random();

        switch (enemyName) {
            case "Shambler" -> {
                return new Enemy(
                        "Shambler",
                        1,
                        3,
                        random.nextInt(5, 7),
                        1,
                        1
                );
            }
            case "Rotter" -> {
                return new Enemy(
                        "Rotter",
                        3,
                        6,
                        random.nextInt(6, 8),
                        2,
                        1
                );
            }
            case "Crawler" -> {
                return new Enemy(
                        "Crawler",
                        1,
                        4,
                        random.nextInt(4, 6),
                        2,
                        1
                );
            }
            case "Ghoul" -> {
                return new Enemy(
                        "Ghoul",
                        4,
                        7,
                        random.nextInt(10, 14),
                        3,
                        1
                );
            }
            case "Bloater" -> {
                return new Enemy(
                        "Bloater",
                        3,
                        6,
                        random.nextInt(12, 18),
                        1,
                        1
                );
            }
            case "Lurker" -> {
                return new Enemy(
                        "Lurker",
                        4,
                        8,
                        random.nextInt(8, 12),
                        3,
                        1
                );
            }
            case "Spitter" -> {
                return new Enemy(
                        "Spitter",
                        5,
                        9,
                        random.nextInt(6, 10),
                        0,
                        3
                );
            }
            case "Brute" -> {
                return new Enemy(
                        "Brute",
                        6,
                        10,
                        random.nextInt(15, 20),
                        2,
                        1
                );
            }
            case "Burnt Zombie" -> {
                return new Enemy(
                        "Burnt Zombie",
                        4,
                        6,
                        random.nextInt(10, 14),
                        3,
                        1
                );
            }
            case "Mutant Zombie" -> {
                return new Enemy(
                        "Mutant Zombie",
                        7,
                        12,
                        random.nextInt(18, 22),
                        4,
                        1
                );
            }
            case "Necrofiend" -> {
                return new Enemy(
                        "Necrofiend",
                        8,
                        15,
                        random.nextInt(20, 25),
                        5,
                        1
                );
            }
            case "Walker" -> {
                return new Enemy(
                        "Walker",
                        2,
                        5,
                        random.nextInt(8, 10),
                        1,
                        1
                );
            }
            default -> {
                return null;
            }
        }
    }
}
