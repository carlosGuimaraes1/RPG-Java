package domain.entidy;

public abstract class Entity {
    protected String name;
    protected final int maxLifePlayer = 150;
    protected int maxLife;
    protected int currentLife;
    protected int strength;
    protected int defense;


    public Entity(String name, int currentLife, int strength, int defense) {
        this.name = name;
        this.currentLife = currentLife;
        this.strength = strength;
        this.defense = defense;
    }

    public Entity() {
    }

    public int receiveDamage(int damage) {
        int realDamage = damage - defense;
        if (realDamage<=0){
            realDamage = 1;
        }
            this.currentLife -= realDamage;
        if (currentLife < 0) {
            currentLife = 0;
        }
        return realDamage;
    }

    public abstract void attack(Entity target);

    public boolean isAlive() {
        if (currentLife > 0) {
            return true;
        }
        return false;
    }

    public void heal(int amount) {
        currentLife += amount;
        if (currentLife > maxLifePlayer) {
            currentLife = maxLifePlayer;
        }
    }

    public void increaseStrength(int amount) {
        this.strength += amount;
    }

    public String getName() {
        return name;
    }

    public int getMaxLifePlayer() {
        return maxLifePlayer;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }
}
