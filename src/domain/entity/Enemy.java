package domain.entity;

import domain.enums.Difficulty;
public class Enemy extends Entity {

    private String description;

    public Enemy(String name, int strength, int defense, Difficulty difficulty, int maxLife, String description) {
        super(name, maxLife, strength, defense);
        increaseStrength(difficulty.getBuffdamege());
        this.maxLife = maxLife + difficulty.getLifeBonus();
        this.currentLife = this.maxLife;
        this.description = description;
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

}
