package basket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<Item, Integer> items = new HashMap<>();

    private PriceRepository priceRepository;

    public Basket(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public void addItems(Item item, int count) {
        items.put(item, count);
    }

    public BigDecimal totalCost() {
        return items
                .entrySet()
                .stream()
                .map((i) -> priceRepository.getPrice(i.getKey()).multiply(new BigDecimal(i.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
