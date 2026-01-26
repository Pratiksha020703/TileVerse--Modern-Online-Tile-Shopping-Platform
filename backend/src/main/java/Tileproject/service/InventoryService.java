package Tileproject.service;


	import org.springframework.stereotype.Service;

import Tileproject.model.Inventory;
import Tileproject.repository.InventoryRepository;

	@Service
	public class InventoryService {

	    private final InventoryRepository inventoryRepository;

	    public InventoryService(InventoryRepository inventoryRepository) {
	        this.inventoryRepository = inventoryRepository;
	    }

	    public Inventory getInventoryByProduct(Integer productId) {
	        return inventoryRepository
	                .findByProduct_ProductId(productId)
	                .orElse(null);
	    }

	    public Inventory updateStock(Inventory inventory) {
	        return inventoryRepository.save(inventory);
	    }
	}



