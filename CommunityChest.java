import java.util.Random;

public class CommunityChest extends Property {
    private String[] communityChestOptions = {
        "Bank error in your favor. Collect $200.",
        "Doctor's fees. Pay $50.",
        "You inherit $100.",
        "Pay school fees of $150.",
        "Receive $25 consultancy fee."
    };

    private int[] communityChestEffects = {
        200, 
        -50, 
        100,
        -150,
        25  
    };

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
