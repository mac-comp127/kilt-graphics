package activityStarterCode.extractInterface.solution;

public class GameCharacter {
    private final String name;
    private final Attack attack;
    private int hitPoints, mana;

    public GameCharacter(String name, int hitPoints, int mana, Attack attack) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.mana = mana;
        this.attack = attack;
    }

    public String attack(GameCharacter target) {
        return attack.attack(this, target);
    }

    public String getName() {
        return name;
    }

    public Attack getAttack() {
        return attack;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getMana() {
        return mana;
    }

    public void takeDamage(int damage) {
        hitPoints = Math.max(0, hitPoints - damage);
    }

    public boolean useMana(int amount) {
        if (mana >= amount) {
            mana -= amount;
            return true;
        } else {
            return false;
        }
    }
}
