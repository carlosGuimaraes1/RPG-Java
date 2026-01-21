package domain.entidy;

import domain.enums.CharacterClass;
import domain.item.Potion;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class Player extends Entidy {


    private int gold;
    ArrayList<Potion> inventory;

    public Player(String name, int currentLife, int strength, int defense) {
        super(name, currentLife, strength, defense);
        this.inventory = new ArrayList<>();
    }

    @Override
    public void attack(Entidy entidy) {
        entidy.receiveDamage(strength);
    }

    public void addItem(Potion potion) {
        inventory.add(potion);
    }

    public boolean dogde() {
        if (Math.random() > 0.5) {
            return true;
            // defendido
        }
        return false;
        //nao defendido
    }

    @Override
    public String toString() {
        return "You status: " +
                ", gold: " + gold +
                ", name: '" + name + '\'' +
                ", currentLife: " + currentLife +
                ", strength: " + strength +
                ", defense: " + defense;
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
}
