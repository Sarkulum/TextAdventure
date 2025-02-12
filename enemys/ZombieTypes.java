package enemys;

import java.util.Random;

public class ZombieTypes {
    public static Integer createZombie(String name, Integer finalId) {
        Random random = new Random();

        switch (name) {
            case "Shambler" -> Enemy.createEnemy(
                    finalId,
                    "Shambler",
                    1,
                    3,
                    random.nextInt(5, 7),
                    1
            );
            case "Rotter" -> Enemy.createEnemy(
                    finalId,
                    "Rotter",
                    3,
                    6,
                    random.nextInt(6, 8),
                    2
            );
            case "Crawler" -> Enemy.createEnemy(
                    finalId,
                    "Crawler",
                    1,
                    4,
                    random.nextInt(4, 6),
                    2
            );
            case "Ghoul" -> Enemy.createEnemy(
                    finalId,
                    "Ghoul",
                    4,
                    7,
                    random.nextInt(10, 14),
                    3
            );
            case "Bloater" -> Enemy.createEnemy(
                    finalId,
                    "Bloater",
                    3,
                    6,
                    random.nextInt(12, 18),
                    1
            );
            case "Lurker" -> Enemy.createEnemy(
                    finalId,
                    "Lurker",
                    4,
                    8,
                    random.nextInt(8, 12),
                    3
            );
            case "Spitter" -> Enemy.createEnemy(
                    finalId,
                    "Spitter",
                    5,
                    9,
                    random.nextInt(6, 10),
                    0
            );
            case "Brute" -> Enemy.createEnemy(
                    finalId,
                    "Brute",
                    6,
                    10,
                    random.nextInt(15, 20),
                    2
            );
            case "Burnt Zombie" -> Enemy.createEnemy(
                    finalId,
                    "Burnt Zombie",
                    4,
                    6,
                    random.nextInt(10, 14),
                    3
            );
            case "Mutant Zombie" -> Enemy.createEnemy(
                    finalId,
                    "Mutant Zombie",
                    7,
                    12,
                    random.nextInt(18, 22),
                    4
            );
            case "Necrofiend" -> Enemy.createEnemy(
                    finalId,
                    "Necrofiend",
                    8,
                    15,
                    random.nextInt(20, 25),
                    5
            );
            case null, default -> Enemy.createEnemy(
                    finalId,
                    "Walker",
                    2,
                    5,
                    random.nextInt(8, 10),
                    1
            );
        }
        return finalId;
    }
}
