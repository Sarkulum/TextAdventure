package world;

import enums.RoomNames;
import items.Weapon;
import logic.Game;
import logic.GameEvent;
import map.Position;
import player.Player;
import player.PlayerManager;
import text.TextColor;
import java.util.*;


// If you want to "reenter" a room, use World.getRoom("outsideHBF").enter();
public class World {
    // Creat a HashMap with every Room object in it.
    private static final Map<Enum, Room> rooms = new HashMap<Enum, Room>();
    private static final PlayerManager playerManager = PlayerManager.getInstance();

    public static void initializeWorld() {
        Room outsideHBF = new Room(
                RoomNames.OUTSIDE_HBF,
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

                            World.getRoom(RoomNames.OUTSIDE_HBF).reenter();
                        },
                        () -> {
                            Game.moveToRoom(World.getRoom(RoomNames.EAG_GROUND));
                        }
                )
        );

        Room startingRoom = new Room(
                 RoomNames.INTRODUCTION,
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
                            World.getRoom(RoomNames.KROEPKE).enter();
                        }
                )
        );

        Room townGate = new Room(
                RoomNames.KROEPKE,
                List.of(
                        "A thick, smoky wall blocks a narrow passage leading further into the city.",
                        "A lone figure sits in front of it, casually exhaling smoke into the already heavy air."
                ),
                List.of(
                    "Talk to the person",
                    "Smack them",
                    "Go north to the crossroad",
                    "Do nothing"
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
                                World.getRoom(RoomNames.KROEPKE).reenter();
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
                            World.getRoom(RoomNames.KROEPKE).reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You decide to leave the smoky wall and the strange person behind, heading toward Kröpke.");
                            System.out.println("---------------------------> press Enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.KROEPKE_CROSSROAD).enter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You stand there awkwardly, but the person ignores you.");
                            System.out.println("Nothing happens.");
                            System.out.println("---------------------------> press Enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.KROEPKE).reenter();
                        }

                )
        );
        Room kiosk = new Room(
                RoomNames.KIOSK,
                List.of(
                        "You step into a ransacked kiosk. Shelves are toppled, shattered glass crunches underfoot, and the air reeks of stale beer and decay.",
                        "Behind the counter, a hunched figure twitches.",
                        "Once a shopkeeper, now a zombie.",
                        "Its head jerks toward you, and with a guttural growl it lunges!"
                ),
                List.of("Decide what to do."),
                List.of(() -> {
                    Player player = playerManager.getCurrentPlayer();
                    Scanner scanner = new Scanner(System.in);
                    if (player.hasDone(GameEvent.KIOSK_ZOMBIE_DEFEATED)) {
                        System.out.println("1. Look around the kiosk.");
                        System.out.println("2. Leave the kiosk and return to Kröpke.");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            System.out.println("Now that the zombie is no longer a threat, you take a moment to search the kiosk.");
                            System.out.println("But you can't find anything of interest.");
                            World.getRoom(RoomNames.KIOSK).reenter();
                        } else {
                            System.out.println("You step over the body and make your way back to Kröpke, the pack of cigarettes tucked safely in your pocket.");
                            Game.moveToRoom(World.getRoom(RoomNames.KROEPKE_CROSSROAD));
                        }
                    } else {
                        System.out.println("1. Fight the kiosk zombie.");
                        System.out.println("2. Run away");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            Game.moveToRoom(World.getRoom(RoomNames.KIOSK_FIGHT));
                        } else {
                            System.out.println("Panic takes over, and you sprint back to Kröpke.");
                            System.out.println("The zombie snarls but doesn't chase you.");
                            System.out.println("The kiosk remains dangerous.");
                            Game.moveToRoom(World.getRoom(RoomNames.KROEPKE_CROSSROAD));
                        }
                    }
                })
        );

        Room kioskFight = new CombatRoom(
                RoomNames.KIOSK_FIGHT,
                List.of("The zombie gurgles and lunges!"),
                5, 5,
                List.of("Shambler"),
                List.of(new Position(4, 1), new Position(3, 1), new Position(2, 1)),
                GameEvent.KIOSK_ZOMBIE_DEFEATED,
                "The zombie gurgles one last time before collapsing:\n'H-heute ... nur Malboro im Angebot ... '\nAs it twitches on the floor, something falls from its pocket ...\na pack of cigarettes!!!",
                World.getRoom(RoomNames.KIOSK)
        );

        Room crossRoadRoom = new Room(
                RoomNames.KROEPKE_CROSSROAD,
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
                        () -> Game.moveToRoom(World.getRoom(RoomNames.PHARMACY)),
                        () -> Game.moveToRoom(World.getRoom(RoomNames.FAST_FOOD_STAND)),
                        () -> Game.moveToRoom(World.getRoom(RoomNames.KROEPKE)),
                        () -> Game.moveToRoom(World.getRoom(RoomNames.KIOSK))
                )
        );
        Room pharmacyRoom = new Room(
                RoomNames.PHARMACY,
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
                            World.getRoom(RoomNames.PHARMACY).reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You step outside, ready to move on.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            Game.moveToRoom(World.getRoom(RoomNames.KROEPKE_CROSSROAD)); // Replace with actual next room name
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
                            World.getRoom(RoomNames.PHARMACY).reenter();
                        }
                )
        );
        Room fastFoodStandRoom = new Room(
                RoomNames.FAST_FOOD_STAND,
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
                            World.getRoom(RoomNames.FAST_FOOD_STAND).reenter();
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
                                World.getRoom(RoomNames.FAST_FOOD_STAND).reenter();
                            } else if (player.hasDone(GameEvent.BURGER_EATEN)) {
                                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("Except for the burger you have foolishly eaten there is nothing else here.");
                                System.out.println("--------------------------->press enter to continue\n");
                                scanner.nextLine();
                                World.getRoom(RoomNames.FAST_FOOD_STAND).reenter();
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
                            Game.moveToRoom(World.getRoom(RoomNames.KROEPKE_CROSSROAD));
                        }
                )
        );
        Room deathRoom = new Room(
                RoomNames.YOU_DIED,
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
                                World.getRoom(RoomNames.SHOP).enter();
                            } else if (!player.hasDone(GameEvent.TUTORIAL_PASSED)) {
                                World.getRoom(RoomNames.KROEPKE).enter();
                            }
                        }
                )
        );
        Room end = new Room(
                RoomNames.EMPTY_PLATFORM,
                List.of(
                        "After solving the riddle you step forward into what looks like an abandoned platform, a forgotten stop on an underground rail line.",
                        "The air is thick with dust, and the faint sound of dripping water echoes around you.",
                        "Old posters and signs in faded German lettering cling to the walls, their colors long since eroded by time.",
                        "Above, a single flickering sign reads: 'Zug Ankunft 2 Minuten' (Train Arrival 2 Minutes).",
                        "As you look around, you notice that the tracks ahead vanish into a pitch-black tunnel.",
                        "The faint rumble of something massive begins to vibrate through the ground.",
                        "A distant light appears, growing brighter with every second.",
                        "The silence is broken by the unmistakable sound of screeching metal and the low hum of a train approaching.",
                        "But ... how? The station looked abandoned, the world in ruins.",
                        "What train could still be running? And who ... or what is on board?",
                        "The train bursts from the darkness, its headlights cutting through the gloom.",
                        "It screeches to a halt in front of you, its exterior battered and rusted but still intact.",
                        "The lights inside flicker eerily, casting strange shadows across the platform.",
                        "The train's doors slide open with a loud hiss, inviting you aboard.",
                        "A cold wind rushes out from inside, carrying with it the faint scent of oil and ... something else.",
                        "A mechanical voice crackles over the intercom:",
                        "'Endstation. Bitte nicht einsteigen. (Final stop. Please don't board.)'",
                        "You realize this is the end of the line, literally and figuratively.",
                        "The train seems to be your only way out of this nightmare, or perhaps the beginning of a new one."
                ),
                List.of(
                        "Board the train.",
                        "Stay behind."
                ),
                List.of(
                        () -> {
                            System.out.println("You take a deep breath and step onto the train.");
                            System.out.println("The doors close behind you with a deafening clang.");
                            System.out.println("The train jolts forward, plunging into the darkness of the tunnel.");
                            System.out.println("You grip a nearby pole for balance as the flickering lights illuminate the empty seats around you.");
                            System.out.println("\nThe intercom crackles again, the voice speaking one last cryptic phrase:");
                            System.out.println("\n'Zurücksetzen der Welt. Reise abgeschlossen. (Resetting the world. Journey complete.)'");
                            System.out.println("\nThe train picks up speed, and the world outside the windows becomes a blur of light and shadow.");
                            System.out.println("--------------------------->press enter to continue");

                            new Scanner(System.in).nextLine();
                            World.getRoom(RoomNames.INTRODUCTION).enter();
                        },
                        () -> {
                            System.out.println("You step back from the train, shaking your head.");
                            System.out.println("Whatever is on that train, it doesn't feel safe.");
                            System.out.println("The doors close with a loud hiss, and the train pulls away into the tunnel.");
                            System.out.println("Silence falls over the platform.");
                            System.out.println("Then you hear it, a low growl and shuffling footsteps.");
                            new Scanner(System.in).nextLine();

                            System.out.println("You whip around. Dozens of figures emerge from the shadows, eyes gleaming.");
                            System.out.println("The zombies have found you.");
                            new Scanner(System.in).nextLine();

                            System.out.println("You grab your " + PlayerManager.getInstance().getCurrentPlayer().getEquippedWeapon().getName() + ", but it’s no use.");
                            System.out.println("They overwhelm you. Screams echo as darkness closes in.");
                            new Scanner(System.in).nextLine();

                            System.out.println("\n                          You Died !!!                            ");
                            System.out.println("                            The End                               \n");
                            System.out.println("                          Presented by:                           ");
                            System.out.println("                            Valerie                               ");
                            System.out.println("                              Anna                                ");
                            System.out.println("                             Ashley                               ");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
                        }
                )
        );
        Room eagGround = new Room(
                RoomNames.EAG_GROUND,
                List.of(
                        "The ground floor of Ernst-August-Galerie is a desolate ruin.",
                        "Broken storefronts, shattered glass, and the faint stench of decay fill the air.",
                        "Emergency lights flicker above, casting unsettling shadows across the debris-covered floor.",
                        "Among the chaos, you notice a strange trail,",
                        "a faint smear of red leading from the Food Court toward what looks like a service door at the far end of the mall."
                ),
                List.of(
                        "Investigate the service door",
                        "Search the fashion section",
                        "Explore the Food Court",
                        "Return to the plaza outside Hauptbahnhof"
                ),
                List.of(
                        // Investigate the service door
                        () -> {
                            Player player = PlayerManager.getInstance().getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You follow the trail to the service door.");
                            System.out.println("The door is locked.");

                            if (player.playerHasItem("Crowbar") && !player.hasDone(GameEvent.TOOK_MEDKIT_EAG)) {
                                System.out.println("But you are able to open it using the crowbar you picked up.");
                                System.out.println("Inside you find a supply room with a MedKit.");
                                System.out.println("System:");
                                System.out.println("You " + TextColor.GREEN.getAnsiCode() + "heal to full health" + PlayerManager.getInstance().getCurrentPlayer().getUserTextColor().getAnsiCode() + ".");
                                player.setCurrentHP(player.getMaxHP());
                                player.markDone(GameEvent.TOOK_MEDKIT_EAG);
                            }

                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.EAG_GROUND).reenter();
                        },

                        // Search the fashion section
                        () -> {
                            Player player = PlayerManager.getInstance().getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("The Fashion section is a mess, with clothes scattered everywhere.");

                            if (player.hasDone(GameEvent.TOOK_BOOTS)) {
                                System.out.println("Among the wreckage, you find a pair of sturdy boots.");
                                System.out.println("They're a bit too big, but they'll do.");
                                System.out.println("System:");
                                System.out.println("You can now take more damage (" + TextColor.GREEN.getAnsiCode() + "+1 max hp" + player.getUserTextColor().getAnsiCode() + ").");

                                // The if statement is so that you're current hp stay at  full if you where full life before picking up the boots.
                                if (player.getCurrentHP() == player.getMaxHP()) {
                                    player.setCurrentHP(player.getCurrentHP() + 1);
                                }
                                player.setMaxHP(player.getMaxHP() + 1);
                                player.markDone(GameEvent.TOOK_BOOTS);
                            }

                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.EAG_GROUND).reenter();
                        },

                        // Explore the Food Court
                        () -> {
                            Player player = PlayerManager.getInstance().getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("The Food Court is eerily silent.");

                            if (player.hasDone(GameEvent.EAT_FOOD)) {
                                System.out.println("Most of the food has spoiled, but behind an overturned kiosk, you find an unopened bottle of soda.");
                                System.out.println("System:");
                                System.out.println("You " + TextColor.GREEN.getAnsiCode() + "heal for +1hp" + player.getUserTextColor().getAnsiCode() + ".");

                                if (player.getCurrentHP() < player.getMaxHP()) {
                                    player.setCurrentHP(player.getCurrentHP() + 1);
                                }

                                player.markDone(GameEvent.EAT_FOOD);
                            }

                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.EAG_GROUND).reenter();
                        },

                        // Return to the plaza
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You decide you've seen enough of the mall for now.");
                            System.out.println("You head back out into the cold night air.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.OUTSIDE_HBF).enter();
                        }
                )
        );
        Room eaStatue = new Room(
                RoomNames.EAG_STATUE,
                List.of(
                        "You return to the statue of Ernst-August.",
                        "The shadows seem darker now, and the once-silent plaza feels... alive, as if something is watching you.",
                        "You notice a faint glimmer in the base of the statue, something you didn't see before.",
                        "As you get closer, you realize there's a small hidden compartment built into the pedestal.",
                        "A carved inscription reads: 'Seek and you shall find.'"
                ),
                !PlayerManager.getInstance().getCurrentPlayer().hasDone(GameEvent.TOOK_KEY)
                        ? List.of(
                        "Inspect the statue again",
                        "Enter the HBF"
                )
                        : List.of(
                        "Inspect the statue again",
                        "Open the hidden compartment",
                        "Enter the HBF"
                ),
                !PlayerManager.getInstance().getCurrentPlayer().hasDone(GameEvent.TOOK_KEY)
                        ? List.of(
                        // 1: Inspect the statue again
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You carefully examine the statue once more.");
                            System.out.println("Aside from the strange writing and the hidden compartment, nothing else seems unusual.");
                            System.out.println("Whatever ‘Forgotten Treasures' means, it's not here.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.EAG_STATUE).reenter();
                        },

                        // 2: Enter HBF
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You enter the HBF and think:");
                            System.out.println("'Maybe you can escape using the U-Bahn...'");
                            System.out.println("Before you can finish your thought you see a Person behind a makeshift counter and go towards them.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.SHOP).enter();
                        }
                )
                        : List.of(
                        // 1: Inspect the statue again
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You carefully examine the statue once more.");
                            System.out.println("Aside from the strange writing and the hidden compartment, nothing else seems unusual.");
                            System.out.println("Whatever ‘Forgotten Treasures' means, it's not here.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.EAG_STATUE).reenter();
                        },

                        // 2: Open hidden compartment
                        () -> {
                            Player player = PlayerManager.getInstance().getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Inside the compartment, you find " + TextColor.HIGH_RED.getAnsiCode() + "a small key" + player.getUserTextColor().getAnsiCode() + " with a tag attached.");
                            System.out.println("The tag reads: 'Forgotten Treasures.'");
                            System.out.println("You feel a chill run down your spine as you realize this must be the key to something nearby.");
                            System.out.println("--------------------------->press enter to continue\n");
                            player.markDone(GameEvent.TOOK_KEY);
                            scanner.nextLine();
                            World.getRoom(RoomNames.EAG_STATUE).enter(); // Re-enter to refresh options
                        },

                        // 3: Enter HBF
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You enter the HBF and think:");
                            System.out.println("'Maybe you can escape using the U-Bahn...'");
                            System.out.println("Before you can finish your thought you see a Person behind a makeshift counter and go towards them.");
                            System.out.println("--------------------------->press enter to continue\n");
                            scanner.nextLine();
                            World.getRoom(RoomNames.SHOP).enter();
                        }
                )
        );
        Room shop = new Room(
                RoomNames.SHOP,
                List.of(
                        "The shop is dimly lit, with flickering neon signs casting eerie glows on dusty shelves.",
                        "A strange shopkeeper eyes you from behind a barricaded counter.",
                        !PlayerManager.getInstance().getCurrentPlayer().hasDone(GameEvent.FIRST_SHOP_VISIT)
                                ? "You could talk to the stranger behind the counter ... though you're not sure you should."
                                : "The shopkeeper nods at you, seemingly expecting you."
                ),
                List.of(
                        "Talk to the shopkeeper",
                        "Leave the shop"
                ),
                List.of(
                        () -> {
                            Player player = PlayerManager.getInstance().getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);

                            if (!player.hasDone(GameEvent.FIRST_SHOP_VISIT)) {
                                System.out.println("\nShopkeeper: Hello " + player.getName() + ", welcome to my humble shop.");
                                System.out.println("Here you can buy permanent upgrades for your stats.");
                                player.markDone(GameEvent.FIRST_SHOP_VISIT);
                                System.out.println("---------------------------> press Enter to continue\n");
                                scanner.nextLine();
                            }

                            int hpDif = player.getMaxHP() - player.getCurrentHP();

                            List<String> shopOptions = new ArrayList<>();
                            if (player.getMinDamage() < player.getMaxDamage() - 1) {
                                shopOptions.add("Upgrade minimum damage (+1) [10 gold]");
                            }
                            shopOptions.add("Upgrade maximum damage (+1) [10 gold]");
                            shopOptions.add("Increase max HP (+1) [30 gold]");
                            shopOptions.add("Heal to full HP (" + hpDif + ") [15 gold]");
                            shopOptions.add("Buy gun (20 max damage) [50 gold]");
                            shopOptions.add("Leave shop");

                            while (true) {
                                System.out.println("\nSystem: Your current stats:");
                                System.out.println("Min Damage: " + player.getMinDamage());
                                System.out.println("Max Damage: " + player.getMaxDamage());
                                System.out.println("HP: " + player.getCurrentHP() + "/" + player.getMaxHP());
                                System.out.println("Gold: " + player.getGoldCoins());
                                System.out.println("\nWhat would you like to buy?");

                                for (int i = 0; i < shopOptions.size(); i++) {
                                    System.out.println((i + 1) + ": " + shopOptions.get(i));
                                }

                                int choice = scanner.nextInt();
                                if (choice < shopOptions.size()) {

                                    if (shopOptions.get(choice).equals("Leave shop")) {
                                        break;
                                    }

                                    switch (choice) {
                                        case 1:
                                            if (player.getMinDamage() < player.getMaxDamage() - 1) {
                                                if (player.getGoldCoins() >= 10) {
                                                    player.setMinDamage(player.getMinDamage() + 1);
                                                    player.setGoldCoins(player.getGoldCoins() - 10);
                                                } else {
                                                    System.out.println("Not enough gold.");
                                                }
                                                break;
                                            }
                                        case 2:
                                            if (player.getGoldCoins() >= 10) {
                                                player.setMaxDamage(player.getMaxDamage() + 1);
                                                player.setGoldCoins(player.getGoldCoins() - 10);
                                            } else {
                                                System.out.println("Not enough gold.");
                                            }
                                            break;
                                        case 3:
                                            if (player.getGoldCoins() >= 30) {
                                                player.setMaxHP(player.getMaxHP() + 1);
                                                player.setGoldCoins(player.getGoldCoins() - 30);
                                            } else {
                                                System.out.println("Not enough gold.");
                                            }
                                            break;
                                        case 4:
                                            if (player.getGoldCoins() >= 15) {
                                                player.setCurrentHP(player.getMaxHP());
                                                player.setGoldCoins(player.getGoldCoins() - 15);
                                            } else {
                                                System.out.println("Not enough gold.");
                                            }
                                            break;
                                        case 5:
                                            if (player.getGoldCoins() >= 50) {
                                                Weapon gun = new Weapon("Gun", 0, 5, 20, 5);
                                                player.addItem(gun);
                                                player.equipWeapon("Gun");
                                                player.setGoldCoins(player.getGoldCoins() - 50);
                                            } else {
                                                System.out.println("Not enough gold.");
                                            }
                                            break;
                                    }
                                }
                            }

                            System.out.println("\nShopkeeper: Safe travels.");

                            if (!player.hasDone(GameEvent.STARTED_RANDOM_ROOM)) {
                                player.markDone(GameEvent.STARTED_RANDOM_ROOM);
                                // TODO add first Random room
                            }else {
                                player.getLastroom().enter();
                            }
                        },
                        () -> {
                            if (!PlayerManager.getInstance().getCurrentPlayer().hasDone(GameEvent.STARTED_RANDOM_ROOM)) {
                                PlayerManager.getInstance().getCurrentPlayer().markDone(GameEvent.STARTED_RANDOM_ROOM);
                                // TODO add first Random room
                            }else {
                                PlayerManager.getInstance().getCurrentPlayer().getLastroom().enter();
                            }
                        }
                )
        );


        Room puzzle1 = new Room(
                RoomNames.PUZZLE_1,
                List.of(
                        "You've carefully navigated the dark, eerie subway tunnel and arrived at a large metal door blocking the way forward.",
                        "At its center is a mechanical panel with glowing letters. A robotic voice speaks from a nearby speaker:",
                        "'Answer my riddle, and the path will open. Fail, and remain trapped in the shadows.'",
                        "The screen on the panel lights up, displaying the following:",
                        "A ticket machine at Hannover Hauptbahnhof isn't printing tickets. Passengers are getting frustrated.",
                        "The screen displays an error: 'Printer Connection Lost.'"
                ),
                List.of(
                        "Check if the printer cables are securely connected.",
                        "Reboot the entire ticket machine."
                ),
                List.of(
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Good Call!");
                            System.out.println("You reconnect the loose printer cable and the machine starts working.");
                            System.out.println("Passengers are happy!");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            Game.moveToRoom(World.getRoom(RoomNames.PUZZLE_2));
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You restart the machine but the problem persists.");
                            System.out.println("Looks like the issue wasn't with the system itself.");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            World.getRoom(RoomNames.PUZZLE_1).reenter();
                        }
                )
        );

        Room puzzle2 = new Room(
                RoomNames.PUZZLE_2,
                List.of(
                        "The panel displays the next riddle:",
                        "A train is experiencing overcrowding and you need to determine whether it's safe to allow more passengers onboard.",
                        "The train has 8 cars, each with a maximum capacity of 100 passengers.",
                        "Currently, the train is carrying 650 passengers.",
                        "How many more passengers can safely board the train?"
                ),
                List.of(
                        "Enter your answer."
                ),
                List.of(
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.print("Your answer: ");
                            int answer = -1;
                            try {
                                answer = scanner.nextInt();
                                scanner.nextLine(); // Consume the rest of the line
                            } catch (Exception e) {
                                // Handles cases where the user doesn't enter a number
                                scanner.nextLine(); // Clear the invalid input
                            }

                            if (answer == 150) {
                                System.out.println("Good job! That was the right answer.");
                                System.out.println("--------------------------->press enter to continue");
                                scanner.nextLine();
                                Game.moveToRoom(World.getRoom(RoomNames.PUZZLE_3));
                            } else {
                                System.out.println("I am sorry but that answer is wrong please try again.");
                                System.out.println("--------------------------->press enter to continue");
                                scanner.nextLine();
                                World.getRoom(RoomNames.PUZZLE_2).reenter();
                            }
                        }
                )
        );

        Room puzzle3 = new Room(
                RoomNames.PUZZLE_3,
                List.of(
                        "The panel displays the final riddle:",
                        "The departure board at HBF is showing incorrect train times.",
                        "The train database seems out of sync with the display system."
                ),
                List.of(
                        "Synchronise the display system with the central database.",
                        "Restart the display system.",
                        "Manually update the train times on the display."
                ),
                List.of(
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Success!");
                            System.out.println("You synced the display system and the departure board now shows accurate train times");
                            System.out.println("The large metal door grinds open, revealing the path forward.");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            Game.moveToRoom(World.getRoom(RoomNames.EMPTY_PLATFORM));
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("The system reboots but the error remains.");
                            System.out.println("Passengers are still confused. Try again!!!");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            World.getRoom(RoomNames.PUZZLE_3).reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("This would work temporarily but it's not a sustainable solution.");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            World.getRoom(RoomNames.PUZZLE_3).reenter();
                        }
                )
        );

        Room subwayEntrance = new Room(
                RoomNames.SUBWAY_ENTRANCE,
                List.of(
                        "You find yourself standing in front of an old metal door.",
                        "On the door, you can make out the words: 'Forgotten Treasures.'"
                ),
                List.of(
                        "Try to open the door."
                ),
                List.of(
                        () -> {
                            Player player = playerManager.getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            if (player.hasDone(GameEvent.TOOK_KEY)) {
                                System.out.println("You use the key you found to unlock the door.");
                                System.out.println("--------------------------->press enter to continue");
                                scanner.nextLine();
                                Game.moveToRoom(World.getRoom(RoomNames.SUBWAY_TUNNEL));
                            } else {
                                System.out.println("You try to open the door but it does not move.");
                                System.out.println("Suddenly you hear a roaring behind you.");
                                System.out.println("As you turn around you see a dozen zombies walking towards you.");
                                System.out.println("You get ready to fight... but something falls on your head and your vision fades to black.");
                                System.out.println("--------------------------->press enter to continue");
                                scanner.nextLine();
                                Game.moveToRoom(World.getRoom(RoomNames.YOU_DIED));
                            }
                        }
                )
        );

        Room subwayTunnel = new Room(
                RoomNames.SUBWAY_TUNNEL,
                List.of(
                        "You step into what used to be a bustling subway tunnel.",
                        "The air here is colder and the faint sound of dripping water echoes all around you.",
                        "The tracks are covered in debris and the walls are adorned with strange symbols.",
                        "A broken vending machine flickers faintly in the corner.",
                        "Among the rubble, a faint, guttural growl catches your attention."
                ),
                List.of(
                        "Inspect the vending machine.",
                        "Investigate the markings on the wall.",
                        "Follow the growl.",
                        "Ignore the noise and move forward."
                ),
                List.of(
                        () -> {
                            Player player = playerManager.getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            if (!player.hasDone(GameEvent.TOOK_SODA)) {
                                System.out.println("The vending machine looks barely functional, but you find an unopened can of soda inside.");
                                System.out.println("System: You " + TextColor.GREEN.getAnsiCode() + "heal 1 HP" + player.getUserTextColor().getAnsiCode() + ".");
                                if(player.getCurrentHP() < player.getMaxHP()) {
                                    player.setCurrentHP(player.getCurrentHP() + 1);
                                }
                                player.markDone(GameEvent.TOOK_SODA);
                            } else {
                                System.out.println("The vending machine sparks and breaks completely. Nothing else can be retrieved.");
                            }
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            World.getRoom(RoomNames.SUBWAY_TUNNEL).reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("The symbols are strange, almost ritualistic. You feel uneasy looking at them for too long.");
                            System.out.println("One of the phrases reads: 'The fog devours, but the light guides.'");
                            System.out.println("Maybe this is a clue for what lies ahead.");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            World.getRoom(RoomNames.SUBWAY_TUNNEL).reenter();
                        },
                        () -> {
                            Player player = playerManager.getCurrentPlayer();
                            Scanner scanner = new Scanner(System.in);
                            if (!player.hasDone(GameEvent.SUBWAY_ZOMBIE_FOUGHT)) {
                                System.out.println("You toughen up and follow the sound.");
                                System.out.println("Suddenly, a zombie lunges at you from behind a pile of rubble!");
                                // Simplified combat sequence
                                System.out.println("You ready your weapon and after a brief, intense struggle, you defeat the creature.");
                                System.out.println("The zombie falls over and dies.");
                                player.markDone(GameEvent.SUBWAY_ZOMBIE_FOUGHT);
                            } else {
                                System.out.println("You see the now lifeless body of the zombie you defeated earlier.");
                            }
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            World.getRoom(RoomNames.SUBWAY_TUNNEL).reenter();
                        },
                        () -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("You decide not to risk whatever is making that sound and move on down the tunnel.");
                            System.out.println("--------------------------->press enter to continue");
                            scanner.nextLine();
                            Game.moveToRoom(World.getRoom(RoomNames.PUZZLE_1));
                        }
                )
        );

        rooms.put(RoomNames.YOU_DIED, deathRoom);
        rooms.put(RoomNames.INTRODUCTION, startingRoom);
        rooms.put(RoomNames.KROEPKE, townGate);
        rooms.put(RoomNames.KROEPKE_CROSSROAD, crossRoadRoom);
        rooms.put(RoomNames.FAST_FOOD_STAND, fastFoodStandRoom);
        rooms.put(RoomNames.PHARMACY, pharmacyRoom);
        rooms.put(RoomNames.OUTSIDE_HBF, outsideHBF);
        rooms.put(RoomNames.EMPTY_PLATFORM, end);
        rooms.put(RoomNames.EAG_GROUND, eagGround);
        rooms.put(RoomNames.SHOP, shop);
        rooms.put(RoomNames.PUZZLE_1, puzzle1);
        rooms.put(RoomNames.PUZZLE_2, puzzle2);
        rooms.put(RoomNames.PUZZLE_3, puzzle3);
        rooms.put(RoomNames.SUBWAY_ENTRANCE, subwayEntrance);
        rooms.put(RoomNames.SUBWAY_TUNNEL, subwayTunnel);
        rooms.put(RoomNames.KIOSK, kiosk);
        rooms.put(RoomNames.KIOSK_FIGHT, kioskFight);
        rooms.put(RoomNames.EAG_STATUE, eaStatue);
    }

    public static Room getRoom(Enum name) {
        return rooms.get(name);
    }
}
