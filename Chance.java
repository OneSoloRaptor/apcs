import java.util.Random;

public class Chance extends Property {
    public static String[] chanceOptions = {
        "Advance in life. Collect $100.",
        "You are poor so someone donates $100.",
        "Your building loan matures. Collect $100.",
        "Lucky duck. Gain $100"
    };
    public String chanceEffects(String[] chanceOptions) {
        return chanceOptions[(int) (Math.random() * chanceOptions.length)];
    }
    private int chanceEffects = 100;

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

        return chanceEffects;
    }
}
