package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.example.demo.model.Shipping;
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
import com.example.demo.repository.ShippingRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/")
public class ShippingController {
	@Autowired
	private ShippingRepository shippingRepository;

	@GetMapping("/shipping")
	public List<Shipping> getAllShippingDetails() {
		return shippingRepository.findAll();
	}

	@GetMapping("/shipping/{id}")
	public ResponseEntity<Shipping> getShippingId(@PathVariable(value = "id") Long ShippingId)
			throws ResourceNotFoundException {
		Shipping shipping = shippingRepository.findById(ShippingId)
				.orElseThrow(() -> new ResourceNotFoundException("Shipping Item not found for this id :: " + ShippingId));
		return ResponseEntity.ok().body(shipping);
	}

	@PostMapping("/shipping")
	public Shipping createShipping(@RequestBody Shipping shipping) {
		return shippingRepository.save(shipping);
	}

	@PutMapping("/shipping/{id}")
	public ResponseEntity<Shipping> updateShipping(@PathVariable(value = "id") Long shippingId,
												   @RequestBody Shipping shippingDetails) throws ResourceNotFoundException {
		Shipping shipping = shippingRepository.findById(shippingId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found for this id :: " + shippingId));

		shipping.setNetPrice(shippingDetails.getNetPrice());
		shipping.setItemName(shippingDetails.getItemName());
		shipping.setTrackingNumber(shippingDetails.getTrackingNumber());
		shipping.setAddress(shippingDetails.getAddress());
		shipping.setShippingService(shippingDetails.getShippingService());
		final Shipping updatedShipping = shippingRepository.save(shipping);
		return ResponseEntity.ok(updatedShipping);
	}

	@DeleteMapping("/shipping/{id}")
	public Map<String, Boolean> deleteShipping(@PathVariable(value = "id") Long shippingID)
			throws ResourceNotFoundException {
		Shipping shipping = shippingRepository.findById(shippingID)
				.orElseThrow(() -> new ResourceNotFoundException("Shipping Item not found for this id :: " + shippingID));

		shippingRepository.delete(shipping);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
