public class Utility extends Property {
    public Utility(String name, int price) {
        super(name, price);
    }

    @Override
    public int getRent() {
        return 30; // Rent can be adjusted based on dice roll
    }
}
