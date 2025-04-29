package OO.world;

import OO.logic.Game;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    // Creat a HashMap with every Room object in it.
    private static final Map<String, Room> rooms = new HashMap<String, Room>();

    public static void initializeWorld() {
        Room outsideHBF = new Room(
                "Outside HBF",
                List.of(
                        "The cold night air hits me as I step outside into the eerie silence.",
                        "The once bastling plaza in front of the station is now lifeless.",
                        "Ernst August Statue looms in the center,its bronze surface darkened with something that look like ... dried blood.",
                        "Broken bicycles and overturned trash cans are scattered across the cobblestones.",
                        "The faint hum and flickering  streetlights adds an ominous soundtrack to the stillness.",
                        "In the west across the street is the Ernst-August-Galerie a shopping enter that used to be full of life.",
                        "Its glass entrance doors are shattered and inside,the dim glow of emergency lights casts long shadows on the tiled floors."
                ),
                List.of(
                        "Inspect the statue.",
                        "Investigate the bicycles.",
                        "Enter Ernst-August-Galerie"
                ),
                List.of(
                        () -> {

                        },
                        () -> {
                            Game.moveToRoom(World.getRoom("nextRoomName"));
                        }
                )
        );

        rooms.put("outsideHBF", outsideHBF);
    }

    public static Room getRoom(String name) {
        return rooms.get(name);
    }
}
