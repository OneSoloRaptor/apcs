public class Railroad extends Property {
    public Railroad(String name, int price) {
        super(name, price);
        buyable = true;
        rent = 25; 
    }
    @Override
    public int getRent(int diceRoll) {
        int amountOwned = 0;
        for (Property property : owner.getProperties()) {
            if (property instanceof Railroad) {
                amountOwned++;
            }
        }
        if (amountOwned == 1) {
            return 25; 
        } else if (amountOwned == 2) {
            return 50; 
        } else if (amountOwned == 3) {
            return 100; 
        } else if (amountOwned == 4) {
            return 200; 
        }
        return 0;
    }

}
