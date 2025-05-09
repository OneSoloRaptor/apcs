import java.util.*;

public class BotPlayer extends Person {

    public BotPlayer(String piece) {
        super(piece);
    }

    @Override
    public void buyProperty(Property property) {
        boolean willBuy = Math.random() < 0.5;
        if (willBuy && money >= property.getPrice()) {
            money -= property.getPrice();
            properties.add(property);
            property.setOwner(this);
            System.out.println(piece + " bought " + property.getName() + " for $" + property.getPrice());
        } else {
            System.out.println(piece + " chose not to buy " + property.getName());
        }
    }

    @Override
    public void move(int roll, int boardLength) {
        position = (position + roll) % boardLength;
        System.out.println(piece + " moved to position " + position);
    }

    @Override
    public void payRent(Person owner, int amount) {
        if (money >= amount) {
            money -= amount;
            owner.receivePayment(amount);
            System.out.println(piece + " paid $" + amount + " in rent to " + owner.getPiece());
        } else {
            System.out.println(piece + " doesn’t have enough money to pay rent.");
        }
    }

    @Override
    public void receivePayment(int amount) {
        money += amount;
        System.out.println(piece + " received $" + amount);
    }

    public void takeTurn(Property[] board) {
        int roll = 2 + (int) (Math.random() * 6) + (int) (Math.random() * 6) ;
        System.out.println(piece + " rolls a " + roll);

        move(roll, board.length);

  
        Property landed = board[position];
        System.out.println(piece + " landed on: " + landed.getName());
        if (landed.getOwner() == null && landed.isBuyable()) {
            buyProperty(landed);
        } else if (landed.getOwner() != null && !landed.getOwner().equals(this)) {
            int rent = landed.getRent(roll);
            payRent(landed.getOwner(), rent);
        } else {
            System.out.println(piece + " landed on their own property or a non-buyable space.");
        }
    }
}
