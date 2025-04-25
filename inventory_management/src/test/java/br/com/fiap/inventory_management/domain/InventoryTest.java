package br.com.fiap.inventory_management.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {

    private final String defaultSku = "160799-0001";
    private final int defaultQuantity = 10;

    @Test
    void throwsExceptionWhenIdIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory(null, defaultSku, defaultQuantity),
                "Id is required"
        );
    }

    @Test
    void throwsExceptionWhenIdIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory(-1L, defaultSku, defaultQuantity),
                "Id is required"
        );
    }

    @Test
    void throwsExceptionWhenSkuIsBlank() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory("", defaultQuantity),
                "Sku is required"
        );
    }

    @Test
    void throwsExceptionWhenQuantityIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory(defaultSku, -1),
                "Quantity cannot be negative"
        );
    }
}