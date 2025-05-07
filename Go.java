public class Go extends Property {
        public Go(String name, int price, int rent, boolean buyable) {
            super(name, price, rent);
            this.buyable = false;
        }
        
        @Override
        public int getRent(int diceRoll) {
            return 0; 
        }
    }
        
    

