package co.simplon.inventory.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.inventory.entity.Inventory;
import co.simplon.inventory.service.InventoryService;

@RestController 
@RequestMapping ("/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{bookId}")
    public Inventory initializeInventory(@PathVariable UUID bookId,
            @RequestParam(defaultValue = "0") int initialQuantity) {
        Inventory inventory = inventoryService.createInventory(bookId, initialQuantity);

        logger.info("Inventory created for Book ID: " + bookId + " with quantity: " + initialQuantity);

        return inventory;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Integer> getStock(@PathVariable UUID bookId) {
        return inventoryService.getStockByBookId(bookId)
                .map(stock -> ResponseEntity.ok(stock))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateStock(@PathVariable UUID bookId,
            @RequestParam int newQuantity) {
        // Appelle le service pour mettre à jour le stock
        inventoryService.updateStock(bookId, newQuantity);

        // Retourne une réponse indiquant que la mise à jour a réussi
        return ResponseEntity.ok("Stock updated for Book ID: " + bookId + " with new quantity: " + newQuantity);
    }
}