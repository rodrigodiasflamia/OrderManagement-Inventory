package br.com.fiap.inventory_management.controller.dto;

import br.com.fiap.inventory_management.domain.Inventory;

public record InventoryDto(Long id, String sku, int quantity) {

    public InventoryDto(Inventory inventory){
        this(inventory.getId(), inventory.getSku(), inventory.getQuantity());
    }
}