package map;

import player.Player;

import java.util.Scanner;

public class Shop {
    static Player player = Player.getPlayer("ID1");
    static Scanner scanner = new Scanner(System.in);

    public static void buyUpgrades(){
        if(player.getFirstShopVisit()){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Hello "+player.getUserName()+" welcome to my humble shop.");
            System.out.println("Here you can buy permanent upgrades for all sorts of stats.");
            System.out.println("------------------------------------------------------------------\n");
            player.setFirstShopVisit(false);
            scanner.nextLine();
        }
        int hpDif = player.getMaxHP()-player.getCurrentHP();
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("These are your current stats:");
        System.out.println("Your minimum damage is: "+player.getMinDamage());
        System.out.println("Your maximum damage is: "+player.getMaxDamage());
        System.out.println("Your maximum hp are: "+player.getMaxHP());
        System.out.println("Your current hp are: "+player.getCurrentHP());
        System.out.println("------------------------------------------------------------------\n");
        scanner.nextLine();
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("You can buy the following:");
        System.out.println("1. A upgrade to your minimum damage(+1)");
        System.out.println("2. A upgrade to your maximum damage(+1)");
        System.out.println("3. A Upgrade to your maximum hp(+1)");
        System.out.println("4. A heal that heals you to full hp("+hpDif+")");
        System.out.println("------------------------------------------------------------------\n");

        int choice = scanner.nextInt();
        goldCheckAndBuy(choice);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Thanks for visiting my shop. \nYou can come back whenever you die and Respawn here.");
        System.out.println("------------------------------------------------------------------\n");
        scanner.nextLine();
    }

    public static void goldCheckAndBuy(int choice) {
        if (
                player.getGoldCoins() < 10 && choice == 1
                || player.getGoldCoins() < 20 && choice == 2
                || player.getGoldCoins() < 30 && choice == 3
                || player.getGoldCoins() < 15 && choice == 4
        ) {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("You do not have enough gold for choice "+choice+".");
            System.out.println("------------------------------------------------------------------\n");
            scanner.nextLine();
        }else{
            switch (choice) {
                case 1 -> player.setMinDamage(player.getMinDamage() + 1);
                case 2 -> player.setMaxDamage(player.getMaxDamage() + 1);
                case 3 -> player.setMaxHP(player.getMaxHP() + 1);
                case 4 -> player.setCurrentHP(player.getMaxHP());
            }
        }
    }
}
