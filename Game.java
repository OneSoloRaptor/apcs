import java.util.*;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private Property[] board = new Property[40];
    private Person user;
    private BotPlayer bot1;
    private BotPlayer bot2;
    private Random random = new Random();
    private String[] invalidInputs = {
        "Oops! That’s not quite right.",
        "Try again, but this time, read carefully!",
        "Not an option, unfortunately.",
        "That wasn’t one of the choices!",
        "Are you even trying?"
    };

    public void startGame() {
        System.out.println("Welcome to MONOPOLY!");
        System.out.println("Press ENTER to start a game.");

        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) break;
            else printRandomExcuse();
        }

        chooseGamePiece();
        setupBoard();
        bot1 = new BotPlayer("Dog");
        bot2 = new BotPlayer("Car");

        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            System.out.println("\n--- Your Turn ---");
            System.out.println("Press ENTER to roll, 3 to view balance and properties, or q to quit:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) exitGame();
            if (input.equals("3")) {
                printStatus();
                continue;
            }

            int roll = user.rollDice();
            System.out.println("You rolled a " + roll + "!");
            user.move(roll, board.length);

            Property landed = board[user.getPositionIndex()];
            System.out.println("You landed on: " + landed.getName());

            if (landed instanceof Chance || landed instanceof CommunityChest) {
                user.setMoney(user.getMoney() + landed.getRent(roll, user));
            } 
         
            else if (landed instanceof GoToJail) {
                user.setPosition(10); // Move to Jail position
                user.setMoney(user.getMoney() - 100); // Pay $100
            System.out.println("You have landed on Go to Jail and are now in jail. You will pay $100 and move back.");
            }
            else if (landed instanceof Go) {
                user.setMoney(user.getMoney() + 200); // Pay $100
                System.out.println("You have landed on Go. Good job! You will receive $200.");
            }
            else if (landed instanceof IncomeTax) {
                user.setMoney((int)(user.getMoney()*0.85)); 
                System.out.println("Unfortunately you have to pay tax (maybe evade it next time), lose 15% of your net worth.");
            }
            else if (landed instanceof FreeParking) {
                System.out.println("Kind of boring but just take a break, there's free parking!");
            }
            else if (landed instanceof Jail) {
                System.out.println("You are just visiting jail. Nothing to worry about.");;
            }

            else if (landed.getOwner() == null) {
                System.out.println("This property is unowned. Would you like to buy it for $" + landed.getPrice() + "? (y/n)");
                while (true) {
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        user.buyProperty(landed);
                        break;
                    } else if (response.equalsIgnoreCase("n")) {
                        System.out.println("You chose not to buy.");
                        break;
                    } else printRandomExcuse();
                }
            } else if (!landed.getOwner().equals(user)) {
                int rent = landed.getRent(roll);
                user.payRent(landed.getOwner(), rent);
                System.out.println("You paid $" + rent + " to " + landed.getOwner().getPiece());
            } else {
                System.out.println("You landed on your own property.");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println();

            bot1.takeTurn(board);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println();

            bot2.takeTurn(board);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println();
        }
    }

    private void chooseGamePiece() {
        System.out.println("Choose your game piece:");
        System.out.println("1. Hat");
        System.out.println("2. Thimble");
        System.out.println("3. Iron");
        System.out.println("4. Boot");
        while (true) {
            String choice = scanner.nextLine();
            if (choice != "") {
                if (choice.equals("1")) {
                    user = new Person("Hat");
                    System.out.println("You chose " + user.getPiece() + ".");
                    return;
                } else if (choice.equals("2")) {
                    user = new Person("Thimble");
                    System.out.println("You chose " + user.getPiece() + ".");
                    return;
                } else if (choice.equals("3")) {
                    user = new Person("Iron");
                    System.out.println("You chose " + user.getPiece() + ".");
                    return;
                } else if (choice.equals("4")) {
                    user = new Person("Boot");
                    System.out.println("You chose " + user.getPiece() + ".");
                    return;
                } else {
                    printRandomExcuse();
                }
            }
        }
    }
        
    

    private void printStatus() {
        System.out.println("Your balance: $" + user.getMoney());
        System.out.println("Your properties:");
        for (Property prop : user.getProperties()) {
            System.out.println("- " + prop.getName());
        }
    }

    private void exitGame() {
        System.out.println("Calculating final scores...");
        //time delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        

        int userNetWorth = user.getMoney();
        for (Property prop : user.getProperties()) {
            userNetWorth += prop.getPrice();
        }

        int bot1NetWorth = bot1.getMoney();
        for (Property prop : bot1.getProperties()) {
            bot1NetWorth += prop.getPrice();
        }

        int bot2NetWorth = bot2.getMoney();
        for (Property prop : bot2.getProperties()) {
            bot2NetWorth += prop.getPrice();
        }

        System.out.println("Final Scores:");
        System.out.println(user.getPiece() + " (You): $" + userNetWorth);
        System.out.println(bot1.getPiece() + ": $" + bot1NetWorth);
        System.out.println(bot2.getPiece() + ": $" + bot2NetWorth);


        if (userNetWorth > bot1NetWorth && userNetWorth > bot2NetWorth) {
            System.out.println("Congratulations! You won the game!");
        } else if (bot1NetWorth > userNetWorth && bot1NetWorth > bot2NetWorth) {
            System.out.println(bot1.getPiece() + " won the game!");
        } else if (bot2NetWorth > userNetWorth && bot2NetWorth > bot1NetWorth) {
            System.out.println(bot2.getPiece() + " won the game!");
        } else {
            System.out.println("It's a tie!");
        }

        System.out.println("Thanks for playing! Goodbye.");
        System.exit(0);
    }

    private void printRandomExcuse() {
        System.out.println(invalidInputs[random.nextInt(invalidInputs.length)]);
    }

    public void setupBoard() {
        board[0] = new Go("Go", 0, 0, false);
        board[1] = new ColoredProperty("Mediterranean Avenue", 60, "Brown", 2);
        board[2] = new CommunityChest();
        board[3] = new ColoredProperty("Baltic Avenue", 60, "Brown", 4);
        board[4] = new IncomeTax("Income Tax", 0, 0, false);
        board[5] = new Railroad("Reading Railroad", 200);
        board[6] = new ColoredProperty("Oriental Avenue", 100, "Light Blue", 6);
        board[7] = new Chance();
        board[8] = new ColoredProperty("Vermont Avenue", 100, "Light Blue", 6);
        board[9] = new ColoredProperty("Connecticut Avenue", 120, "Light Blue", 8);
        board[10] = new Jail("Jail / Just Visiting", 0, 0, false);
        board[11] = new ColoredProperty("St. Charles Place", 140, "Pink", 10);
        board[12] = new Utility("Electric Company", 150);
        board[13] = new ColoredProperty("States Avenue", 140, "Pink", 10);
        board[14] = new ColoredProperty("Virginia Avenue", 160, "Pink", 12);
        board[15] = new Railroad("Pennsylvania Railroad", 200);
        board[16] = new ColoredProperty("St. James Place", 180, "Orange", 14);
        board[17] = new CommunityChest();
        board[18] = new ColoredProperty("Tennessee Avenue", 180, "Orange", 14);
        board[19] = new ColoredProperty("New York Avenue", 200, "Orange", 16);
        board[20] = new FreeParking("Free Parking", 0, 0, false);
        board[21] = new ColoredProperty("Kentucky Avenue", 220, "Red", 18);
        board[22] = new Chance();
        board[23] = new ColoredProperty("Indiana Avenue", 220, "Red", 18);
        board[24] = new ColoredProperty("Illinois Avenue", 240, "Red", 20);
        board[25] = new Railroad("B&O Railroad", 200);
        board[26] = new ColoredProperty("Atlantic Avenue", 260, "Yellow", 22);
        board[27] = new ColoredProperty("Ventnor Avenue", 260, "Yellow", 22);
        board[28] = new Utility("Water Works", 150);
        board[29] = new ColoredProperty("Marvin Gardens", 280, "Yellow", 24);
        board[30] = new GoToJail("Go To Jail", 0, 0, false);
        board[31] = new ColoredProperty("Pacific Avenue", 300, "Green", 26);
        board[32] = new ColoredProperty("North Carolina Avenue", 300, "Green", 26);
        board[33] = new CommunityChest();
        board[34] = new ColoredProperty("Pennsylvania Avenue", 320, "Green", 28);
        board[35] = new Railroad("Short Line", 200);
        board[36] = new Chance();
        board[37] = new ColoredProperty("Park Place", 350, "Dark Blue", 35);
        board[38] = new IncomeTax("Income Tax", 0, 0, false);
        board[39] = new ColoredProperty("Boardwalk", 400, "Dark Blue", 50);
    }
}
