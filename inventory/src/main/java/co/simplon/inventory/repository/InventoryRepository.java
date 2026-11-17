package co.simplon.inventory.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    public Optional<Inventory> findByProductId(UUID productId);
}
