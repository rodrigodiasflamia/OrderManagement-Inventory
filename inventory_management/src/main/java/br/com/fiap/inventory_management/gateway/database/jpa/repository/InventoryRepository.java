package br.com.fiap.inventory_management.gateway.database.jpa.repository;

import br.com.fiap.inventory_management.gateway.database.jpa.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    @Query(value = """
        select i.* from inventory i where i.sku = :sku
    """, nativeQuery = true)
    Optional<InventoryEntity> findBySku(String sku);
}