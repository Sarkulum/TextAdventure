package text;

public enum TextColor {
    // For static color uses (like damage) use TextColor.RED.getAnsiCode()
    // For player use player.getTextColor().getAnsiCode()
    RESET("Reset", "\u001B[0m"),
    RED("Red", "\u001B[31m"),
    GREEN("Green", "\u001B[32m"),
    YELLOW("Yellow", "\u001B[33m"),
    BLUE("Blue", "\u001B[34m"),
    PURPLE("Purple", "\u001B[35m"),
    CYAN("Cyan", "\u001B[36m"),
    GRAY("Gray", "\u001B[37m"),
    MAGENTA("Magenta", "\u001B[35m"),

    HIGH_RED("High Red", "\u001B[0;91m"),
    HIGH_GREEN("High Green", "\u001B[0;92m"),
    HIGH_YELLOW("High Yellow", "\u001B[0;93m"),
    HIGH_BLUE("High Blue", "\u001B[0;94m"),
    HIGH_PURPLE("High Purple", "\u001B[0;95m"),
    HIGH_CYAN("High Cyan", "\u001B[0;96m");

    private final String name;
    private final String ansiCode;

    TextColor(String name, String ansiCode) {
        this.name = name;
        this.ansiCode = ansiCode;
    }

    public String getName() {return name;}

    public String getAnsiCode() {return ansiCode;}

    @Override
    public String toString() {
        return ansiCode + name + RESET.getAnsiCode();
    }

    public static TextColor fromName(String input) {
        for (TextColor color : values()) {
            if (color.name.equalsIgnoreCase(input)) {
                return color;
            }
        }
        return RESET;
    }
}
