package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;

public class CreateInventoryUseCase {
    public static Inventory createInventory(String sku, int quantity) {
        return new Inventory(sku, quantity);
    }
}