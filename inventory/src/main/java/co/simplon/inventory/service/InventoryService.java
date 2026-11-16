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

    public Inventory createInventory(UUID bookId, int initialQuantity) {
        Inventory inventory = new Inventory(null, bookId, initialQuantity);
        return inventoryRepository.save(inventory);
    }

    public Optional<Integer> getStockByBookId(UUID bookId) {
        return inventoryRepository.findByBookId(bookId)
                .map(Inventory::getStockQuantity);
    }

    public void updateStock(UUID bookId, int newQuantity) {
        Inventory inventory = inventoryRepository.findByBookId(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for Book ID: " + bookId));

        inventory.setStockQuantity(newQuantity);

        inventoryRepository.save(inventory);
    }
}
