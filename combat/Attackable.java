package combat;

public interface Attackable {
    void takeDamage(Damage damage);
    String getName(); // For feedback (not implemented yet)
}
