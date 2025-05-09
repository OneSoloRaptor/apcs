import java.util.Random;

public class CommunityChest extends Property {
    public static String[] communityChestOptions = {
        "Bank error. Pay $100.",
        "Doctor's fees. Pay $100.",
        "You lose $100.",
        "Pay school fees of $100.",
        "Cursed :(, pay $100 consultancy fee."
    };

    private int[] communityChestEffects = {100, 100, 100, 100, 100};

    private Random random = new Random();

    public CommunityChest() {
        super("Community Chest", 0);
        buyable = false;
    }

    @Override
    public int getRent(int diceRoll, Person player) {
        int index = random.nextInt(communityChestOptions.length);
        System.out.println(communityChestOptions[index]);
        return communityChestEffects[index];
    }
    
}
