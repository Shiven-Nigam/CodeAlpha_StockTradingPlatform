import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("AAPL", "Apple", 190));
        market.add(new Stock("GOOG", "Google", 2800));
        market.add(new Stock("TSLA", "Tesla", 250));
        market.add(new Stock("AMZN", "Amazon", 3500));

        User user = new User("Saurabh", 100000);

        while (true) {

            System.out.println("\n========== STOCK TRADING PLATFORM ==========");
            System.out.println("1. Display Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Show Balance");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\nAvailable Stocks");

                    for (Stock s : market) {

                        System.out.println(s.getSymbol()
                                + " - "
                                + s.getCompanyName()
                                + " : ₹"
                                + s.getPrice());

                    }

                    break;

                case 2:

                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next().toUpperCase();

                    Stock buyStock = findStock(market, buySymbol);

                    if (buyStock == null) {

                        System.out.println("Stock not found.");
                        break;

                    }

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    double cost = qty * buyStock.getPrice();

                    if (user.withdraw(cost)) {

                        user.getPortfolio().buyStock(buySymbol, qty);

                        System.out.println("Stock Purchased Successfully.");

                    } else {

                        System.out.println("Insufficient Balance.");

                    }

                    break;

                case 3:

                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next().toUpperCase();

                    Stock sellStock = findStock(market, sellSymbol);

                    if (sellStock == null) {

                        System.out.println("Stock not found.");
                        break;

                    }

                    System.out.print("Quantity: ");
                    int sellQty = sc.nextInt();

                    if (user.getPortfolio().sellStock(sellSymbol, sellQty)) {

                        double amount = sellQty * sellStock.getPrice();

                        user.deposit(amount);

                        System.out.println("Stock Sold Successfully.");

                    } else {

                        System.out.println("You don't own enough shares.");

                    }

                    break;

                case 4:

                    System.out.println("\nPortfolio");

                    double total = 0;

                    for (Map.Entry<String, Integer> entry : user.getPortfolio().getHoldings().entrySet()) {

                        Stock stock = findStock(market, entry.getKey());

                        double value = stock.getPrice() * entry.getValue();

                        total += value;

                        System.out.println(entry.getKey()
                                + " | Quantity: "
                                + entry.getValue()
                                + " | Value: ₹"
                                + value);

                    }

                    System.out.println("Total Portfolio Value : ₹" + total);

                    break;

                case 5:

                    System.out.println("Available Balance : ₹" + user.getBalance());

                    break;

                case 6:

                    System.out.println("Thank You!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice");

            }

        }

    }

    public static Stock findStock(ArrayList<Stock> market, String symbol) {

        for (Stock s : market) {

            if (s.getSymbol().equalsIgnoreCase(symbol))
                return s;

        }

        return null;

    }

}