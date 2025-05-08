package br.com.fiap.inventory_management.usecase;

import br.com.fiap.inventory_management.domain.Inventory;
import io.micrometer.common.util.StringUtils;

public class CreateInventoryUseCase {
    public static Inventory createInventory(String sku, int quantity) {
        validateSku(sku);
        validateQuantity(quantity);

        return new Inventory(sku, quantity);
    }

    private static void validateSku(String sku) {
        if (StringUtils.isBlank(sku)) {
            throw new IllegalArgumentException("Sku is invalid");
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}