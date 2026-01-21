package domain.entidy;

import domain.enums.Difficulty;

public class Enemy extends Entidy {

    private String description;

    public Enemy(String name, int strength, int defense, String labelDamage, int currentLife, int maxLife,String labelLife, String description) {
        super(name, currentLife, strength, defense);
        this.currentLife = currentLife;
        this.maxLife = maxLife;
        increaseStrength(Difficulty.searchDifficultyDamage(labelDamage));
        heal(Difficulty.searchDifficultyLife(labelLife));
        this.description = description;
    }

    @Override
    public void attack(Entidy entidy) {
        entidy.receiveDamage(strength);
    }

    public void revive(){
        setCurrentLife(maxLife);
    }

    public String getDescription() {
        return description;
    }

}
