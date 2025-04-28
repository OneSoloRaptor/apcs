public class ColoredProperty extends Property {
    private String color;
    private int rent;

    public ColoredProperty(String name, int price, String color, int rent) {
        super(name, price);
        this.color = color;
        this.rent = rent;
    }

    @Override
    public int getRent() {
        return rent;
    }

    public String getColor() {
        return color;
    }
}
