package br.com.fiap.inventory_management.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private final String defaultSku = "160799-0001";
    private final int defaultQuantity = 10;

    @Test
    void throwsExceptionWhenIdIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory(null, defaultSku, defaultQuantity)
        );
        assertEquals("Id is required", exception.getMessage());
    }

    @Test
    void throwsExceptionWhenIdIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory(-1L, defaultSku, defaultQuantity)
        );
        assertEquals("Id is required", exception.getMessage());
    }

    @Test
    void throwsExceptionWhenSkuIsBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory("", defaultQuantity)
        );
        assertEquals("Sku is required", exception.getMessage());
    }

    @Test
    void throwsExceptionWhenQuantityIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Inventory(defaultSku, -1)
        );
        assertEquals("Quantity cannot be negative", exception.getMessage());
    }

    @Test
    void validateGettersAndSettersWithId() {
        Inventory inventory = new Inventory(1L, defaultSku, defaultQuantity);

        assertEquals(1L, inventory.getId());
        assertEquals(defaultSku, inventory.getSku());
        assertEquals(defaultQuantity, inventory.getQuantity());

        inventory.setId(2L);
        inventory.setSku("160799-0002");
        inventory.setQuantity(15);

        assertEquals(2L, inventory.getId());
        assertEquals("160799-0002", inventory.getSku());
        assertEquals(15, inventory.getQuantity());
    }

    @Test
    void validateGettersAndSettersWithoutId() {
        Inventory inventory = new Inventory(defaultSku, defaultQuantity);

        assertNull(inventory.getId());
        assertEquals(defaultSku, inventory.getSku());
        assertEquals(defaultQuantity, inventory.getQuantity());

        inventory.setId(3L);
        inventory.setSku("160799-0002");
        inventory.setQuantity(25);

        assertEquals(3L, inventory.getId());
        assertEquals("160799-0002", inventory.getSku());
        assertEquals(25, inventory.getQuantity());
    }

    @Test
    void testEqualsReflexive() {
        Inventory inventory = new Inventory(1L, "SKU123", 10);
        assertTrue(inventory.equals(inventory));
    }

    @Test
    void testEqualsSymmetric() {
        Inventory inv1 = new Inventory(1L, "SKU123", 10);
        Inventory inv2 = new Inventory(1L, "SKU123", 10);

        assertTrue(inv1.equals(inv2));
        assertTrue(inv2.equals(inv1));
    }

    @Test
    void testEqualsConsistent() {
        Inventory inv1 = new Inventory(1L, "SKU123", 10);
        Inventory inv2 = new Inventory(1L, "SKU123", 10);

        assertTrue(inv1.equals(inv2));
        assertTrue(inv1.equals(inv2));
    }

    @Test
    void testEqualsNull() {
        Inventory inventory = new Inventory(1L, "SKU123", 10);
        assertFalse(inventory.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        Inventory inventory = new Inventory(1L, "SKU123", 10);
        String other = "não é um Inventory";
        assertFalse(inventory.equals(other));
    }

    @Test
    void testEqualsDifferentId() {
        Inventory inv1 = new Inventory(1L, "SKU123", 10);
        Inventory inv2 = new Inventory(2L, "SKU123", 10);
        assertFalse(inv1.equals(inv2));
    }

    @Test
    void testEqualsDifferentSku() {
        Inventory inv1 = new Inventory(1L, "SKU123", 10);
        Inventory inv2 = new Inventory(1L, "SKU456", 10);
        assertFalse(inv1.equals(inv2));
    }

    @Test
    void testEqualsDifferentQuantity() {
        Inventory inv1 = new Inventory(1L, "SKU123", 10);
        Inventory inv2 = new Inventory(1L, "SKU123", 20);
        assertFalse(inv1.equals(inv2));
    }
}