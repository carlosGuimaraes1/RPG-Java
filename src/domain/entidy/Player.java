package domain.entidy;

import domain.item.Potion;

import java.util.ArrayList;

public class Player extends Entity {

    private int gold;
    ArrayList<Potion> inventory;
    private int stage = 1;

    public Player(String name, int currentLife, int strength, int defense) {
        super(name, currentLife, strength, defense);
        this.inventory = new ArrayList<>();
    }

    @Override
    public void attack(Entity entidy) {
        entidy.receiveDamage(strength);
    }

    public void addItem(Potion potion) {
        inventory.add(potion);
    }

    public boolean dodge() {
        if (Math.random() > 0.5) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "gold=" + gold +
                ", inventory=" + this.inventory.toString()+
                ", stage=" + stage +
                ", name='" + name + '\'' +
                ", maxLife=" + maxLife +
                ", currentLife=" + currentLife +
                ", strength=" + strength +
                ", defense=" + defense +
                '}';
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Potion> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Potion> inventory) {
        this.inventory = inventory;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

}
