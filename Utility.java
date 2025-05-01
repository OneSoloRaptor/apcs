public class Utility extends Property {
    public Utility(String name, int price) {
        super(name, price);
    }

    @Override
    public int getRent(int diceRoll) {
        int amountOwned = 0;
        for (Property property : owner.getProperties()) {
            if (property instanceof Utility) {
                amountOwned++;
            }
        }
        if (amountOwned == 1) {
            return 4 * diceRoll; 
        } else if (amountOwned == 2) {
            return 10 * diceRoll; 
        }
        return 0; 
    }
}
