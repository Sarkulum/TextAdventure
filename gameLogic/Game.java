package gameLogic;

import items.StoryItems;
import map.Tutorial;
import player.Player;

public class Game {
    public static void StartGame() {
        // Create a UserData instance.
        Player player1 = new Player();

        // Set all params.
        player1.setUserName();
        player1.setUserAge();
        player1.setTextColor();
        player1.setMaxDamage(5);
        player1.setMinDamage(1);
        player1.setCurrentHP(20);
        player1.setMaxHP(20);
        player1.setPlayerWeapon("Knife");

        // Create story item status for player 1
        StoryItems storyItemsPlayer1 = new StoryItems();

        storyItemsPlayer1.setSilverRing(false);

        Tutorial.townGate(player1, storyItemsPlayer1);
    }
}
