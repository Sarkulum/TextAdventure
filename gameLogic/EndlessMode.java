package gameLogic;

import map.RandomRooms;
import map.Shop;
import player.Player;
import player.PlayerDecision;
import player.Score;

public class EndlessMode {
    static int waveIndex = 1;
    static boolean fistVisit = true;

    public static void endlessMode() {
        int score = 0;
        RandomRooms randomRooms = new RandomRooms();
        Player player = Player.getPlayer("ID1");

        if (fistVisit) {
            Score.displayScores();
            fistVisit = false;
        }
        Shop.buyUpgrades();


        while (player.getCurrentHP() > 0) {
            randomRooms.setRandomRoom(waveIndex);
            waveIndex++;
        }

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Do you want to continue or do you want to exit?");
        System.out.println("If you continue you respawn in the shop and you can buy some new Upgrades.");
        System.out.println("You will also restart at the latest Wave.");
        System.out.println("If you exit your Score will be saved to score.txt and the program will stop.\n");
        System.out.println("1. Continue");
        System.out.println("2. Exit");

        int choice = PlayerDecision.inputWithCheck(2);

        if (choice == 1) {
            endlessMode();
        } else if (choice == 2) {
            for (int i = 0; i <= waveIndex; i++) {
                score = score + i;
            }
            Score.saveScore(score);
        }
    }
}
