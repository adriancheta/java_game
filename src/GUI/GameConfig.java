package GUI;

public class GameConfig {

    public GameMode mode;
    public String player1Name;
    public String player2Name;

    public GameConfig(GameMode mode, String p1, String p2) {

        this.mode = mode;
        this.player1Name = p1;
        this.player2Name = p2;
    }
}