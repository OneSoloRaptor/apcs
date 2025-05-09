public class ColoredProperty extends Property {
    private String color;
    private int rent;

    public ColoredProperty(String name, int price, String color, int rent) {
        super(name, price);
        this.color = color;
        this.rent = rent;
        buyable = true;
    }

    @Override
    public int getRent(int diceRoll) {
        return rent;
    }

    public String getColor() {
        return color;
    }
}
