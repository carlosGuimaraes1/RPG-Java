package game;

import domain.entidy.Player;
import domain.item.HealPotion;
import domain.item.Potion;
import utils.GameSaver;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Potion> potions = new ArrayList<>();
        potions.add(new HealPotion());
        Player test = new Player("ze", 100, 10,5);
        GameSaver saver = new GameSaver();
        test.setGold(10);
        test.setStage(2);
        test.setInventory(potions);
        saver.save(test);
        System.out.println(saver.load());


    }
}
