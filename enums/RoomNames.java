package enums;

import player.PlayerManager;

public enum RoomNames {
    YOU_DIED("You Died"),
    INTRODUCTION(PlayerManager.getInstance().getCurrentPlayer().getUserTextColor().getAnsiCode() + "Introduction"),
    KROEPKE("Kröpke"),
    KROEPKE_CROSSROAD("Kröepke Crossroad"),
    FAST_FOOD_STAND("Fast-Food Stand"),
    PHARMACY("Pharmacy"),
    OUTSIDE_HBF("Outside HBF"),
    EMPTY_PLATFORM("Empty Platform"),
    EAG_GROUND("Ernst-August-Galerie Ground Floor"),
    SHOP("Shop"),
    PUZZLE_1("Puzzle 1"),
    PUZZLE_2("Puzzle 2"),
    PUZZLE_3("Puzzle 3"),
    SUBWAY_ENTRANCE("Subway Entrance"),
    SUBWAY_TUNNEL("Subway Tunnel"),
    KIOSK("Abonded Kiosk"),
    EAG_STATUE("Ernst-August-Galerie Statue"),
    KIOSK_FIGHT("Abonded Kiosk Fight");

    private final String name;

    RoomNames(String name){
        this.name = name;
    }

    public String getName() {return name;}
}
