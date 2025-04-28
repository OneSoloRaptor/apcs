import java.util.*;

public class BotPlayer {
    private String piece;

    public BotPlayer(String piece) {
        this.piece = piece;
    }

    public void takeTurn(Property[] board) {
        Random random = new Random();
        int roll = (int)(Math.random()*12 + 1);
        System.out.println(piece + " rolls a " + roll);
    }
}
