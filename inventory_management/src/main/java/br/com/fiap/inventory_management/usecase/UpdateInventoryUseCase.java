package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;

public class UpdateInventoryUseCase {

    public static Inventory updateInventory(Inventory inventory, int newQuantity) {
        validateNewQuantity(newQuantity);

        inventory.setQuantity(newQuantity);
        return inventory;
    }

    private static void validateNewQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("There are not enough products in inventory");
        }
    }
}