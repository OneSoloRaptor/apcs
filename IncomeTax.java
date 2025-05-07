public class IncomeTax extends Property {
        public IncomeTax(String name, int price, int rent, boolean buyable) {
            super(name, price, rent);
            this.buyable = false;
        }
        
        @Override
        public int getRent(int diceRoll) {
            return 0; 
        }
    }
        
    


