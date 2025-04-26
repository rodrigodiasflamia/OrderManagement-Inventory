package br.com.fiap.inventory_management.gateway.database.jpa.entity;

import br.com.fiap.inventory_management.domain.Inventory;
import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private int quantity;

    public InventoryEntity() {}

    public InventoryEntity(Inventory inventory) {
        this.id = inventory.getId();
        this.sku = inventory.getSku();
        this.quantity = inventory.getQuantity();
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

    public Inventory toInventory() {
        return new Inventory(id, sku, quantity);
    }
}