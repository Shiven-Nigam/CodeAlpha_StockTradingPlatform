import java.util.HashMap;

public class Portfolio {

    private HashMap<String, Integer> holdings = new HashMap<>();

    public void buyStock(String symbol, int quantity) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
    }

    public boolean sellStock(String symbol, int quantity) {

        if (!holdings.containsKey(symbol))
            return false;

        int current = holdings.get(symbol);

        if (current < quantity)
            return false;

        if (current == quantity)
            holdings.remove(symbol);
        else
            holdings.put(symbol, current - quantity);

        return true;
    }

    public HashMap<String, Integer> getHoldings() {
        return holdings;
    }
}