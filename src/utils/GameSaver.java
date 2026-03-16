package utils;

import domain.entidy.Player;
import domain.enums.*;
import domain.item.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameSaver {
    public void save(Player player) {
        File fileDirectory = new File("saves");
        if (!fileDirectory.exists()){
            boolean mkdir = fileDirectory.mkdir();
        }
        File file = new File(fileDirectory,"save.txt");

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String playerData = player.getName() + "," + player.getCurrentLife() + "," + player.getStrength() + "," + player.getDefense() + "," + player.getGold() + ","
                    + player.getStage() + "," + player.getCharacterClass() + "," + player.getDifficulty();

            bw.write(playerData);
            bw.flush();
            if (player.getInventory() != null && !player.getInventory().isEmpty()) {
                StringBuilder bag = new StringBuilder();
                bw.newLine();
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (i == player.getInventory().size() - 1) {
                        bag.append(player.getInventory().get(i).getClass().getSimpleName());
                    } else {
                        bag.append(player.getInventory().get(i).getClass().getSimpleName()).append(",");
                    }
                }
                bw.write(bag.toString());
            }
        } catch (IOException e) {
            System.out.println("Failed to save game." + e);
        }
        System.out.println(ConsoleColor.BLUE.ansiCode + "Game saved" + ConsoleColor.RESET.ansiCode);
    }

    public Player load() {
        Player player;
        File file = new File("saves","save.txt");
        try (Scanner s = new Scanner(file)) {
            String playerStatus = s.nextLine();

            String[] status = playerStatus.split(",");
            String name = status[0];
            int life = Integer.parseInt(status[1]);
            int strength = Integer.parseInt(status[2]);
            int defense = Integer.parseInt(status[3]);
            int gold = Integer.parseInt(status[4]);
            int stage = Integer.parseInt(status[5]);
            CharacterClass characterClass = CharacterClass.valueOf(status[6]);
            Difficulty difficulty = Difficulty.valueOf(status[7]);

            player = new Player(name, life, strength, defense, difficulty, characterClass);
            player.setGold(gold);
            player.setStage(stage);
            player.setCharacterClass(characterClass);
            player.setDifficulty(difficulty);
            if (s.hasNextLine()) {
                ArrayList<Potion> potions = new ArrayList<>();
                String bag = s.nextLine();
                String[] inv = bag.split(",");
                for (int i = 0; i < inv.length; i++) {
                    if (inv[i].equals("HealPotion")) {
                        potions.add(new HealPotion());
                    } else if (inv[i].equals("StrengthPotion")) {
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
