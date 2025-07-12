package player;

import enemy.Enemy;
import map.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager instance;
    Map<Player, Position> players = new HashMap<>(); // A List to save all player objects and make them easy to access.
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
        players.put(player, null);
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
        for (Map.Entry<Player, Position> entry : players.entrySet()) { // Iterates over the array
            Player player = entry.getKey();

            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public Position getPositionCurrentPlayer() {
        for (Map.Entry<Player, Position> entry : players.entrySet()) {
            if (currentPlayer == entry.getKey()) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void setPositionCurrentPlayer(Position position) {
        for (Map.Entry<Player, Position> entry : players.entrySet()) {
            if (currentPlayer == entry.getKey()) {
                entry.setValue(position);
            }
        }
    }

    public Map<Player, Position> getPlayers() {
        return players;
    }
}
