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
        int[] possiblePositions = {
             1, 3, 5, 6, 8, 9, 11, 12, 13,
             14, 15, 16, 18, 19, 21, 23, 24, 25,
             26, 27, 28, 29, 31, 32, 34, 35, 37,
             38, 39
        };

        ArrayList<Integer> validPositions = new ArrayList<>();
        for (int pos : possiblePositions) {
            int distance = (pos - position + boardLength) % boardLength; // Handle wrapping around the board
            if (distance > 0 && distance <= 12) {
                validPositions.add(pos);
            }
        }

        int randomIndex = (int) (Math.random() * validPositions.size());
        position = validPositions.get(randomIndex);
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
        int prevPosition = position;
        move(0, board.length);
        int roll = (position-prevPosition + 40)%40;
        System.out.println(piece + " rolls a " + roll);

  
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
