package com.example.bikestores.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikestores.service.business.BikestoresBusiness;
import com.example.bikestores.to.CustomerDTO;


@RestController
@RequestMapping(path="/test")
@Validated
public class BikestoresController {

	@Autowired
	private BikestoresBusiness bikestoresBusiness;

	@GetMapping(path="/customers", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> readCustomers() {
		return new ResponseEntity<>(bikestoresBusiness.readCustomers(),  HttpStatus.OK);	
	}

	//CONSUMING REST APPLICATION
	@GetMapping(path="/customersConsumingRest", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> readCustomersConsumingRest() {
		return new ResponseEntity<>(bikestoresBusiness.readCustomerConsumingRest(), HttpStatus.OK);	
	}

	@GetMapping(path="/customer/{id}", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> readCustomerById(@PathVariable String id) {
		return new ResponseEntity<>(bikestoresBusiness.readCustomersById(id), HttpStatus.OK);
	}

	@GetMapping(path="/customerWithParams", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> readCustomerByIdWithParams(@RequestParam(required = false, defaultValue = "1") String id) {
		return new ResponseEntity<>(bikestoresBusiness.readCustomersById(id), HttpStatus.OK);
	}

	@PostMapping(path="/customer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
		return new ResponseEntity<>(bikestoresBusiness.createCustomer(customerDTO), HttpStatus.OK);
	}

	@DeleteMapping(path="/customer/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
		bikestoresBusiness.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path="/customer/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerDTO customerDTO) {
		return new ResponseEntity<>(bikestoresBusiness.updateCustomer(id, customerDTO), HttpStatus.OK);
	}

	@PatchMapping(path="/customerName/{id}", produces = "application/json")
	public ResponseEntity<List<CustomerDTO>> updateNameCustomer(@PathVariable String id, @RequestParam String name, @RequestHeader("cognome") String surname) {
		return new ResponseEntity<>(bikestoresBusiness.partialUpdateCustomer(id, name, surname), HttpStatus.OK);
	}
}
