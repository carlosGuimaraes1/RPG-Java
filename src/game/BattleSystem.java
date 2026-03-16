package game;

import controller.InventoryController;
import domain.entidy.*;
import domain.enums.*;
import domain.item.*;
import utils.*;

import java.io.File;
import java.util.*;

public class BattleSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CharacterClass selectedClass = null;
        Difficulty difficulty = null;
        Player player = new Player();
        List<Enemy> enemyList = new ArrayList<>();
        GameSaver saver = new GameSaver();

        System.out.println(ConsoleColor.BLUE.ansiCode + "WELCOME TO GAME DEV " + ConsoleColor.RESET.ansiCode);

        File file = new File("saves", "save.txt");
        if (file.exists()) {
            System.out.println(ConsoleColor.GREEN.ansiCode + "1 - New Game " + ConsoleColor.RESET.ansiCode + ConsoleColor.YELLOW.ansiCode + "\n2 - Load Game " + ConsoleColor.RESET.ansiCode);
            int choiceGame = InputValidation.readInt(input);
            if (choiceGame == 2) {
                player = saver.load();
                if (player == null){
                    System.out.println("Failed to load save. Starting new game...");
                    return;
                }
            }
        } else {

            System.out.println("Enter the name for your character");
            String name = input.nextLine();

            System.out.println("Choose your class:");

            while (selectedClass == null) {

                System.out.println(" 1 - for Warrior \n 2 - for Mage \n 3 - for Archer");
                int classChoice = InputValidation.readInt(input);

                switch (classChoice) {
                    case 1:
                        selectedClass = CharacterClass.WARRIOR;
                        player.setCharacterClass(selectedClass);
                        break;
                    case 2:
                        selectedClass = CharacterClass.MAGE;
                        player.setCharacterClass(selectedClass);
                        break;
                    case 3:
                        selectedClass = CharacterClass.ARCHER;
                        player.setCharacterClass(selectedClass);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            System.out.println("Choose the difficulty.");
            while (difficulty == null) {
                System.out.println(" 1 - for Easy \n 2 - for Normal \n 3 - for Hard");
                int difficultyChoice = InputValidation.readInt(input);

                switch (difficultyChoice) {
                    case 1:
                        difficulty = Difficulty.EASY;
                        player.setDifficulty(difficulty);
                        break;
                    case 2:
                        difficulty = Difficulty.NORMAL;
                        player.setDifficulty(difficulty);
                        break;
                    case 3:
                        difficulty = Difficulty.HARD;
                        player.setDifficulty(difficulty);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            player = new Player(name, 100, selectedClass.getStrength(), selectedClass.getDefense(), player.getDifficulty(), player.getCharacterClass());
            System.out.println("To start, you receive 10 gold.");
            player.setGold(10);
        }


        Enemy wolf = new Enemy("Wolf", 7, 3, player.getDifficulty().toString(), 80, 80, player.getDifficulty().toString(), "You hear a growl... A rabid beast is staring right at you.");
        Enemy orc = new Enemy("Orc", 9, 5, player.getDifficulty().toString(), 100, 100, player.getDifficulty().toString(), "'FRESH MEAT!' screams a massive warrior charging at you.");
        Enemy dragon = new Enemy("Dragon", 12, 7, player.getDifficulty().toString(), 120, 120, player.getDifficulty().toString(), "The air burns around you. An ancient terror has awakened!");

        enemyList.add(wolf);
        enemyList.add(orc);
        enemyList.add(dragon);

        Shop shop = new Shop();
        while (true) {
            System.out.println("1 - Battle: Stage(" + player.getStage() + ") \n2 - Status: \n3 - Shop: \n4 - Inventory: \n5 - Save: \n0 - Exit:");
            int choice = InputValidation.readInt(input);

            switch (choice) {
                case 1:
                    Enemy currentEnemy;
                    for (int i = player.getStage() - 1; i < enemyList.size(); i++) {
                        currentEnemy = enemyList.get(i);
                        System.out.println(ConsoleColor.PURPLE.ansiCode + currentEnemy.getDescription() + ConsoleColor.RESET.ansiCode);

                        while (player.isAlive()) {
                            System.out.println("=== Your Turn ===");
                            player.attack(currentEnemy);

                            System.out.println("\nYou dealt " +  " damage.");
                            System.out.println("Enemy HP " + currentEnemy.getCurrentLife());
                            System.out.println("Press Enter to continue...");
                            input.nextLine();

                            if (!currentEnemy.isAlive()) {
                                System.out.println(ConsoleColor.BLUE.ansiCode + "You defeated him." + ConsoleColor.RESET.ansiCode);
                                System.out.println();
                                System.out.println(ConsoleColor.YELLOW.ansiCode + "You receive 10 gold." + ConsoleColor.RESET.ansiCode);
                                player.setGold(player.getGold() + 10);
                                player.nextStage();
                                System.out.println("Press Enter to continue...");
                                input.nextLine();
                                break;
                            }
                            System.out.println("\n=== ENEMY TURN ===");
                            System.out.println(currentEnemy.getName() + " prepares to attack\n");
                            System.out.println("1 - to defend \n2 - to dodge ");
                            int choiceDefend = InputValidation.readInt(input);

                            while (choiceDefend != 1 && choiceDefend != 2) {
                                System.out.println("Invalid number! Please enter 1 or 2.");
                                System.out.println("1 - to defend \n2 - to dodge ");
                                choiceDefend = InputValidation.readInt(input);
                            }
                            if (choiceDefend == 1) {
                                double reducedDamage = currentEnemy.getStrength() - 2;
                                int realDamage = player.receiveDamage((int) reducedDamage);

                                System.out.println("You received " + realDamage + " damage.");
                                System.out.println("Your life " + player.getCurrentLife());
                                System.out.println("Press Enter to continue...");
                                input.nextLine();
                            }
                            if (choiceDefend == 2) {
                                if (player.dodge()) {
                                    System.out.println("you deviated. you received no harm.");
                                    System.out.println("Press Enter to continue...");
                                    input.nextLine();
                                } else {
                                    int reducedDamage = (int) (currentEnemy.getStrength() * 1.5);
                                    int realDamage = player.receiveDamage(reducedDamage);

                                    System.out.println("You received " + realDamage + " damage.");
                                    System.out.println("Your life " + player.getCurrentLife());
                                    System.out.println("Press Enter to continue...");
                                    input.nextLine();
                                }
                            }
                            if (!player.isAlive()) {
                                System.out.println(ConsoleColor.RED.ansiCode + "game Over" + ConsoleColor.RESET.ansiCode);
                                for (int j = 0; j < enemyList.size(); j++) {
                                    enemyList.get(j).revive();
                                }
                                player.revive();
                                player.resetStage();
                                System.out.println("Press Enter to continue...");
                                input.nextLine();
                                break;
                            }
                        }
                    }
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
                    InventoryController invController = new InventoryController();
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
