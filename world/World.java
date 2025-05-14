package world;

import items.Weapon;
import logic.Game;
import player.Player;
import player.PlayerManager;
import text.TextColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


// If you want to "reenter" a room use World.getRoom("outsideHBF").enter();
public class World {
    // Creat a HashMap with every Room object in it.
    private static final Map<String, Room> rooms = new HashMap<String, Room>();
    private static final PlayerManager playerManager = PlayerManager.getInstance();

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
                List.of(
                        "Continue"
                ),
                List.of(
                        () -> {
                            World.getRoom("Kröpke").enter();
                        }
                )
        );

        Room townGate = new Room(
                "Kröpke",
                List.of(
                        "A thick, smoky wall blocks a narrow passage leading further into the city.",
                        "A lone figure sits in front of it, casually exhaling smoke into the already heavy air."
                ),
                List.of(
                    "Talk to the person",
                    "Smack them",
                    "Do nothing",
                    "Go north to the crossroad"
                ),
                List.of(
                        () -> {
                            Player player = playerManager.getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);

                            if (player.playerHasItem("cigarettes")) {
                                System.out.println("Person:");
                                System.out.println("'Thanks a lot, mate. Here, now you can go further.'\n");
                                System.out.println("The person inhales the smoke as if it is nothing, and the smoky wall dissipates.");

                                scanner.nextLine();
                            } else {
                                System.out.println("Person:");
                                System.out.println("'Welcome, nice to see another survivor.'");
                                System.out.println("If you bring me a pack of cigarettes, "+player.getName()+", I'll let you through that smoky wall.");

                                scanner.nextLine();
                                World.getRoom("Kröpke").reenter();
                            }
                        },
                        () -> {
                            Player player = playerManager.getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);

                            System.out.println("Person: 'Hey what's wrong with you?'");
                            System.out.println("The person bonks you on the head.");
                            System.out.println("For some reason, you feel like picking a fight isn't the best idea.");
                            System.out.println("\nSystem:");
                            System.out.println("You receive " + TextColor.RED.getAnsiCode() + "1 damage" + player.getUserTextColor().getAnsiCode() + ".");
                            player.setCurrentHP(player.getCurrentHP() - 1);
                            System.out.println("Your" + TextColor.GREEN.getAnsiCode() + " HP: " + player.getCurrentHP() + player.getUserTextColor().getAnsiCode());

                            scanner.nextLine();
                            World.getRoom("Kröpke").reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You decide to leave the smoky wall and the strange person behind, heading toward Kröpke.");
                            scanner.nextLine();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You stand there awkwardly, but the person ignores you.");
                            System.out.println("Nothing happens.");

                            scanner.nextLine();
                            World.getRoom("Kröpke").reenter();
                        }

                )
        );

        rooms.put("Intro", startingRoom);
        rooms.put("Kröpke", townGate);
        rooms.put("outsideHBF", outsideHBF);
    }

    public static Room getRoom(String name) {
        return rooms.get(name);
    }
}
