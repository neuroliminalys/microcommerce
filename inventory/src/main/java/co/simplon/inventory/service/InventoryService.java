package co.simplon.inventory.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.simplon.inventory.entity.Inventory;
import co.simplon.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(UUID productId, int initialQuantity) {
        Inventory inventory = new Inventory(null, productId, initialQuantity);
        return inventoryRepository.save(inventory);
    }

    public Optional<Integer> getStockByBookId(UUID productId) {
        return inventoryRepository.findByProductId(productId)
                .map(Inventory::getStockQuantity);
    }

    public void updateStock(UUID productId, int newQuantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for Book ID: " + productId));

        inventory.setStockQuantity(newQuantity);

        inventoryRepository.save(inventory);
    }
}
