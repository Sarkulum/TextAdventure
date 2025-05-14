package combat;

// Currently not necessary to separate but maybe I want to implement different damage types later.
public class Damage {
    private static int amount;

    public Damage(int amount) {
        Damage.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
