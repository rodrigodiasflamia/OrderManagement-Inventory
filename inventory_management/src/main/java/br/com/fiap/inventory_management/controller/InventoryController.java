package br.com.fiap.inventory_management.controller;

import br.com.fiap.inventory_management.controller.dto.InventoryDto;
import br.com.fiap.inventory_management.domain.Inventory;
import br.com.fiap.inventory_management.gateway.database.InventoryJpaGateway;
import br.com.fiap.inventory_management.usecase.CreateInventoryUseCase;
import br.com.fiap.inventory_management.usecase.UpdateInventoryUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryJpaGateway inventoryJpaGateway;

    public InventoryController(InventoryJpaGateway inventoryJpaGateway) {
        this.inventoryJpaGateway = inventoryJpaGateway;
    }

    @GetMapping("/getInventoryById/{id}")
    public InventoryDto getInventoryById(@PathVariable("id") Long id) {
        return new InventoryDto(inventoryJpaGateway.findById(id));
    }

    @GetMapping("/getInventoryBySku/{sku}")
    public InventoryDto getInventoryBySku(@PathVariable("sku") String sku) {
        return new InventoryDto(inventoryJpaGateway.findBySku(sku));
    }

    @PostMapping("/createInventory")
    public InventoryDto createInventory(@RequestBody InventoryDto inventoryDto) {
        Inventory inventory = CreateInventoryUseCase.createInventory(inventoryDto.sku(), inventoryDto.quantity());
        return new InventoryDto(inventoryJpaGateway.create(inventory));
    }

    @PutMapping("/updateInventory/{sku}/{quantity}")
    public InventoryDto updateInventory(@PathVariable("sku") String sku, @PathVariable("quantity") int quantity) {
        Inventory inventory = inventoryJpaGateway.findBySku(sku);
        Inventory updatedInventory = UpdateInventoryUseCase.updateInventory(inventory, quantity);

        return new InventoryDto(inventoryJpaGateway.update(updatedInventory));
    }

    @DeleteMapping("/deleteInventory/{sku}")
    public void deleteInventory(@PathVariable("sku") String sku) {
        Inventory inventory = inventoryJpaGateway.findBySku(sku);
        inventoryJpaGateway.delete(inventory);
    }
}