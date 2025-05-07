public class GoToJail extends Property{
    public GoToJail(String name, int price, int rent, boolean buyable){
        super(name, price, rent);
        this.buyable = false;
    }
    
    @Override
    public int getRent(int diceRoll) {
        return 0; // No rent for Go to Jail
    }
}
    
