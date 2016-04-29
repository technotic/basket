package basket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;

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
        BigDecimal cost = items
                .entrySet()
                .stream()
                .map((i) -> priceRepository.getPrice(i.getKey()).multiply(new BigDecimal(i.getValue())))
                .reduce(ZERO, BigDecimal::add);

        return cost.subtract(getDiscount());
    }

    private BigDecimal getDiscount() {
        return isDiscountable() ? calculateDiscount() : ZERO;
    }

    private BigDecimal calculateDiscount() {
        Optional<BigDecimal> cheapest = items
                .keySet()
                .stream()
                .distinct()
                .map((i) -> priceRepository.getPrice(i)).min(BigDecimal::compareTo);
        return cheapest.orElse(ZERO);
    }

    private boolean isDiscountable() {
        return totalItems() > 1;
    }

    private int totalItems() {
        return items.values().stream().reduce(0, Integer::sum);
    }
}
