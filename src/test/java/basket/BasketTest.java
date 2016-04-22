package basket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BasketTest {

    @InjectMocks
    private Basket fixture;

    @Mock
    private PriceRepository priceRepository;

    @Test
    public void shouldCalculateBasketCostOfEmptyBasket() {

        // Given
        // No items in basket

        // When
        BigDecimal result = fixture.totalCost();

        // Then
        assertThat(result, equalTo(BigDecimal.ZERO));
    }

    @Test
    public void shouldCalculateBasketCostOfSingleItemBasket() {

        // Given
        fixture.addItems(Item.BANANA, 1);
        given(priceRepository.getPrice(Item.BANANA)).willReturn(new BigDecimal("0.10"));

        // When
        BigDecimal result = fixture.totalCost();

        // Then
        assertThat(result, equalTo(new BigDecimal("0.10")));
    }

    @Test
    public void shouldCalculateBasketCostOfMultipleItemSingleItemTypeBasket() {

        // Given
        fixture.addItems(Item.BANANA, 2);
        given(priceRepository.getPrice(Item.BANANA)).willReturn(new BigDecimal("0.10"));

        // When
        BigDecimal result = fixture.totalCost();

        // Then
        assertThat(result, equalTo(new BigDecimal("0.20")));
    }

    @Test
    public void shouldCalculateBasketCostOfMultipleItemMultipleItemTypeBasket() {

        // Given
        fixture.addItems(Item.BANANA, 2);
        fixture.addItems(Item.APPLE, 1);
        fixture.addItems(Item.LEMON, 3);
        given(priceRepository.getPrice(Item.BANANA)).willReturn(new BigDecimal("0.10"));
        given(priceRepository.getPrice(Item.APPLE)).willReturn(new BigDecimal("0.20"));
        given(priceRepository.getPrice(Item.LEMON)).willReturn(new BigDecimal("0.30"));

        // When
        BigDecimal result = fixture.totalCost();

        // Then
        assertThat(result, equalTo(new BigDecimal("1.30")));
    }
}