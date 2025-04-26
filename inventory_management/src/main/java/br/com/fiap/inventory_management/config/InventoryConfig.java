package br.com.fiap.inventory_management.config;

import br.com.fiap.inventory_management.gateway.database.InventoryJpaGateway;
import br.com.fiap.inventory_management.gateway.database.jpa.repository.InventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Bean
    public InventoryJpaGateway inventoryJpaGateway(InventoryRepository inventoryRepository) {
        return new InventoryJpaGateway(inventoryRepository);
    }
}