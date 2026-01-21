package domain.enums;

public enum Difficulty {
    EASY(1, 0, 0, "Easy"),
    NORMAL(2, 3, 10, "Normal"),
    HARD(3, 5, 15, "Hard");

    private final int id;
    private final int damageBonus;
    private final int lifeBonus;
    private final String label;

    Difficulty(int id, int damageBonus, int lifeBonus, String label) {
        this.id = id;
        this.damageBonus = damageBonus;
        this.lifeBonus = lifeBonus;
        this.label = label;
    }

    public static int searchDifficultyDamage(String label) {
        for (Difficulty difficulty : values()) {
            if (difficulty.label.equalsIgnoreCase(label)) {
                return difficulty.damageBonus;
            }
        }
        return 0;
    }

    public static int searchDifficultyLife(String label) {
        for (Difficulty difficulty : values()) {
            if (difficulty.label.equalsIgnoreCase(label)) {
                return difficulty.lifeBonus;
            }
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public int getBuffdamege() {
        return damageBonus;
    }

    public String getReportname() {
        return label;
    }

}

