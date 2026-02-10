// package Tileproject.controller;
// import org.springframework.web.bind.annotation.*;

// import Tileproject.model.Inventory;
// import Tileproject.service.InventoryService;

// 	@RestController
// 	@RequestMapping("/api/inventory")
// 	public class InventoryController {

// 	    private final InventoryService inventoryService;

// 	    public InventoryController(InventoryService inventoryService) {
// 	        this.inventoryService = inventoryService;
// 	    }

// 	    @GetMapping("/product/{productId}")
// 	    public Inventory getInventory(@PathVariable Integer productId) {
// 	        return inventoryService.getInventoryByProduct(productId);
// 	    }
// 	}

package Tileproject.controller;

import Tileproject.model.Inventory;
import Tileproject.repository.InventoryRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/admin/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminInventoryController {

    private final InventoryRepository inventoryRepository;

    public AdminInventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // 🔥 Get low stock products
    @GetMapping("/low")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Inventory> lowStock() {
        return inventoryRepository.findByStockQuantityLessThan(5);
    }

    // 🔥 RESTOCK API
    @PutMapping("/restock/{productId}")
@PreAuthorize("hasRole('ADMIN')")
public Inventory restock(
        @PathVariable Integer productId,
        @RequestParam Integer quantity
) {
    Inventory inv = inventoryRepository
            .findByProduct_ProductId(productId)
            .orElseThrow(() -> new RuntimeException("Inventory not found"));

    inv.setStockQuantity(quantity);   // 🔥 SET, not ADD
    return inventoryRepository.save(inv);
}

}
