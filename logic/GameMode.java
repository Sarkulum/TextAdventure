package logic;

public enum GameMode {
    STORY("Story Mode"),
    ENDLESS("Endless Mode"),
    LOCAL_MULTIPLAYER("Local Multiplayer"),
    ONLINE_MULTIPLAYER("Online Multiplayer");

    private final String displayName;

    GameMode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}