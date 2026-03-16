package controller;
import domain.entity.Player;
import utils.InputValidation;
import java.util.Scanner;
public class InventoryController {
    public void manageInventory(Player player, Scanner input) {
        System.out.println("=== INVENTORY ===");
        if (player.getInventory().isEmpty()) {
            System.out.println("Your bag is empty.");
        } else {
            for (int i = 0; i < player.getInventory().size(); i++) {
                System.out.println((i + 1) + " - " + player.getInventory().get(i).getName());
            }
            System.out.println("0 - Return");

            System.out.println("Choose an item that you want to use.");
            int item = InputValidation.readInt(input);

            if (item > 0 && item <= player.getInventory().size()) {
                String namePotion = player.getInventory().get(item-1).getName();
                player.useItem(item-1);
                System.out.println("You use " + namePotion);
                System.out.println("Current Life: " + player.getCurrentLife());
                System.out.println("Current Strength: " + player.getStrength());

            } else if (item == 0) {
                System.out.println("Closing bag...");
            } else {
                System.out.println("Invalid item slot.");
            }
        }
    }
}