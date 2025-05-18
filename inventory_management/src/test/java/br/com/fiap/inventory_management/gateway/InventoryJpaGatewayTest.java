package br.com.fiap.inventory_management.gateway;

import br.com.fiap.inventory_management.config.InventoryConfig;
import br.com.fiap.inventory_management.domain.Inventory;
import br.com.fiap.inventory_management.gateway.database.InventoryJpaGateway;
import br.com.fiap.inventory_management.gateway.database.jpa.entity.InventoryEntity;
import br.com.fiap.inventory_management.gateway.database.jpa.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(InventoryConfig.class)
public class InventoryJpaGatewayTest {
    private final Long id = 1L;
    private final String sku = "160799-0001";
    private final int quantity = 10;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryJpaGateway inventoryJpaGateway;

    private Inventory inventory;
    private InventoryEntity inventoryEntity;

    @BeforeEach
    void setUp(){
        inventory = new Inventory(id, sku, quantity);
        inventoryEntity = new InventoryEntity(new Inventory(id, sku, quantity));
    }

    @Test
    public void validadeFindById() {
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventoryEntity));

        Inventory result = inventoryJpaGateway.findById(id);

        assertNotNull(result);
        assertEquals(sku, result.getSku());
        assertEquals(quantity, result.getQuantity());
    }

    @Test
    void validadeFindByIdNotFound() {
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryJpaGateway.findById(id);
        });

        assertEquals("Inventory not found", exception.getMessage());
    }

    @Test
    public void validadeFindBySku() {
        when(inventoryRepository.findBySku(sku)).thenReturn(Optional.of(inventoryEntity));

        Inventory result = inventoryJpaGateway.findBySku(sku);

        assertNotNull(result);
        assertEquals(sku, result.getSku());
        assertEquals(quantity, result.getQuantity());
    }

    @Test
    void validadeFindBySkuNotFound() {
        when(inventoryRepository.findBySku(sku)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryJpaGateway.findBySku(sku);
        });

        assertEquals("Inventory not found", exception.getMessage());
    }

    @Test
    public void validadeCreate() {
        when(inventoryRepository.findBySku(sku)).thenReturn(Optional.empty());
        when(inventoryRepository.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);

        Inventory result = inventoryJpaGateway.create(inventory);

        assertNotNull(result);
        assertEquals(sku, result.getSku());
        assertEquals(quantity, result.getQuantity());
    }

    @Test
    void validadeCreateAlreadyExists() {
        when(inventoryRepository.findBySku(sku)).thenReturn(Optional.of(inventoryEntity));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryJpaGateway.create(inventory);
        });

        assertEquals("Inventory already exists", exception.getMessage());
    }

    @Test
    public void validadeUpdate() {
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventoryEntity));
        when(inventoryRepository.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);

        Inventory result = inventoryJpaGateway.update(inventory);

        assertNotNull(result);
        assertEquals(sku, result.getSku());
        assertEquals(quantity, result.getQuantity());
    }

    @Test
    void validadeUpdateNotFound() {
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryJpaGateway.update(inventory);
        });

        assertEquals("Inventory not found", exception.getMessage());
    }

    @Test
    public void validadeDelete() {
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventoryEntity));

        inventoryJpaGateway.delete(inventory);

        verify(inventoryRepository, times(1)).delete(inventoryEntity);
    }

    @Test
    void validadeDeleteNotFound() {
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryJpaGateway.delete(inventory);
        });

        assertEquals("Inventory not found", exception.getMessage());
    }
}