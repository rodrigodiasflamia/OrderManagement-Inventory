package br.com.fiap.inventory_management.gateway;

import br.com.fiap.inventory_management.domain.Inventory;

public interface InventoryGateway {
    Inventory findById(Long id);

    Inventory findBySku(String sku);

    Inventory create(Inventory inventory);

    Inventory update(Inventory inventory);

    void delete(Inventory inventory);
}