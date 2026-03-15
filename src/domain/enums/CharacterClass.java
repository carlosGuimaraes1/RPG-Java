package domain.enums;

public enum CharacterClass {
    WARRIOR("Warrior", 10, 8),
    MAGE("Mage", 8, 10),
    ARCHER("Archer", 7, 8);

    private int strength;
    private int defense;
    private String name;
    private int value;

    CharacterClass(String name, int strength, int defense) {
        this.name = name;
        this.strength = strength;
        this.defense = defense;
    }

    public static CharacterClass searchClass(String name) {
        for (CharacterClass characterClass : values()) {
            if (characterClass.getName().equals(name)) {
                return characterClass;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }
}
