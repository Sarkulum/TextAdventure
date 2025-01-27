package map;

import player.Player;

import java.util.Scanner;

public class Shop {
    Player player = Player.getPlayer("ID1");
    Scanner scanner = new Scanner(System.in);

    public void buyUpgrades(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You can buy a minDamage, maxDamage, maxHP upgrade and a Heal.");
        System.out.println("------------------------------------------------------------------\n");

        int choice = scanner.nextInt();

        switch (choice){
            case 1 -> player.setMinDamage(player.getMinDamage()+1);
            case 2 -> player.setMaxDamage(player.getMaxDamage()+1);
            case 3 -> player.setMaxHP(player.getMaxHP()+1);
            case 4 -> player.setCurrentHP(player.getMaxHP());
        }
    }
}
