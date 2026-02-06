package game;

import domain.entidy.Player;
import domain.item.HealPotion;
import domain.item.Potion;
import domain.item.StrengthPotion;

import java.util.ArrayList;

public class Shop {

    private ArrayList<Potion> stock = new ArrayList<>();

    public Shop() {
        this.stock.add(new HealPotion());
        this.stock.add(new StrengthPotion());
    }

    public void sell(Player player, int indice) {
        switch (indice) {
            case 0:
                return;
            case 1:
                indice--;
                if (player.getGold() >= stock.get(indice).getGold()) {
                    player.setGold(player.getGold() - stock.get(indice).getGold());
                    player.addItem(new HealPotion());
                } else {
                    System.out.println("Not enough gold for" + stock.get(indice).getName());
                }
                break;
            case 2:
                indice--;
                if (player.getGold() >= stock.get(indice).getGold()) {
                    player.setGold(player.getGold() - stock.get(indice).getGold());
                    player.addItem(new StrengthPotion());
                } else {
                    System.out.println("Not enough gold! " + stock.get(indice).getName());
                }
                break;
            default:
                System.out.println("invalid option");
        }

    }
}
