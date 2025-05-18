package br.com.fiap.inventory_management.domain;

import io.micrometer.common.util.StringUtils;

import java.util.Objects;

public class Inventory {

    private Long id;
    private String sku;
    private int quantity;

    public Inventory(Long id, String sku, int quantity) {
        validateInventoryId(id);
        validateInventorySku(sku);
        validateProductQuantity(quantity);

        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
    }

    public Inventory(String sku, int quantity) {
        validateInventorySku(sku);
        validateProductQuantity(quantity);

        this.sku = sku;
        this.quantity = quantity;
    }

    public void validateInventoryId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id is required");
        }
    }

    public void validateInventorySku(String sku) {
        if (StringUtils.isBlank(sku)) {
            throw new IllegalArgumentException("Sku is required");
        }
    }

    private void validateProductQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;
        return quantity == inventory.quantity
                && Objects.equals(id, inventory.id)
                && Objects.equals(sku, inventory.sku);
    }
}