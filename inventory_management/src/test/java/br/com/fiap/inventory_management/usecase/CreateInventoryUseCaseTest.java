package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateInventoryUseCaseTest {
    private final String defaultSku = "160799-0001";
    private final int defaultQuantity = 10;

    @Test
    void throwsExceptionWhenSkuIsInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CreateInventoryUseCase.createInventory("", defaultQuantity),
                "Sku is invalid"
        );
    }

    @Test
    void throwsExceptionWhenQuantityIsInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CreateInventoryUseCase.createInventory(defaultSku, -1),
                "Quantity must be greater than 0"
        );
    }

    @Test
    void shouldCreateInventoryUseCase() {
        Inventory inventory = CreateInventoryUseCase.createInventory(defaultSku, defaultQuantity);

        assertInstanceOf(Inventory.class, inventory);
    }
}