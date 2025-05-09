import java.util.*;

public class Person {
    protected String piece;
    protected int money = 1500;
    protected ArrayList<Property> properties = new ArrayList<>();
    protected int position = 0;

    public Person(String piece) {
        this.piece = piece;
    }

    public void buyProperty(Property property) {
        if (money >= property.getPrice()) {
            money -= property.getPrice();
            properties.add(property);
            property.setOwner(this);
            System.out.println("You bought " + property.getName() + " for $" + property.getPrice());
        } else {
            System.out.println("You don’t have enough money to buy this property.");
        }
    }

    public void move(int roll, int boardLength) {
        position = (position + roll) % boardLength;
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }

    public void payRent(Person owner, int amount) {
        if (money >= amount) {
            money -= amount;
            owner.receivePayment(amount);
            System.out.println("You paid $" + amount + " in rent to " + owner.getPiece());
        } else {
            System.out.println("You don’t have enough money to pay rent.");
        }
    }

    public void receivePayment(int amount) {
        money += amount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        if (money < 0) {
            money = 0;
            if (piece == "Hat" || piece == "Thimble" || piece == "Iron" || piece == "Boot") {
                System.out.println("You are bankrupt!");
            } else {
                System.out.println(piece + " is bankrupt!");
            }
            System.out.println("Game Over.");
            System.exit(0);
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public String getPiece() {
        return piece;
    }

    public int getPositionIndex() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

