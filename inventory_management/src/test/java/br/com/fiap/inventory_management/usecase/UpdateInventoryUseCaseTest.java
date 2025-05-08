package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateInventoryUseCaseTest {
    private final Inventory defaultOldInventory = new Inventory("160799-0001", 10);
    private final int defaultNewQuantity = 15;

    @Test
    void throwsExceptionWhenNewQuantityIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateInventoryUseCase.updateInventory(defaultOldInventory, -1),
                "There are not enough products in inventory"
        );
    }

    @Test
    void updateInventory() {
        final Inventory updatedInventory = UpdateInventoryUseCase.updateInventory(defaultOldInventory, defaultNewQuantity);

        assertEquals(defaultOldInventory, updatedInventory);
    }
}