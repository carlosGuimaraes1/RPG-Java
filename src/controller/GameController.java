package controller;

import domain.entity.*;
import domain.enums.*;
import utils.InputValidation;

import java.util.List;
import java.util.Scanner;

public class GameController { 
    public Player createNewPlayer(Scanner input) {
        System.out.println("Enter the name for your character");
        String name = input.nextLine();

        System.out.println("Choose your class:");
        CharacterClass selectedClass = null;

        while (selectedClass == null) {

            System.out.println(" 1 - for Warrior \n 2 - for Mage \n 3 - for Archer");
            int classChoice = InputValidation.readInt(input);

            switch (classChoice) {
                case 1:
                    selectedClass = CharacterClass.WARRIOR;
                    break;
                case 2:
                    selectedClass = CharacterClass.MAGE;
                    break;
                case 3:
                    selectedClass = CharacterClass.ARCHER;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("Choose the difficulty.");
        Difficulty difficulty = null;
        while (difficulty == null) {
            System.out.println(" 1 - for Easy \n 2 - for Normal \n 3 - for Hard");
            int difficultyChoice = InputValidation.readInt(input);

            switch (difficultyChoice) {
                case 1:
                    difficulty = Difficulty.EASY;
                    break;
                case 2:
                    difficulty = Difficulty.NORMAL;
                    break;
                case 3:
                    difficulty = Difficulty.HARD;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("To start, you receive 10 gold.");
        Player player = new Player(name, 100, selectedClass.getStrength(), selectedClass.getDefense(), difficulty, selectedClass);
        player.setGold(10);

        return player;
    }

    public void runBattle(Player player, List<Enemy> enemyList, Scanner input) {
        if (player.getStage() > enemyList.size()) {
            System.out.println(ConsoleColor.BLUE.ansiCode + "No enemies remain. New challengers coming soon..." + ConsoleColor.RESET.ansiCode);
            return;
        }
        Enemy currentEnemy = enemyList.get(player.getStage() - 1);
        System.out.println(ConsoleColor.PURPLE.ansiCode + currentEnemy.getDescription() + ConsoleColor.RESET.ansiCode);

        while (player.isAlive()) {
            System.out.println("=== Your Turn ===");

            System.out.println("\nYou dealt " + player.attack(currentEnemy) + " damage.");
            System.out.println("Enemy HP " + currentEnemy.getCurrentLife());
            System.out.println("Press Enter to continue...");
            input.nextLine();

            if (!currentEnemy.isAlive()) {
                player.nextStage();
                player.setGold(player.getGold() + 10);
                System.out.println(ConsoleColor.BLUE.ansiCode + "You defeated him." + ConsoleColor.RESET.ansiCode);
                System.out.println();
                if (player.getStage() > enemyList.size()) {
                    System.out.println(ConsoleColor.GREEN.ansiCode + "The dungeon has been cleansed. You have proven your worth and become the strongest among the strong!" + ConsoleColor.RESET.ansiCode);
                }
                System.out.println(ConsoleColor.YELLOW.ansiCode + "You receive 10 gold." + ConsoleColor.RESET.ansiCode);
                player.setXp(currentEnemy.getXp());
                if (player.levelUp()) System.out.println("You have leveled up to level"+ player.getLevel()+"\nYou gained 1 more point in strength and defense. ");
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
                System.out.println(ConsoleColor.RED.ansiCode + "Game Over" + ConsoleColor.RESET.ansiCode);
                for (Enemy enemy : enemyList) {
                    enemy.revive();
                }
                player.revive();
                player.resetStage();
                System.out.println("Press Enter to continue...");
                input.nextLine();
                break;
            }
        }
    }
}