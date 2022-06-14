package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.example.demo.model.Customer;
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
import com.example.demo.repository.CustomerRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers")
	public List<Customer> getAllEmployees() {
		return customerRepository.findAll();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getUserById(@PathVariable(value = "id") Long customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/customers")
	public Customer createEmployee(@RequestBody Customer employee) {
		return customerRepository.save(employee);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateEmployee(@PathVariable(value = "id") Long customerId,
												   @RequestBody Customer customerDetails) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customer.setEmailId(customerDetails.getEmailId());
		customer.setLastName(customerDetails.getLastName());
		customer.setFirstName(customerDetails.getFirstName());
		customer.setGender(customerDetails.getGender());
		customer.setContactNumber(customerDetails.getContactNumber());
		final Customer updatedCustomer = customerRepository.save(customer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerID)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerID)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerID));

		customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
