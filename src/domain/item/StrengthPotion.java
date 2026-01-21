package domain.item;

import domain.entidy.Player;
import domain.enums.ConsoleColor;

public class StrengthPotion extends Potion {

    public StrengthPotion() {
        super("Potion of strength", 10, 5);
    }

    @Override
    public void use(Player player) {
        player.increaseStrength(this.points);
    }

    @Override
    protected ConsoleColor getColorCode() {
        return ConsoleColor.RED;
    }
}
