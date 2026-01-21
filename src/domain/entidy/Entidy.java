package domain.entidy;

public abstract class Entidy {
    protected String name;
    protected int maxLife = 150;
    protected int currentLife;
    protected int strength;
    protected int defense;

    public Entidy(String name, int currentLife, int strength, int defense) {
        this.name = name;
        this.currentLife = currentLife;
        this.strength = strength;
        this.defense = defense;
    }

    public void receiveDamage(int damage) {
        int realDamage = damage - defense;
        if (realDamage<=0){
            realDamage =1;
        }
            this.currentLife -= realDamage;

        if (currentLife < 0) {
            currentLife = 0;
        }
    }

    public abstract void attack(Entidy target);

    public boolean isAlive() {
        if (currentLife > 0) {
            return true;
        }
        return false;
    }

    public void heal(int amount) {
        if (currentLife > maxLife) {
            currentLife = maxLife;
        }
        currentLife += amount;
    }

    public void increaseStrength(int amount) {
        this.strength += amount;
    }

    public String getName() {
        return name;
    }

    public int getMaxLife() {
        return maxLife;
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
