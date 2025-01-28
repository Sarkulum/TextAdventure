package enemys;

import java.util.Random;

public class ZombieTypes {
    public static Integer createZombie(String name, Integer finalId) {
        Random random = new Random();
        /*
        boolean idFree = false;
        Integer finalId = 0;

        while (!idFree) {
            Integer id = random.nextInt(100);
            if(Enemy.isIdFree(id)) {
                idFree = true;
                finalId = id;
            }
        }
         */

        switch (name) {
            case "Shambler" -> Enemy.createEnemy(
                    finalId,
                    "Shambler",
                    random.nextInt((7 - 5) + 1) + 5,
                    1,
                    3
            );
            case "Rotter" -> Enemy.createEnemy(
                    finalId,
                    "Rotter",
                    random.nextInt((8 - 6) + 1) + 6,
                    3,
                    6
            );
            case "Crawler" -> Enemy.createEnemy(
                    finalId,
                    "Crawler",
                    random.nextInt((6 - 4) + 1) + 4,
                    1,
                    4
            );
            case "Ghoul" -> Enemy.createEnemy(
                    finalId,
                    "Ghoul",
                    random.nextInt((14 - 10) + 1) + 10,
                    4,
                    7
            );
            case "Bloater" -> Enemy.createEnemy(
                    finalId,
                    "Bloater",
                    random.nextInt((18 - 12) + 1) + 12,
                    3,
                    6
            );
            case "Lurker" -> Enemy.createEnemy(
                    finalId,
                    "Lurker",
                    random.nextInt((12 - 8) + 1) + 8,
                    4,
                    8
            );
            case "Spitter" -> Enemy.createEnemy(
                    finalId,
                    "Spitter",
                    random.nextInt((10 - 6) + 1) + 6,
                    5,
                    9
            );
            case "Brute" -> Enemy.createEnemy(
                    finalId,
                    "Brute",
                    random.nextInt((20 - 15) + 1) + 15,
                    6,
                    10
            );
            case "Burnt Zombie" -> Enemy.createEnemy(
                    finalId,
                    "Burnt Zombie",
                    random.nextInt((14 - 10) + 1) + 10,
                    4,
                    6
            );
            case "Mutant Zombie" -> Enemy.createEnemy(
                    finalId,
                    "Mutant Zombie",
                    random.nextInt((22 - 18) + 1) + 18,
                    7,
                    12
            );
            case "Necrofiend" -> Enemy.createEnemy(
                    finalId,
                    "Necrofiend",
                    random.nextInt((25 - 20) + 1) + 20,
                    8,
                    15
            );
            case null, default -> Enemy.createEnemy(
                    finalId,
                    "Walker",
                    random.nextInt((10 - 8) + 1) + 8,
                    2,
                    5
            );
        }
        return finalId;
    }
}
