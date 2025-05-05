package OO.world;

import OO.items.Weapon;
import OO.logic.Game;
import OO.player.Player;
import OO.player.Player.*;
import OO.player.PlayerManager;
import OO.text.TextColor;
import text.Colors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// If you want to "reenter" a room use World.getRoom("outsideHBF").enter();
public class World {
    // Creat a HashMap with every Room object in it.
    private static final Map<String, Room> rooms = new HashMap<String, Room>();
    private static PlayerManager playerManager = PlayerManager.getInstance();

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
                            Player player = playerManager.getCurrentPlayer();

                            System.out.println("You approach the statue carefully.");
                            System.out.println("\nOn its pedestal, someone has carved the words:");
                            System.out.println("'Follow the fog, trust the light.'\n");

                            if (player.playerHasItem("Crowbar")) {
                                System.out.println("You also notice " +TextColor.HIGH_RED.getAnsiCode()+ "a crowbar" +player.getUserTextColor().getAnsiCode()+ " leaning against the base of the statue.");
                                System.out.println("System:");
                                System.out.println("You have obtained weapon: 'Crowbar'");
                                System.out.println("It is not noticeably better than the kitchen knife.");

                                Weapon crowbar = new Weapon("Crowbar", 1, 5, 1, 0);
                                player.addItem(crowbar);
                                player.equipWeapon("Crowbar");
                            }
                            World.getRoom("outsideHBF").reenter();
                        },
                        () -> {
                            Game.moveToRoom(World.getRoom("nextRoomName"));
                        }
                )
        );

        Room startingRoom = new Room(
                "Intro",
                List.of(
                        "I woke up in a pitch-black alleyway. My head is pounding, and I can't remember how I got here.",
                        "I reach for my phone, but it's dead. Great. Just great.",
                        "The air is damp, filled with the distant hum of flickering streetlights.",
                        "Trash bins are overturned, and the stench of decay lingers.",
                        "As I push myself up, my eyes adjust to the dim surroundings.",
                        "The neon glow of Kröpke's empty streets is visible ahead but something feels off."
                ),
                List.of(),
                List.of()
        );

        rooms.put("outsideHBF", outsideHBF);
    }

    public static Room getRoom(String name) {
        return rooms.get(name);
    }
}
