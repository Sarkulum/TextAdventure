package OO.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static PlayerManager instance;
    private static final List<Player> players = new ArrayList<>(); // A List to save all player objects and make them easy to access.
    private Player currentPlayer;

    private PlayerManager() {
        // Private empty constructor
    }

    // Function to get the singleton instance. In case it does not exist it creates one.
    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public void add(Player player) {
        players.add(player);
        if (currentPlayer == null) {
            currentPlayer = player;
        }
    }

    public void remove(Player player) {
        players.remove(player);
        if (currentPlayer == player) {
            currentPlayer = null;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public Player getPlayerByName(String name) {
        for (Player player : players) { // Iterates over the array
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
