package Tileproject.controller;

import Tileproject.model.Inventory;
import Tileproject.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // 🔥 Get stock of ONE product
    @GetMapping("/product/{productId}")
    public Inventory getStock(@PathVariable Integer productId) {
        return inventoryRepository
            .findByProduct_ProductId(productId)
            .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
}
