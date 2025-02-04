package player;

import java.io.*;

public class Score {
    public static final String FILE_NAME_PLAYER = "player.txt";
     public static final String FILE_NAME_SCORE = "scores.txt";
     //public static int score = 0;
     static Player player = Player.getPlayer("ID1");


    public static void ensureFileExists(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
                System.out.println(fileName + " created.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating " + fileName);
            e.printStackTrace();
        }
    }

    // Save a score to the file
    public static void saveScore(int score) {
        String playerName = player.getUserName();
        try (FileWriter writer = new FileWriter(FILE_NAME_SCORE, true)) { // 'true' enables appending
            writer.write(playerName + ": " + score + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the score.");
            e.printStackTrace();
        }
    }


    // Read and display all scores
    public static void displayScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_SCORE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the scores.");
            e.printStackTrace();
        }
    }

    public static void savePlayer() {
        String playerName = player.getUserName();
        try (FileWriter writer = new FileWriter(FILE_NAME_SCORE, true)) { // 'true' enables appending
            writer.write(playerName+ ",");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the score.");
            e.printStackTrace();
        }
    }

    // Check if player played before
    public static boolean previousPlayer() {
        String playerName = player.getUserName();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME_PLAYER))) {
            String line = br.readLine(); // Read the first line
            if (line != null) {
                String[] players = line.split(","); // Split players by ","

                for (String player : players) {
                    if (playerName.equals(player/*.trim()*/)) { // Trim to remove spaces
                        System.out.println(playerName + " found!");
                        return true;
                    }
                }
            }
            // System.out.println(playerName + " not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
