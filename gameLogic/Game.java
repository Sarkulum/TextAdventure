package gameLogic;

import combat.Attack;
import items.StoryItems;
import map.Tutorial;
import player.Player;
import player.PlayerDecision;

public class Game {
    public static void StartGame() {
        PlayerDecision setParams = new PlayerDecision();

        // Create a UserData instance.
        Player player1 = Player.createPlayer(
                "ID1",
                setParams.setUserName(),
                false,
                setParams.setUserAge(),
                setParams.setTextColor(),
                20,
                5,
                1,
                "Knife",
                0
        );
        player1.setDEV(setParams.DEV());

        // Initialize the Attack singleton with the player
        Attack.initialize(player1);

        // Create story item status for player 1
        StoryItems storyItemsPlayer1 = new StoryItems();

        storyItemsPlayer1.setSilverRing(false);

        Tutorial.townGate(storyItemsPlayer1);
    }
}
