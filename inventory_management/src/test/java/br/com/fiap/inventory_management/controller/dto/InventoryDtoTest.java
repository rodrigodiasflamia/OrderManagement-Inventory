package br.com.fiap.inventory_management.controller.dto;

import br.com.fiap.inventory_management.domain.Inventory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryDtoTest {

    private final Inventory inventory = new Inventory("160799-0001", 10);

    @Test
    void createInventory(){
        final InventoryDto inventoryDto = new InventoryDto(inventory);

        assertEquals(inventory.getSku(), inventoryDto.sku());
        assertEquals(inventory.getQuantity(), inventoryDto.quantity());
    }
}