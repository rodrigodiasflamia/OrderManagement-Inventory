package br.com.fiap.inventory_management.controller;

import br.com.fiap.inventory_management.controller.dto.InventoryDto;
import br.com.fiap.inventory_management.domain.Inventory;
import br.com.fiap.inventory_management.gateway.database.InventoryJpaGateway;
import br.com.fiap.inventory_management.usecase.CreateInventoryUseCase;
import br.com.fiap.inventory_management.usecase.UpdateInventoryUseCase;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryJpaGateway inventoryJpaGateway;

    public InventoryController(InventoryJpaGateway inventoryJpaGateway) {
        this.inventoryJpaGateway = inventoryJpaGateway;
    }

    @Operation(summary = "Busca o produto em estoque",
               description = "Retorna o produto em estoque com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso!")
    })
    @GetMapping("/getInventoryById/{id}")
    public InventoryDto getInventoryById(@PathVariable("id") Long id) {
        return new InventoryDto(inventoryJpaGateway.findById(id));
    }

    @Operation(summary = "Busca o produto em estoque",
               description = "Retorna o produto em estoque com base no seu SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso!")
    })
    @GetMapping("/getInventoryBySku/{sku}")
    public InventoryDto getInventoryBySku(@PathVariable("sku") String sku) {
        return new InventoryDto(inventoryJpaGateway.findBySku(sku));
    }

    @Operation(summary = "Cria um produto em estoque",
               description = "Cria um produto em estoque com base no seu SKU e na quantidade em estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso!")
    })
    @PostMapping("/createInventory/{sku}/{quantity}")
    public InventoryDto createInventory(@PathVariable("sku") String sku, @PathVariable("quantity") int quantity) {
        Inventory inventory = CreateInventoryUseCase.createInventory(sku, quantity);
        return new InventoryDto(inventoryJpaGateway.create(inventory));
    }

    @Operation(summary = "Adiciona quantidade de produtos em estoque",
               description = "Adiciona a quantidade de produtos em estoque com base no seu SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidade adicionada com sucesso!")
    })
    @PatchMapping("/addQuantityInventory/{sku}/{quantity}")
    public InventoryDto addQuantityInventory(@PathVariable("sku") String sku, @PathVariable("quantity") int quantity) {
        Inventory inventory = inventoryJpaGateway.findBySku(sku);
        Inventory updatedInventory = UpdateInventoryUseCase.updateInventory(inventory, inventory.getQuantity() + quantity);

        return new InventoryDto(inventoryJpaGateway.update(updatedInventory));
    }

    @Operation(summary = "Remove quantidade de produtos em estoque",
               description = "Remove a quantidade de produtos em estoque com base no seu SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidade removida com sucesso!")
    })
    @PatchMapping("/removeQuantityInventory/{sku}/{quantity}")
    public InventoryDto removeQuantityInventory(@PathVariable("sku") String sku, @PathVariable("quantity") int quantity) {
        Inventory inventory = inventoryJpaGateway.findBySku(sku);
        Inventory updatedInventory = UpdateInventoryUseCase.updateInventory(inventory, inventory.getQuantity() - quantity);

        return new InventoryDto(inventoryJpaGateway.update(updatedInventory));
    }

    @Operation(summary = "Deleta um produto em estoque",
               description = "Deleta um produto em estoque com base no seu SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto excluído com sucesso!")
    })
    @DeleteMapping("/deleteInventory/{sku}")
    public void deleteInventory(@PathVariable("sku") String sku) {
        Inventory inventory = inventoryJpaGateway.findBySku(sku);
        inventoryJpaGateway.delete(inventory);
    }
}