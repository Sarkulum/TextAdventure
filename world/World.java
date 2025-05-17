package world;

import items.Item;
import items.Weapon;
import logic.Game;
import logic.GameEvent;
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
                            Scanner scanner = new Scanner(System.in);
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
                            System.out.println("---------------------------> press Enter to continue\n");
                            scanner.nextLine();

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

                            if (player.playerHasItem("Cigarettes")) {
                                System.out.println("Person:");
                                System.out.println("'Thanks a lot, mate. Here, now you can go further.'\n");
                                System.out.println("The person inhales the smoke as if it is nothing, and the smoky wall dissipates.");
                                System.out.println("---------------------------> press Enter to continue\n");
                                scanner.nextLine();
                            } else {
                                System.out.println("Person:");
                                System.out.println("'Welcome, nice to see another survivor.'");
                                System.out.println("If you bring me a pack of cigarettes, "+player.getName()+", I'll let you through that smoky wall.");
                                System.out.println("---------------------------> press Enter to continue\n");
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
                            System.out.println("---------------------------> press Enter to continue\n");
                            scanner.nextLine();
                            World.getRoom("Kröpke").reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You decide to leave the smoky wall and the strange person behind, heading toward Kröpke.");
                            System.out.println("---------------------------> press Enter to continue\n");
                            scanner.nextLine();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You stand there awkwardly, but the person ignores you.");
                            System.out.println("Nothing happens.");
                            System.out.println("---------------------------> press Enter to continue\n");
                            scanner.nextLine();
                            World.getRoom("Kröpke").reenter();
                        }

                )
        );
        Room crossRoadRoom = new Room(
                "Kröpke Crossroads",
                List.of(
                        "You stand in the heart of the city, but it feels nothing like it used to.",
                        "Once a bustling square filled with life, Kröpke is now eerily silent.",
                        "Four paths lie before you:"
                ),
                List.of(
                        "Go north (To the abandoned pharmacy.)",
                        "Go east (To the old fast-food stand.)",
                        "Go south (To the smoky wall.)",
                        "Go west (To the abandoned kiosk.)"
                ),
                List.of(
                        () -> Game.moveToRoom(World.getRoom("pharmacy")),
                        () -> Game.moveToRoom(World.getRoom("forest")),     // Replace "forest" with your actual room key if needed
                        () -> Game.moveToRoom(World.getRoom("townGate")),
                        () -> Game.moveToRoom(World.getRoom("goblinCave"))  // Same here, use the correct key
                )
        );
        Room pharmacyRoom = new Room(
                "Pharmacy",
                List.of(
                        "You step into what used to be a pharmacy.",
                        "The shelves are mostly empty, some toppled over, and shattered pill bottles crunch under your feet.",
                        "A faint smell of disinfectant lingers in the air.",
                        "The place has been ransacked, but maybe there's still something useful left."
                ),
                List.of(
                        "Look around the pharmacy.",
                        "Leave the pharmacy.",
                        "Check behind the counter."
                ),
                List.of(
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You take a moment to examine the ruined pharmacy.");
                            System.out.println("Broken shelves, dried bloodstains on the floor, and a faint buzzing sound from a flickering light overhead.");
                            System.out.println("You wonder who came here before you and if they made it out alive.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom("Pharmacy").reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You step outside, ready to move on.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            Game.moveToRoom(World.getRoom("Kröpke Crossroads")); // Replace with actual next room name
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            Player player = playerManager.getCurrentPlayer();

                            if (player.hasDone(GameEvent.TOOK_MEDKIT)) {
                                System.out.println("You spot an old first-aid kit behind the counter.");
                                System.out.println("Some of the items inside are still usable.");
                                System.out.println("You patch yourself up as best as you can.");
                                System.out.println("Your " + TextColor.GREEN.getAnsiCode() + "HP" + player.getUserTextColor().getAnsiCode() + " have recovered.");
                                player.setCurrentHP(player.getMaxHP());

                                player.markDone(GameEvent.TOOK_MEDKIT);
                                System.out.println("Your " + TextColor.GREEN.getAnsiCode() + "HP: " + player.getCurrentHP() + player.getUserTextColor().getAnsiCode());
                            } else {
                                System.out.println("You glance behind the counter, but the first-aid kit is empty.");
                                System.out.println("No more supplies left.");
                            }

                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom("Pharmacy").reenter();
                        }
                )
        );
        Room fastFoodStandRoom = new Room(
                "Fast-Food Stand",
                List.of(
                        "You enter what used to be a small fast-food stand.",
                        "The air is stale, and the floor is sticky with old grease.",
                        "Chairs are knocked over, ketchup stains cover the counter, and a rotten burger sits half-eaten on a tray.",
                        "The smell of decay lingers."
                ),
                List.of(
                        "Look around the stand.",
                        "Eat the rotten burger.",
                        "Leave the fast-food stand and return to Kröpke."
                ),
                List.of(
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            Player player = playerManager.getCurrentPlayer();
                            if (player.playerHasItem("Knife")) {
                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("You step behind the counter, searching for anything useful.");
                                System.out.println("As you rummage through a drawer, your fingers touch something cold and metallic ...");
                                System.out.println("a kitchen knife!!!");
                                System.out.println("It's not in the best condition, but it's better than nothing.");
                                System.out.println("--------------------------->press enter to continue\n");

                                scanner.nextLine();

                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("System:");
                                System.out.println("You replaced your weapon 'Fist' with 'Knife'(" + TextColor.RED.getAnsiCode() + "+1 min damage" + player.getUserTextColor().getAnsiCode() + " & " + TextColor.RED.getAnsiCode() + "+5 max damage" + player.getUserTextColor().getAnsiCode() + ")");
                                System.out.println("--------------------------->press enter to continue\n");

                                Weapon knife = new Weapon("Knife", 1, 5, 1, 0);
                                player.addItem(knife);
                                player.equipWeapon("Knife");
                                scanner.nextLine();
                            } else {
                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("You already searched here. Nothing else useful remains.");
                                System.out.println("--------------------------->press enter to continue\n");
                                scanner.nextLine();
                            }
                            World.getRoom("Fast-Food Stand").reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            Player player = playerManager.getCurrentPlayer();
                            if (!player.hasDone(GameEvent.BURGER_EATEN) && player.getCurrentHP() > 1) {
                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("You pick up the rotten burger, your stomach turning as you take a bite.");
                                System.out.println("It tastes awful, and something feels wrong.");
                                System.out.println("You feel your stomach churn, and your head spins.\n");
                                System.out.println("System:");
                                System.out.println("You " + TextColor.RED.getAnsiCode() + "lose 1 HP" + player.getUserTextColor().getAnsiCode() + ".");
                                System.out.println("--------------------------->press enter to continue\n");

                                player.setCurrentHP(player.getCurrentHP() - 1);
                                player.markDone(GameEvent.BURGER_EATEN);
                                scanner.nextLine();
                                World.getRoom("Fast-Food Stand").reenter();
                            } else if (player.hasDone(GameEvent.BURGER_EATEN)) {
                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("Except for the burger you have foolishly eaten there is nothing else here.");
                                System.out.println("--------------------------->press enter to continue\n");
                                scanner.nextLine();
                                World.getRoom("Fast-Food Stand").reenter();
                            } else {
                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("You feel your stomach churn, and your head spins as you fall over and your vision turns black.");
                                System.out.println("--------------------------->press enter to continue\n");

                                scanner.nextLine();
                                player.setCurrentHP(player.getMaxHP());
                                // You may want to send them somewhere (e.g., back to a hub or shop)
                            }
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("You turn away from the abandoned stand and head back to Kröpke.");
                            System.out.println("--------------------------->press enter to continue\n");

                            scanner.nextLine();
                            Game.moveToRoom(World.getRoom("Kröpke Crossroads"));
                        }
                )
        );
        Room deathRoom = new Room(
                "You Died",
                List.of(
                        "You feel your limbs grow cold...",
                        "Your vision fades to black as the world slips away.",
                        "Whatever you were trying to do, it's over now."
                ),
                List.of(
                        "Exit the game.",
                        "Restart from Last checkpoint"
                ),
                List.of(
                        () -> {
                            System.out.println("\nGame Over.");
                            System.exit(0); // Terminates the program
                        },
                        () -> {
                            Player player = playerManager.getCurrentPlayer();
                            if (player.hasDone(GameEvent.TUTORIAL_PASSED)) {
                                World.getRoom("Shop").enter();
                            } else if (!player.hasDone(GameEvent.TUTORIAL_PASSED)) {
                                World.getRoom("Kröpke").enter();
                            }
                        }
                )
        );




        rooms.put("You Died", deathRoom);
        rooms.put("Intro", startingRoom);
        rooms.put("Kröpke", townGate);
        rooms.put("Kröpke Crossroads", crossRoadRoom);
        rooms.put("Fast-Food Stand", fastFoodStandRoom);
        rooms.put("Pharmacy", pharmacyRoom);
        rooms.put("outsideHBF", outsideHBF);
    }

    public static Room getRoom(String name) {
        return rooms.get(name);
    }
}
