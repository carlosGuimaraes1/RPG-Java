package domain.enums;

public enum CharacterClass {
    WARRIOR(1, 20, 8),
    MAGE(2, 8, 10),
    ARCHER(3, 10, 8);

    private int value;
    private int strength;
    private int defense;
    private String name;

    CharacterClass(int value, int strength, int defense) {
        this.value = value;
        this.strength = strength;
        this.defense = defense;
    }

    public static CharacterClass searchClass(int value) {
        for (CharacterClass characterClass : values()) {
            if (characterClass.getValue() == value) {
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
