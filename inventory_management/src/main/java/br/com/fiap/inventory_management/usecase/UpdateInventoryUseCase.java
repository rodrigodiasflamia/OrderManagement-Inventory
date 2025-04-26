package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;

public class UpdateInventoryUseCase {

    public static Inventory updateInventory(Inventory inventory, int quantity) {
        validateOldInventory(inventory);
        validateNewQuantity(quantity);
        validateUpdateQuantityInventory(inventory.getQuantity(), quantity);

        inventory.setQuantity(quantity);
        return inventory;
    }

    private static void validateOldInventory(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Old inventory is required");
        }
    }

    private static void validateNewQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("New quantity is invalid");
        }
    }

    private static void validateUpdateQuantityInventory(int oldQuantity, int newQuantity) {
        if (oldQuantity == newQuantity) {
            throw new IllegalArgumentException("Old quantity and new quantity are the same");
        }
    }
}