import java.util.Random;

public class Chance extends Property {
    private static final String[] chanceOptions = {
        "Advance to Go. Collect $200.",
        "Pay poor tax of $15.",
        "Your building loan matures. Collect $150.",
        "Go back 3 spaces.",
        "Pay speeding fine of $50."
    };

    private static final int[] chanceEffects = {
        200,  // Advance to Go
        -15,  // Pay poor tax
        150,  // Building loan matures
        -3,   // Go back 3 spaces (special case, not monetary)
        -50   // Pay speeding fine
    };

    private Random random = new Random();
    public Chance() {
        super("Chance", 0);
        buyable = false;
    }

    public int getRent(int diceRoll, Person player) {
        int index = random.nextInt(chanceOptions.length);
        System.out.println(chanceOptions[index]);

        
        if (index == 0) {
            player.setPosition(0);
        }
        else if (index == 3) {
            player.move(-3, 40);
            return 0;
        }

        return chanceEffects[index];
    }
}
