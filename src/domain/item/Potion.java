package domain.item;

import domain.enums.ConsoleColor;
import domain.entidy.Player;

public abstract class Potion {
    protected String name;
    protected int gold;
    protected int points;

    public Potion(String name, int gold, int points) {
        this.name = name;
        this.gold = gold;
        this.points = points;
    }

    public abstract void use(Player player);

    protected abstract ConsoleColor getColorCode();

    public void showDetails() {
        ConsoleColor color = getColorCode();
        System.out.println(color.ansiCode + " Item " + this.name.toUpperCase() + ConsoleColor.RESET.ansiCode);
        System.out.println("Price " + this.gold);
        System.out.println("Poder " + this.points);
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public int getPoints() {
        return points;
    }
}
