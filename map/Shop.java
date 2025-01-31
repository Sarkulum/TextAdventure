package map;

import player.Player;
import text.Colors;

import java.util.Scanner;

public class Shop {
    static Player player = Player.getPlayer("ID1");
    static Scanner scanner = new Scanner(System.in);

    public static void buyUpgrades(){
        if(player.getFirstShopVisit()){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Shopkeeper: Hello "+player.getUserName()+" welcome to my humble shop.");
            System.out.println("Here you can buy permanent upgrades for all sorts of stats.");
            System.out.println("--------------------------->press enter to continue");
            player.setFirstShopVisit(false);
            scanner.nextLine();
        }
        int hpDif = player.getMaxHP()-player.getCurrentHP();
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System: These are your current stats:");
        System.out.println("Your "+Colors.RED+"minimum damage"+ player.getUserTextColor()+" is: "+player.getMinDamage());
        System.out.println("Your "+Colors.RED+"maximum damage"+ player.getUserTextColor()+" is: "+player.getMaxDamage());
        System.out.println("Your "+Colors.GREEN+"maximum hp"+ player.getUserTextColor()+" are: "+player.getMaxHP());
        System.out.println("Your "+Colors.GREEN+"current hp"+ player.getUserTextColor()+" are: "+player.getCurrentHP());
        System.out.println("--------------------------->press enter to continue");
        scanner.nextLine();
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("System: You can buy the following:");
        System.out.println("1. A upgrade to your "+Colors.RED+"minimum damage"+player.getUserTextColor()+ "(+1).  Costs: "+Colors.YELLOW+"10 gold"+player.getUserTextColor()+".");
        System.out.println("2. A upgrade to your "+Colors.RED+"maximum damage"+ player.getUserTextColor()+"(+1).  Costs: "+Colors.YELLOW+"10 gold"+player.getUserTextColor()+".");
        System.out.println("3. A Upgrade to your "+Colors.GREEN+"maximum hp"+ player.getUserTextColor()+"(+1).      Costs: "+Colors.YELLOW+"30 gold"+player.getUserTextColor()+".");
        System.out.println("4. A heal that " + Colors.GREEN+ "heals " + player.getUserTextColor()+ "you to full hp("+hpDif+").   Costs: "+Colors.YELLOW+"15 gold"+player.getUserTextColor()+".");
        System.out.println("5. Don't buy anything.");
        System.out.println("--------------------------->press enter to continue");

        int choice = scanner.nextInt();
        goldCheckAndBuy(choice);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Shopkeeper: Thanks for visiting my shop. \nYou can come back whenever you die and Respawn here.");
        System.out.println("--------------------------->press enter to continue");
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
            System.out.println("System: You do not have enough gold for choice "+choice+".");
            System.out.println("--------------------------->press enter to continue");
            scanner.nextLine();
        }else if(choice == 5){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Shopkeeper: You can always come back later.");
            System.out.println("--------------------------->press enter to continue");
            scanner.nextLine();
        }else {
            switch (choice) {
                case 1 -> player.setMinDamage(player.getMinDamage() + 1);
                case 2 -> player.setMaxDamage(player.getMaxDamage() + 1);
                case 3 -> player.setMaxHP(player.getMaxHP() + 1);
                case 4 -> player.setCurrentHP(player.getMaxHP());
            }
        }
    }
}
