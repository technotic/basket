package basket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceRepository {

    private Map<Item, BigDecimal> prices = new HashMap<>();

    public void addPrice(Item item, BigDecimal price) {
        prices.put(item, price);
    }

    public BigDecimal getPrice(Item item) {
        return prices.get(item);
    }
}
