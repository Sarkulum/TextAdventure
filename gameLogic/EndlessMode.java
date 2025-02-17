package gameLogic;

import enemys.PercentBasedEnemy;
import rooms.RandomRooms;
import rooms.Shop;
import player.Player;
import player.PlayerDecision;
import player.Score;

public class EndlessMode {
    static int waveIndex = 1; // Saves the wave you are at
    static boolean fistVisit = true; // Used to display the previous scores if you visit the first time

    public static void endlessMode() {
        int score = 0; // Score is the amount of waves and for each the current wave value
        RandomRooms randomRooms = new RandomRooms(); // Make a random room instance
        Player player = Player.getPlayer("ID1"); // Get player
        player.setEndless(true); // Set the game mode boolean of player to true

        // Display score once
        if (fistVisit) {
            Score.displayScores();
            fistVisit = false;
        }
        // Call shop
        Shop.buyUpgrades();

        // Check for player alive status and
        while (player.getCurrentHP() > 0) {
            PercentBasedEnemy.adjustEnemySpawnRates(waveIndex);
            randomRooms.setRandomRoom(waveIndex);
            waveIndex++;
        }

        PercentBasedEnemy.resetIndex();
        // Ask the player if the want to continue
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Do you want to continue or do you want to exit?");
        System.out.println("If you continue you respawn in the shop and you can buy some new Upgrades.");
        System.out.println("You will also restart at the latest Wave.");
        System.out.println("If you exit your Score will be saved to score.txt and the program will stop.\n");
        System.out.println("1. Continue");
        System.out.println("2. Exit");

        int choice = PlayerDecision.inputWithCheck(2); // Input with try catch and so on

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
