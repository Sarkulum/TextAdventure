package OO.combat;

import java.util.Random;

public class Attack {
    private final int minDamage;
    private final int maxDamage;
    private final int weaponMinDamage;
    private final int weaponMaxDamage;

    private final Random random = new Random();

    public Attack(int minDamage, int maxDamage, int weaponMinDamage, int weaponMaxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.weaponMinDamage = weaponMinDamage;
        this.weaponMaxDamage = weaponMaxDamage;
    }

    public Damage execute() {
        int weaponDamage = random.nextInt(weaponMinDamage, weaponMaxDamage);
        int playerDamage = random.nextInt(minDamage, maxDamage);

        int finalDamage = weaponDamage + playerDamage;
        return new Damage(finalDamage);
    }
}
