package domain.entity;

import domain.enums.CharacterClass;
import domain.enums.Difficulty;
import domain.item.Potion;

import java.util.ArrayList;
import java.util.List;


public class Player extends Entity {

    private int gold;
    private List<Potion> inventory;
    private int stage = 1;
    private CharacterClass characterClass;
    private Difficulty difficulty;

    public Player(String name, int currentLife, int strength, int defense, Difficulty difficulty, CharacterClass characterClass) {
        super(name, currentLife, strength, defense);
        this.inventory = new ArrayList<>();
        this.difficulty = difficulty;
        this.characterClass = characterClass;

    }

    public Player() {
        this.inventory = new ArrayList<>();
    }

    @Override
    public int attack(Entity entity) {
        return entity.receiveDamage(strength);
    }

    @Override
    public void revive() {
        setCurrentLife(maxLife);
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

    public void nextStage() {
        stage++;
    }

    public void resetStage() {
        stage = 1;
    }

    @Override
    public String toString() {
        return "Player:" +
                "gold: " + gold +
                ", inventory: " + inventory +
                ", stage: " + stage +
                ", characterClass: " + characterClass +
                ", difficulty=" + difficulty +
                ", name='" + name + '\'' +
                ", currentLife=" + currentLife +
                ", strength=" + strength +
                ", defense=" + defense +
                '}';
    }

    public void useItem(int item) {
        inventory.get(item).use(this);
        inventory.remove(item);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Potion> getInventory() {
        return inventory;
    }

    public void setInventory(List<Potion> inventory) {
        this.inventory = inventory;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

