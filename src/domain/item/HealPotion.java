package domain.item;

import domain.enums.ConsoleColor;
import domain.entidy.Player;

public class HealPotion extends Potion{

    public HealPotion() {
        super("Heal potion", 15, 20);
    }

    @Override
    public void use(Player player) {
        player.heal(this.points);
    }

    @Override
    protected ConsoleColor getColorCode() {
        return ConsoleColor.GREEN;
    }

    @Override
    public String toString() {
        return name;
    }
}
