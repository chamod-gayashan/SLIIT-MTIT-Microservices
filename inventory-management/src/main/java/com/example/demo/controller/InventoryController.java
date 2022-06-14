package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.example.demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/")
public class InventoryController {
	@Autowired
	private InventoryRepository inventoryRepository;

	@GetMapping("/inventory")
	public List<Inventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@GetMapping("/inventory/{id}")
	public ResponseEntity<Inventory> getInventoryId(@PathVariable(value = "id") Long InventoryId)
			throws ResourceNotFoundException {
		Inventory inventory = inventoryRepository.findById(InventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found for this id :: " + InventoryId));
		return ResponseEntity.ok().body(inventory);
	}

	@PostMapping("/inventory")
	public Inventory createInventory(@RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@PutMapping("/inventory/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable(value = "id") Long inventoryId,
													 @RequestBody Inventory inventoryDetails) throws ResourceNotFoundException {
		Inventory inventory = inventoryRepository.findById(inventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found for this id :: " + inventoryId));

		inventory.setGenre(inventoryDetails.getGenre());
		inventory.setIsbnNumber(inventoryDetails.getIsbnNumber());
		inventory.setName(inventoryDetails.getName());
		inventory.setPrice(inventoryDetails.getPrice());
		inventory.setAuthor(inventoryDetails.getAuthor());
		final Inventory updatedInventory = inventoryRepository.save(inventory);
		return ResponseEntity.ok(updatedInventory);
	}

	@DeleteMapping("/inventory/{id}")
	public Map<String, Boolean> deleteInventory(@PathVariable(value = "id") Long inventoryID)
			throws ResourceNotFoundException {
		Inventory inventory = inventoryRepository.findById(inventoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found for this id :: " + inventoryID));

		inventoryRepository.delete(inventory);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
