package Tileproject.controller;
import org.springframework.web.bind.annotation.*;

import Tileproject.model.Inventory;
import Tileproject.service.InventoryService;

	@RestController
	@RequestMapping("/api/inventory")
	public class InventoryController {

	    private final InventoryService inventoryService;

	    public InventoryController(InventoryService inventoryService) {
	        this.inventoryService = inventoryService;
	    }

	    @GetMapping("/product/{productId}")
	    public Inventory getInventory(@PathVariable Integer productId) {
	        return inventoryService.getInventoryByProduct(productId);
	    }
	}


