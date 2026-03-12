package utils;

import domain.entidy.Player;
import domain.enums.ConsoleColor;
import domain.item.HealPotion;
import domain.item.Potion;
import domain.item.StrengthPotion;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameSaver {
    public void save(Player player) {
        File file = new File("save.txt");
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            String playerData = player.getName() + "," + player.getCurrentLife() + "," + player.getStrength() + "," + player.getDefense() + "," + player.getGold() + "," + player.getStage();
            bw.write(playerData);
            bw.flush();
            if (player.getInventory() != null && !player.getInventory().isEmpty()) {
                StringBuilder bag = new StringBuilder();
                bw.newLine();
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (i == player.getInventory().size() - 1) {
                        bag.append(player.getInventory().get(i).getName());
                    } else {
                        bag.append(player.getInventory().get(i).getName() + ",");
                    }
                }
                bw.write(bag.toString());
                System.out.println(ConsoleColor.BLUE.ansiCode + "Game saved" + ConsoleColor.RESET.ansiCode);
            }
        } catch (IOException e) {
            System.out.println("Failed to save game." + e);
        }
    }

    public Player load() {
        Player player;
        File file = new File("save.txt");
        try (Scanner s = new Scanner(file)) {
            String playerStatus = s.nextLine();

            String[] status = playerStatus.split(",");
            String name = status[0];
            int life = Integer.parseInt(status[1]);
            int strength = Integer.parseInt(status[2]);
            int defense = Integer.parseInt(status[3]);
            int gold = Integer.parseInt(status[4]);
            int stage = Integer.parseInt(status[5]);

            player = new Player(name, life, strength, defense);
            player.setGold(gold);
            player.setStage(stage);

            if (s.hasNextLine()) {
                ArrayList<Potion> potions = new ArrayList<>();
                String bag = s.nextLine();
                String[] inv = bag.split(",");
                for (int i = 0; i <inv.length ; i++) {
                    if (inv[i].equals("Heal potion")) {
                        potions.add(new HealPotion());
                    } else if (inv[i].equals("Strength potion")) {
                        potions.add(new StrengthPotion());
                    }
                }
                player.setInventory(potions);
            }
            return player;
        } catch (IOException e) {
            System.out.println("failed to load " + e);
        }
        return null;
    }
}
