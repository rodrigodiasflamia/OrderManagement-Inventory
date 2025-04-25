package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateInventoryUseCaseTest {
    private final Inventory defaultOldInventory = new Inventory("160799-0001", 10);
    private final Inventory defaultNewInventory = new Inventory("160799-0001", 15);

    @Test
    void throwsExceptionWhenOldInventoryIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateInventoryUseCase.updateInventory(null, defaultNewInventory),
                "Old inventory is required"
        );
    }

    @Test
    void throwsExceptionWhenNewInventoryIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateInventoryUseCase.updateInventory(defaultOldInventory, null),
                "New inventory is required"
        );
    }

    @Test
    void throwsExceptionWhenOldInventoryIsEqualToNewInventory() {
        final Inventory newInventory = new Inventory("160799-0001", 10);

        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateInventoryUseCase.updateInventory(defaultOldInventory, newInventory),
                "Old inventory and new inventory are the same"
        );
    }

    @Test
    void updateInventory() {
        final Inventory updatedInventory = UpdateInventoryUseCase.updateInventory(defaultOldInventory, defaultNewInventory);

        assertEquals(defaultNewInventory, updatedInventory);
    }
}