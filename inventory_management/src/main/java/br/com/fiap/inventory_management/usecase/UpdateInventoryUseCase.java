package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;

public class UpdateInventoryUseCase {

    public static Inventory updateInventory(Inventory oldInventory, Inventory newInventory) {
        validateOldInventory(oldInventory);
        validateNewInventory(newInventory);
        validateUpdateInventory(oldInventory, newInventory);

        oldInventory.setSku(newInventory.getSku());
        oldInventory.setQuantity(newInventory.getQuantity());
        return oldInventory;
    }

    private static void validateOldInventory(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Old inventory is required");
        }
    }

    private static void validateNewInventory(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("New inventory is required");
        }
    }

    private static void validateUpdateInventory(Inventory oldInventory, Inventory newInventory) {
        if (oldInventory.equals(newInventory)) {
            throw new IllegalArgumentException("Old inventory and new inventory are the same");
        }
    }
}