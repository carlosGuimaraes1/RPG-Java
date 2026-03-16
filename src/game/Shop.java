package game;

import domain.entidy.Player;
import domain.item.HealPotion;
import domain.item.Potion;
import domain.item.StrengthPotion;
import utils.InputValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    private ArrayList<Potion> stock = new ArrayList<>();

    public Shop() {
        this.stock.add(new HealPotion());
        this.stock.add(new StrengthPotion());
    }

    public boolean sell(Player player, int indice) {
        switch (indice) {
            case 0:
                return false;
            case 1:
                indice--;
                if (player.getGold() >= stock.get(indice).getGold()) {
                    player.setGold(player.getGold() - stock.get(indice).getGold());
                    player.addItem(new HealPotion());
                    return true;
                } else {
                    System.out.println("Not enough gold for" + stock.get(indice).getName());
                    return false;
                }
            case 2:
                indice--;
                if (player.getGold() >= stock.get(indice).getGold()) {
                    player.setGold(player.getGold() - stock.get(indice).getGold());
                    player.addItem(new StrengthPotion());
                    return true;
                } else {
                    System.out.println("Not enough gold! " + stock.get(indice).getName());
                    return false;
                }
            default:
                System.out.println("invalid option");
        }
        return false;
    }
    public void runShop(Player player, Scanner input){

        System.out.println("=== MERCHANT ===");
        System.out.println("Your gold " + player.getGold());
        System.out.println("Which potion do you wish to buy?");

        for (Potion potion : stock) {
            potion.showDetails();
        }

        while (true) {
            System.out.println("1 for buy healing potion \n2 for buy strength potion \n0 for exit");
            int choicePotion = InputValidation.readInt(input);
            if (choicePotion == 1) {
                if (sell(player, choicePotion)){
                    System.out.println("You bought the healing potion. ");
                }
            } else if (choicePotion == 2) {
                if (sell(player, choicePotion)){
                    System.out.println("You bought the strength potion. ");
                }
            } else if (choicePotion == 0) {
                System.out.println("leaving the shop");
                break;
            } else {
                System.out.println("Invalid choice.");
                break;
            }
        }
    }
}
