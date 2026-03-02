package game;
import domain.entidy.*;
import domain.enums.*;
import domain.item.*;
import utils.InputValidation;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleSystem {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println(ConsoleColor.BLUE.ansiCode + "WELCOME TO GAMEDEV " + ConsoleColor.RESET.ansiCode);

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

        Enemy wolf = new Enemy("Wolf", 7, 3, difficulty.getReportname(), 80, 80, difficulty.getReportname(), "You hear a growl... A rabid beast is staring right at you.");
        Enemy orc = new Enemy("Orc", 9, 5, difficulty.getReportname(), 100, 100, difficulty.getReportname(), "'FRESH MEAT!' screams a massive warrior charging at you.");
        Enemy dragon = new Enemy("Dragon", 12, 7, difficulty.getReportname(), 120, 120, difficulty.getReportname(), "The air burns around you. An ancient terror has awakened!");

        Player player = new Player(name, 100, selectedClass.getStrength(), selectedClass.getDefense());

        Potion healPotion = new HealPotion();
        Potion strengthPotion = new StrengthPotion();

        Shop shop = new Shop();

        System.out.println("To start, you receive 10 gold.");
        player.setGold(10);

        int stage = 1;

        while (true) {
            System.out.println("1 - Battle: \n2 - Status: \n3 - Shop: \n4 - Inventory: \n0 - Exit:");
            int choice = InputValidation.readInt(input);

            switch (choice) {
                case 1:
                    Enemy currentEnemy = null;
                    if (stage == 1) {
                        currentEnemy = wolf;
                    } else if (stage == 2) {
                        currentEnemy = orc;
                    } else if (stage == 3) {
                        currentEnemy = dragon;
                    } else {
                        System.out.println(ConsoleColor.PURPLE.ansiCode + "You have become the strongest of the heroes." + ConsoleColor.RESET.ansiCode);
                        input.nextLine();
                        break;
                    }
                    System.out.println(ConsoleColor.PURPLE.ansiCode + currentEnemy.getDescription() + ConsoleColor.RESET.ansiCode);

                    while (player.isAlive()) {
                        System.out.println("=== Your Turn ===");
                        player.attack(currentEnemy);
                        System.out.println("\nYou dealt " + (player.getStrength() - currentEnemy.getDefense()) + " damage.");
                        System.out.println("Enemy HP " + currentEnemy.getCurrentLife());
                        System.out.println("Press Enter to continue...");
                        input.nextLine();

                        if (!currentEnemy.isAlive()) {
                            System.out.println(ConsoleColor.BLUE.ansiCode + "You defeated him." + ConsoleColor.RESET.ansiCode);
                            System.out.println();
                            System.out.println(ConsoleColor.YELLOW.ansiCode + "You receive 10 gold." + ConsoleColor.RESET.ansiCode);
                            player.setGold(player.getGold() + 10);
                            stage++;
                            System.out.println("Press Enter to continue...");
                            input.nextLine();
                            break;
                        }
                        System.out.println("\n=== ENEMY TURN ===");
                        System.out.println(currentEnemy.getName() + " prepares to attack\n");
                        System.out.println("1 - to defend \n2 - to dodge ");
                        int choiceDefend = InputValidation.readInt(input);

                        while (choiceDefend != 1 && choiceDefend != 2) {
                            System.out.println("Numero Invalido! digite 1 ou 2");
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
                            if (!player.isAlive()) {
                                System.out.println(ConsoleColor.RED.ansiCode + "game Over" + ConsoleColor.RESET.ansiCode);
                                player.setCurrentLife(100);
                                wolf.revive();
                                orc.revive();
                                dragon.revive();
                                stage = 1;
                                System.out.println("Press Enter to continue...");
                                input.nextLine();
                                break;
                            }
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

                                if (!player.isAlive()) {
                                    System.out.println(ConsoleColor.RED.ansiCode + " game Over " + ConsoleColor.RESET.ansiCode);
                                    player.setCurrentLife(100);
                                    wolf.revive();
                                    orc.revive();
                                    dragon.revive();
                                    stage = 1;
                                    System.out.println("Press Enter to continue...");
                                    input.nextLine();
                                    break;
                                }
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
                    System.out.println("=== MERCHANT ===");
                    System.out.println("Your gold " + player.getGold());
                    System.out.println("Which potion do you wish to buy?");

                    healPotion.showDetails();
                    strengthPotion.showDetails();

                    while (true) {
                        System.out.println("1 for buy healing potion \n2 for buy strength potion \n0 for exit");
                        int choicePotion = InputValidation.readInt(input);

                        if (choicePotion == 1) {
                            shop.sell(player, choicePotion);
                            System.out.println("You bought the healing potion. ");
                        } else if (choicePotion == 2) {
                            shop.sell(player, choicePotion);
                            System.out.println("You bought the strength potion. ");
                        } else if (choicePotion == 0) {
                            System.out.println("leaving the shop");
                            break;
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("=== INVENTORY ===");
                    ArrayList<Potion> bag = player.getInventory();

                    if (bag.isEmpty()) {
                        System.out.println("Your bag is empty.");
                    } else {
                        for (int i = 0; i < bag.size(); i++) {
                            System.out.println((i + 1) + " - " + bag.get(i).getName());
                        }
                        System.out.println("0 - Return");

                        System.out.println("Choose an item that you want to use.");
                        int item = InputValidation.readInt(input);


                        if (item > 0 && item <=bag.size()) {
                            Potion selectPotion = bag.get(item - 1);
                            selectPotion.use(player);
                            bag.remove(item - 1);

                            System.out.println("You use " + selectPotion.getName());
                            System.out.println("Current Life: " + player.getCurrentLife());
                            System.out.println("Current Strength: " + player.getStrength());

                        } else if (item == 0) {
                            System.out.println("Closing bag...");
                        } else {
                            System.out.println("Invalid item slot.");
                        }
                    }
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
