package gameLogic;

import combat.Attack;
import enemys.PercentBasedEnemy;
import items.Weapon;
import rooms.*;
import player.Player;
import player.PlayerDecision;
import player.Score;

import java.util.Random;

public class StoryGame {
    public static void initialSetUp() {
        Score.ensureFileExists("scores.txt");
        Score.ensureFileExists("player.txt");
        PlayerDecision setParams = new PlayerDecision();

        // Create a Player instance.
        Player player1 = Player.createPlayer(
                "ID1",
                setParams.setUserName(),
                false,
                setParams.setUserAge(),
                setParams.setTextColor(),
                20,
                1,
                5,
                "Fist",
                0,
                3
        );

        Weapon.creatWeapon("Fist", 0, 0, 0, 1);
        Weapon.equipWeapon("Fist");
        player1.setDEV(setParams.DEV());

        if(player1.DEV()){
            player1.setMaxHP(200);
            player1.setCurrentHP(200);
            player1.setMinDamage(100);
            player1.setMaxDamage(200);
            player1.setGoldCoins(100);
            player1.setMovementSpeed(100);
            Score.saveDEV();
        }

        checkPlayer();
    }

    public static void StartGame() {
        Random random = new Random();
        Player player1 = Player.getPlayer("ID1");

        if (!player1.DEV()) {
            while (!player1.getTutorialPassed()) {
                Intro.intro();
                Tutorial.townGate();
            }
            Room1.outsideHBF();
            Shop.buyUpgrades();
        }
        //Attack.initialize(player1);
            // Make random rooms and populate them.
            RandomRooms randomRoom = new RandomRooms();
            PercentBasedEnemy.resetIndex();
            PercentBasedEnemy.setEnemyArray("Walker", 60);
            PercentBasedEnemy.setEnemyArray("Shambler", 30);
            PercentBasedEnemy.setEnemyArray("Crawler", 10);
            randomRoom.setRandomRoom(random.nextInt(3, 5));

            PercentBasedEnemy.resetIndex();
            PercentBasedEnemy.setEnemyArray("Walker", 50);
            PercentBasedEnemy.setEnemyArray("Shambler", 30);
            PercentBasedEnemy.setEnemyArray("Crawler", 10);
            PercentBasedEnemy.setEnemyArray("Bloater", 10);
            randomRoom.setRandomRoom(random.nextInt(4, 6));

            PercentBasedEnemy.resetIndex();
            PercentBasedEnemy.setEnemyArray("Walker", 40);
            PercentBasedEnemy.setEnemyArray("Shambler", 30);
            PercentBasedEnemy.setEnemyArray("Crawler", 10);
            PercentBasedEnemy.setEnemyArray("Bloater", 10);
            PercentBasedEnemy.setEnemyArray("Brute", 10);
            randomRoom.setRandomRoom(random.nextInt(5, 7));

            PercentBasedEnemy.resetIndex();
            PercentBasedEnemy.setEnemyArray("Walker", 20);
            PercentBasedEnemy.setEnemyArray("Shambler", 30);
            PercentBasedEnemy.setEnemyArray("Crawler", 10);
            PercentBasedEnemy.setEnemyArray("Bloater", 20);
            PercentBasedEnemy.setEnemyArray("Brute", 15);
            PercentBasedEnemy.setEnemyArray("Necrofiend", 5);
            randomRoom.setRandomRoom(random.nextInt(6, 8));

            PercentBasedEnemy.resetIndex();
            PercentBasedEnemy.setEnemyArray("Walker", 10);
            PercentBasedEnemy.setEnemyArray("Shambler", 10);
            PercentBasedEnemy.setEnemyArray("Crawler", 25);
            PercentBasedEnemy.setEnemyArray("Bloater", 20);
            PercentBasedEnemy.setEnemyArray("Brute", 20);
            PercentBasedEnemy.setEnemyArray("Necrofiend", 15);
            randomRoom.setRandomRoom(random.nextInt(7, 10));

            // Start Room 2
            Room2.subwaySandwich();

            //Start puzzle
            Puzzle.intro();

        // Start End
        End.emptyEnd();
    }

    public static void checkPlayer() {
        Player player = Player.getPlayer("ID1");
        String playerName = player.getUserName();
        if (!Score.previousPlayer(playerName)) {
            StartGame();
        }else{
            System.out.println(player.getUserTextColor()+"\n------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("We have found out that you are a player who has previous played.");
            System.out.println("If you want to you can play the endless mode.");
            System.out.println("In endless mode you only fight and there is no end.\n");
            System.out.println("1. Endless mode");
            System.out.println("2. Story mode");

            int choice = PlayerDecision.inputWithCheck(2);

            if (choice == 1) {
                // Initialize the Attack singleton with the player
                Attack.initialize(player);
                EndlessMode.endlessMode();
            } else if (choice == 2) {
                StartGame();
            }
        }
    }
}
