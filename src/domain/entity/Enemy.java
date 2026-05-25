package domain.entity;

import domain.enums.Difficulty;
public class Enemy extends Entity {

    private String description;
    private int xp;

    public Enemy(String name, int strength, int defense, Difficulty difficulty, int maxLife, String description, int xp) {
        super(name, maxLife, strength, defense);
        increaseStrength(difficulty.getBuffdamege());
        this.maxLife = maxLife + difficulty.getLifeBonus();
        this.currentLife = this.maxLife;
        this.description = description;
        this.xp = xp;
    }

    @Override
    public int attack(Entity entity) {
        return entity.receiveDamage(strength);
    }

    @Override
    public void revive() {
        setCurrentLife(maxLife);
    }

    public String getDescription() {
        return description;
    }

    public int getXp() {
        return xp;
    }
}
