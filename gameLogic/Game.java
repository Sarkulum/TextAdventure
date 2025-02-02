package gameLogic;

import combat.Attack;
import map.*;
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

        if(player1.DEV()){
            player1.setMaxHP(200);
            player1.setMinDamage(100);
            player1.setMaxDamage(200);
            player1.setGoldCoins(100);
        }

        // Initialize the Attack singleton with the player
        Attack.initialize(player1);

        Tutorial.townGate();
        player1.setTutorialPassed(true);
        Empty1.outsideHBF();
        Shop.buyUpgrades();

        RandomRooms randomRoom = new RandomRooms();
        randomRoom.setRandomRoom(3);
        randomRoom.setRandomRoom(4);
        randomRoom.setRandomRoom(6);
        randomRoom.setRandomRoom(7);
        randomRoom.setRandomRoom(10);
        Empty2.EmptyRoom2();
        EmptyPuzzle.puzzle1();
        EmptyEnd.emptyEnd();
    }
}
