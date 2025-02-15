/**
 * Axe Class - A powerful weapon given after completing the Tower of Mystery.
 */
public class Axe extends Weapon {
    public Axe() {
        super("Axe", 50);
    }

    @Override
    public void useWeapon() {
        System.out.println("🪓 You swing the Axe, dealing " + this.damage + " damage!");
    }

    public void heavyStrike() {
        System.out.println("🔥 You perform a HEAVY STRIKE, dealing massive damage!");
    }
}
