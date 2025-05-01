public abstract class Property {
    protected String name;
    protected int price;
    protected Person owner;
    protected boolean buyable;
    protected int rent;

    public Property(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Property(String name, int price, int rent) {
        this.name = name;
        this.price = price;
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Person getOwner() {
        return owner;
    }

    public boolean isBuyable() {
        return buyable;
    }

    public int getRent(int diceRoll) {
        return rent;
    };

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}