package br.com.fiap.inventory_management.gateway.database;

import br.com.fiap.inventory_management.domain.Inventory;
import br.com.fiap.inventory_management.gateway.InventoryGateway;
import br.com.fiap.inventory_management.gateway.database.jpa.entity.InventoryEntity;
import br.com.fiap.inventory_management.gateway.database.jpa.repository.InventoryRepository;

import java.util.Optional;

public class InventoryJpaGateway implements InventoryGateway {

    private InventoryRepository inventoryRepository;

    public InventoryJpaGateway(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory findById(Long id) {
        Optional<InventoryEntity> inventoryEntity =  inventoryRepository.findById(id);
        if (inventoryEntity.isEmpty()) {
            throw new IllegalArgumentException("Inventory not found");
        }
        return inventoryEntity.get().toInventory();
    }

    @Override
    public Inventory findBySku(String sku) {
        Optional<InventoryEntity> inventoryEntity =  inventoryRepository.findBySku(sku);
        if (inventoryEntity.isEmpty()) {
            throw new IllegalArgumentException("Inventory not found");
        }
        return inventoryEntity.get().toInventory();
    }

    @Override
    public Inventory create(Inventory inventory) {
        boolean hasInventoryForSku = inventoryRepository.findBySku(inventory.getSku()).isPresent();
        if (hasInventoryForSku) {
            throw new IllegalArgumentException("Inventory already exists");
        }

        InventoryEntity inventoryEntity = inventoryRepository.save(new InventoryEntity(inventory));
        return inventoryEntity.toInventory();
    }

    @Override
    public Inventory update(Inventory inventory) {
        Optional<InventoryEntity> inventoryEntity =  inventoryRepository.findById(inventory.getId());
        if (inventoryEntity.isEmpty()) {
            throw new IllegalArgumentException("Inventory not found");
        }

        InventoryEntity updatedInventoryEntity = inventoryRepository.save(new InventoryEntity(inventory));
        return updatedInventoryEntity.toInventory();
    }

    @Override
    public void delete(Inventory inventory) {
        Optional<InventoryEntity> inventoryEntity =  inventoryRepository.findById(inventory.getId());
        if (inventoryEntity.isEmpty()) {
            throw new IllegalArgumentException("Inventory not found");
        }

        inventoryRepository.delete(inventoryEntity.get());
    }
}