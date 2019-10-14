package activityStarterCode.extractInterface.solution;

public class FireballAttack implements Attack {
    private final int damage, manaRequired;

    public FireballAttack(int damage, int manaRequired) {
        this.damage = damage;
        this.manaRequired = manaRequired;
    }

    @Override
    public String attack(GameCharacter attacker, GameCharacter target) {
        if (attacker.useMana(manaRequired)) {
            target.takeDamage(damage);
            return attacker.getName() + " hit " + target.getName() + " with a fireball for "
                + damage + " points of damage";
        } else {
            return attacker.getName() + " did not have enough mana for a fireball attack";
        }
    }
}
