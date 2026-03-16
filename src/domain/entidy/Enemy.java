package domain.entidy;

import domain.enums.Difficulty;

public class Enemy extends Entity {

    private String description;

    public Enemy(String name, int strength, int defense, String labelDamage, int currentLife, int maxLife,String labelLife, String description) {
        super(name, currentLife, strength, defense);
        increaseStrength(Difficulty.searchDifficultyDamage(labelDamage));
        heal(Difficulty.searchDifficultyLife(labelLife));
        this.maxLife = maxLife;
        this.description = description;
    }

    @Override
    public void attack(Entity entidy) {
        entidy.receiveDamage(strength);
    }

    @Override
    public void revive() {
        setCurrentLife(maxLife);
    }

    public String getDescription() {
        return description;
    }

}
