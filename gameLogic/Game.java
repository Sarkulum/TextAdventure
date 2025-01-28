package gameLogic;

import combat.Attack;
import map.Empty1;
import map.Tutorial;
import player.Player;
import player.PlayerDecision;

public class Game {
    public static void StartGame() {
        PlayerDecision setParams = new PlayerDecision();

        // Create a Player instance.
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

        Tutorial.townGate();
        Empty1.EmptyRoom();
    }
}
