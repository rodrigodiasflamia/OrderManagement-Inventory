package br.com.fiap.inventory_management.controller;

import br.com.fiap.inventory_management.controller.dto.InventoryDto;
import br.com.fiap.inventory_management.domain.Inventory;
import br.com.fiap.inventory_management.gateway.database.InventoryJpaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryControllerTest {
    @Mock
    private InventoryJpaGateway inventoryJpaGateway;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp(){
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)){
            inventoryController = new InventoryController(inventoryJpaGateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validadeGetInventoryById(){
        Inventory inventory = new Inventory(1L,"160799-0001", 10);
        when(inventoryJpaGateway.findById(1L)).thenReturn(inventory);

        InventoryDto inventoryDto = inventoryController.getInventoryById(1L);

        assertEquals("160799-0001", inventoryDto.sku());
        assertEquals(10, inventoryDto.quantity());
    }

    @Test
    public void validadeGetInventoryBySku() {
        Inventory inventory = new Inventory(1L, "160799-0002", 15);
        when(inventoryJpaGateway.findBySku("160799-0002")).thenReturn(inventory);

        InventoryDto inventoryDto = inventoryController.getInventoryBySku("160799-0002");

        assertEquals(1L, inventoryDto.id());
        assertEquals(15, inventoryDto.quantity());
    }

    @Test
    public void validadeCreateInventory() {
        Inventory inventory = new Inventory(1L, "160799-0003", 20);
        when(inventoryJpaGateway.create(any())).thenReturn(inventory);

        InventoryDto inventoryDto = inventoryController.createInventory("160799-0003", 20);

        assertEquals("160799-0003", inventoryDto.sku());
        assertEquals(20, inventoryDto.quantity());
    }

    @Test
    public void validadeAddQuantityInventory() {
        Inventory inventory = new Inventory(1L, "160799-0004", 25);
        when(inventoryJpaGateway.findBySku("160799-0004")).thenReturn(inventory);
        when(inventoryJpaGateway.update(inventory)).thenReturn(inventory);

        InventoryDto inventoryDto = inventoryController.addQuantityInventory("160799-0004", 5);

        assertEquals(1L, inventoryDto.id());
        assertEquals(30, inventoryDto.quantity());
    }

    @Test
    public void validadeRemoveQuantityInventory() {
        Inventory inventory = new Inventory(1L, "160799-0005", 30);
        when(inventoryJpaGateway.findBySku("160799-0005")).thenReturn(inventory);
        when(inventoryJpaGateway.update(inventory)).thenReturn(inventory);

        InventoryDto inventoryDto = inventoryController.removeQuantityInventory("160799-0005", 5);

        assertEquals(1L, inventoryDto.id());
        assertEquals(25, inventoryDto.quantity());
    }

    @Test
    public void validadeDeleteInventory() {
        Inventory inventory = new Inventory(1L, "160799-0006", 35);
        when(inventoryJpaGateway.findBySku("160799-0006")).thenReturn(inventory);

        doNothing().when(inventoryJpaGateway).delete(inventory);

        inventoryController.deleteInventory("160799-0006");
        verify(inventoryJpaGateway, times(1)).delete(inventory);
    }
}