package rooms;

import gameLogic.StoryGame;
import items.Weapon;
import player.Player;
import player.PlayerDecision;
import text.Colors;
import java.util.Scanner;

public class Shop {
    static Player player = Player.getPlayer("ID1");
    static Scanner scanner = new Scanner(System.in);
    static int choice;

    public static void buyUpgrades() {
        if (!player.getKey() && !player.isEndless()) {

            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");

            if (player.getFirstShopVisit()) {
                System.out.println("You could talke to the person behind the counter but you don't know them and this is a zombie Apocalypse soooo ...");
                System.out.println("1. Talk to the stranger.");

            } else {
                System.out.println("You could talke to the Shopkeeper OR you could go back to the Statue.");
                System.out.println("Maybe you have missed something.");
                System.out.println("1. Talk to the Shopkeeper.");

            }

            System.out.println("2. Go back to the Statue.");
            choice = PlayerDecision.inputWithCheck(2);
            if (choice == 2) {
                Room1.eaStatue();
            }

        }
        if (player.getFirstShopVisit()) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Shopkeeper: Hello " + player.getUserName() + " welcome to my humble shop.");
            System.out.println("Here you can buy permanent upgrades for all sorts of stats.");
            System.out.println("--------------------------->press enter to continue\n");
            player.setFirstShopVisit(false);
            scanner.nextLine();
        }
        int hpDif = player.getMaxHP() - player.getCurrentHP();
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("System: These are your current stats:");
        System.out.println("Your " + Colors.RED + "minimum damage" + player.getUserTextColor() + " is: " + player.getMinDamage());
        System.out.println("Your " + Colors.RED + "maximum damage" + player.getUserTextColor() + " is: " + player.getMaxDamage());
        System.out.println("Your " + Colors.GREEN + "maximum hp" + player.getUserTextColor() + " are: " + player.getMaxHP());
        System.out.println("Your " + Colors.GREEN + "current hp" + player.getUserTextColor() + " are: " + player.getCurrentHP());
        System.out.println("--------------------------->press enter to continue\n");
        scanner.nextLine();
        if (player.getMinDamage() < player.getMaxDamage()-1) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("System: You can buy the following:");
            System.out.println("1. A upgrade to your " + Colors.RED + "minimum damage" + player.getUserTextColor() + "(+1).  Costs: " + Colors.YELLOW + "10 gold" + player.getUserTextColor() + ".");
            System.out.println("2. A upgrade to your " + Colors.RED + "maximum damage" + player.getUserTextColor() + "(+1).  Costs: " + Colors.YELLOW + "10 gold" + player.getUserTextColor() + ".");
            System.out.println("3. A Upgrade to your " + Colors.GREEN + "maximum hp" + player.getUserTextColor() + "(+1).      Costs: " + Colors.YELLOW + "30 gold" + player.getUserTextColor() + ".");
            System.out.println("4. A heal that " + Colors.GREEN + "heals " + player.getUserTextColor() + "you to full hp(" + hpDif + ").   Costs: " + Colors.YELLOW + "15 gold" + player.getUserTextColor() + ".");
            System.out.println("5. A gun that can attack over Range for " + Colors.RED + "20 max damage" + player.getUserTextColor() + ".   Costs: " + Colors.YELLOW + "50 gold" + player.getUserTextColor() + ".");
            System.out.println("6. Don't buy anything.");

            choice = PlayerDecision.inputWithCheck(6);
            goldCheckAndBuy(choice);
        }else{
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("System: You can buy the following:");
            System.out.println("1. A upgrade to your " + Colors.RED + "maximum damage" + player.getUserTextColor() + "(+1).  Costs: " + Colors.YELLOW + "10 gold" + player.getUserTextColor() + ".");
            System.out.println("2. A Upgrade to your " + Colors.GREEN + "maximum hp" + player.getUserTextColor() + "(+1).      Costs: " + Colors.YELLOW + "30 gold" + player.getUserTextColor() + ".");
            System.out.println("3. A heal that " + Colors.GREEN + "heals " + player.getUserTextColor() + "you to full hp(" + hpDif + ").   Costs: " + Colors.YELLOW + "15 gold" + player.getUserTextColor() + ".");
            System.out.println("4. A gun that can attack over Range for " + Colors.RED + "20 max damage" + player.getUserTextColor() + ".   Costs: " + Colors.YELLOW + "50 gold" + player.getUserTextColor() + ".");
            System.out.println("5. Don't buy anything.");

            choice = PlayerDecision.inputWithCheck(5);
            goldCheckAndBuy(choice+1);
        }
    }


    public static void goldCheckAndBuy(int choice) {
        if (
                player.getGoldCoins() < 10 && choice == 1
                || player.getGoldCoins() < 20 && choice == 2
                || player.getGoldCoins() < 30 && choice == 3
                || player.getGoldCoins() < 15 && choice == 4
                || player.getGoldCoins() < 50 && choice == 5
        ) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("System: You do not have enough gold for choice "+choice+".");
            System.out.println("--------------------------->press enter to continue\n");
            scanner.nextLine();
        }else {
            if (choice == 1) {
                player.setMinDamage(player.getMinDamage() + 1);
                player.setGoldCoins(player.getGoldCoins() -10);
                goodBye();
            } else if (choice == 2) {
                player.setMaxDamage(player.getMaxDamage() + 1);
                player.setGoldCoins(player.getGoldCoins() -20);
                goodBye();
            } else if (choice == 3) {
                player.setMaxHP(player.getMaxHP() + 1);
                player.setGoldCoins(player.getGoldCoins() -30);
                goodBye();
            } else if (choice == 4) {
                player.setCurrentHP(player.getMaxHP());
                player.setGoldCoins(player.getGoldCoins() -15);
                goodBye();
            } else if (choice == 5) {
                Weapon.creatWeapon("Gun", 0, 5, 20, 5);
                Weapon.equipWeapon("Gun");
                player.setGoldCoins(player.getGoldCoins() -50);
                goodBye();
            } else if (choice == 6) {
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Shopkeeper: Thanks for visiting my shop.");
                System.out.println("You can come back whenever you die and Respawn here.");
                System.out.println("--------------------------->press enter to continue\n");
                scanner.nextLine();

                if (player.isDied()) {
                    if (player.getRandomRoom() == 1) {
                        player.setDied(false);
                        StoryGame.randomRoom1();
                    } else if (player.getRandomRoom() == 2) {
                        player.setDied(false);
                        StoryGame.randomRoom2();
                    } else if (player.getRandomRoom() == 3) {
                        player.setDied(false);
                        StoryGame.randomRoom3();
                    } else if (player.getRandomRoom() == 4) {
                        player.setDied(false);
                        StoryGame.randomRoom4();
                    } else if (player.getRandomRoom() == 5) {
                        player.setDied(false);
                        StoryGame.randomRoom5();
                    }
                }
            }
        }
    }

    public static void goodBye() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Do you want to make another purchase?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        choice = PlayerDecision.inputWithCheck(2);

        if (choice == 1) {
            buyUpgrades();
        } else if (choice == 2) {
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Shopkeeper: Thanks for visiting my shop.");
            System.out.println("You can come back whenever you die and Respawn here.");
            System.out.println("--------------------------->press enter to continue\n");
            scanner.nextLine();

            if (player.isDied()) {
                if (player.getRandomRoom() == 1) {
                    player.setDied(false);
                    StoryGame.randomRoom1();
                } else if (player.getRandomRoom() == 2) {
                    player.setDied(false);
                    StoryGame.randomRoom2();
                } else if (player.getRandomRoom() == 3) {
                    player.setDied(false);
                    StoryGame.randomRoom3();
                } else if (player.getRandomRoom() == 4) {
                    player.setDied(false);
                    StoryGame.randomRoom4();
                } else if (player.getRandomRoom() == 5) {
                    player.setDied(false);
                    StoryGame.randomRoom5();
                }
            }
        }
    }
}
