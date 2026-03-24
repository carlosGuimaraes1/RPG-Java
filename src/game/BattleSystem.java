package game;

import controller.*;
import domain.entity.*;
import domain.enums.*;
import utils.*;

import java.io.File;
import java.util.*;

public class BattleSystem {
    public static void main(String[] args) {
        GameController controller = new GameController();
        Scanner input = new Scanner(System.in);
        Player player;
        List<Enemy> enemyList = new ArrayList<>();
        GameSaver saver = new GameSaver();
        Shop shop = new Shop();
        InventoryController invController = new InventoryController();

        System.out.println(ConsoleColor.BLUE.ansiCode + "WELCOME TO GAME DEV " + ConsoleColor.RESET.ansiCode);

        File file = new File("saves", "save.txt");
        if (!file.exists()) {
            player = controller.createNewPlayer(input);
        } else {
            System.out.println(ConsoleColor.GREEN.ansiCode + "1 - New Game " + ConsoleColor.RESET.ansiCode + ConsoleColor.YELLOW.ansiCode + "\n2 - Load Game " + ConsoleColor.RESET.ansiCode);
            int choiceGame = InputValidation.readInt(input);
            if (choiceGame == 1) {
                player = controller.createNewPlayer(input);
            } else {
                player = saver.load();
                if (player == null) {
                    System.out.println("Failed to load save. Starting new game...");
                    return;
                }
            }
        }

        Enemy wolf = new Enemy("Wolf", 12, 3, player.getDifficulty(), 80, "You hear a growl... A rabid beast is staring right at you.");
        Enemy orc = new Enemy("Orc", 14, 5, player.getDifficulty(), 100, "'FRESH MEAT!' screams a massive warrior charging at you.");
        Enemy dragon = new Enemy("Dragon", 16, 7, player.getDifficulty(), 120, "The air burns around you. An ancient terror has awakened!");
        enemyList.add(wolf);
        enemyList.add(orc);
        enemyList.add(dragon);

        while (true) {
            if (player.getStage() > enemyList.size()) {
                System.out.println(ConsoleColor.YELLOW.ansiCode + "1 - Battle:(New enemies coming soon...)" + ConsoleColor.RESET.ansiCode);
            } else {
                System.out.println(ConsoleColor.BLUE.ansiCode + "1 - Battle: Stage(" + player.getStage() + ") " + ConsoleColor.RESET.ansiCode);
            }
            System.out.println(ConsoleColor.BLUE.ansiCode + "2 - Status \n3 - Shop \n4 - Inventory \n5 - Save \n0 - Exit:" + ConsoleColor.RESET.ansiCode);
            int choice = InputValidation.readInt(input);
            switch (choice) {
                case 1:
                    controller.runBattle(player, enemyList, input);
                    break;
                case 2:
                    System.out.println(player);
                    System.out.println("Press Enter to exit");
                    input.nextLine();
                    break;
                case 3:
                    shop.runShop(player, input);
                    break;
                case 4:
                    invController.manageInventory(player, input);
                    break;
                case 5:
                    saver.save(player);
                    break;
                case 0:
                    System.out.println("leaving the game");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}