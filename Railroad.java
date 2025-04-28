public class Railroad extends Property {
    public Railroad(String name, int price) {
        super(name, price);
    }

    @Override
    public int getRent() {
        return 25; // Rent can be adjusted based on number of railroads owned
    }
}
