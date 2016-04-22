package basket;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class PriceRepositoryTest {

    @Test
    public void shouldStoreAndRetrivePrices() {

        // Given
        PriceRepository fixture = new PriceRepository();

        // When
        fixture.addPrice(Item.APPLE, new BigDecimal("1.0"));
        BigDecimal result = fixture.getPrice(Item.APPLE);

        // Then
        assertThat(result, equalTo(new BigDecimal("1.0")));
    }
}