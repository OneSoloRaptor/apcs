public class FreeParking extends Property{
        public FreeParking(String name, int price, int rent, boolean buyable) {
            super(name, price, rent);
            this.buyable = false;
        }
        
        @Override
        public int getRent(int diceRoll) {
            return 0; 
        }
    }
        
    




