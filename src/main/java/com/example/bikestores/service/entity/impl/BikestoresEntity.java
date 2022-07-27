package com.example.bikestores.service.entity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bikestores.model.Customer;
import com.example.bikestores.repository.CustomerRepository;
import com.example.bikestores.service.entity.converter.CustomerToCustomerDTOConverter;
import com.example.bikestores.to.CustomerDTO;

@Service
//@Transactional values are of default, it is the same thing to use just @Transactional
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = TransactionDefinition.TIMEOUT_DEFAULT)
public class BikestoresEntity {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerToCustomerDTOConverter customerToCustomerDTOConverter;

	@Value("${value.firstName}")
	private String firstName;

	@Value("${value.lastName}")
	private String lastName;
	
	@Value("${value.city}")
	private String city;

	public List<CustomerDTO> readCustomers() {
		//JPA Repository Tests for Debug
		customerRepository.findByFirstNameAndLastNameJPQL(firstName, lastName);
		customerRepository.findByFirstNameAndLastNameNative(firstName, lastName);
		customerRepository.findByCity(city);
		customerRepository.renameCustomer(firstName, lastName, Long.valueOf(1458));
		customerRepository.renameCustomerProcedure(firstName, lastName, Long.valueOf(1459));
		
		return customerToCustomerDTOConverter.convert(customerRepository.findAll());
	}

	public List<CustomerDTO> readCustomersById(String id) {
		List<Customer> customers = new ArrayList<>();
		Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));
		if (customer.isPresent()) {
			Customer customerRead = customer.get();
			customers.add(customerRead);
		}
		return customerToCustomerDTOConverter.convert(customers);
	}

	public List<CustomerDTO> createCustomer(Customer customer) {
		List<Customer> customers = new ArrayList<>();
		Customer customerSaved = customerRepository.save(customer);
		customers.add(customerSaved);
		return customerToCustomerDTOConverter.convert(customers);
	}

	public void deleteCustomer(String id) {
		Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));
		if (customer.isPresent()) {
			customerRepository.deleteById(Long.parseLong(id));
		}
	}

	public List<CustomerDTO> updateCustomer(String id, Customer customer) {
		List<Customer> customers = new ArrayList<>();
		Optional<Customer> customerRead = customerRepository.findById(Long.parseLong(id));
		if (customerRead.isPresent()) {
			Customer customerSaved = customerRepository.save(customer);
			customers.add(customerSaved);
		}
		return customerToCustomerDTOConverter.convert(customers);
	}

	public List<CustomerDTO> partialUpdateCustomer(String id, String name, String surname) {
		List<Customer> customers = new ArrayList<>();
		Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));
		if (customer.isPresent()) {
			Customer customerToUpdate = customer.get();
			customerToUpdate.setFirstName(name);
			customerToUpdate.setLastName(surname);
			Customer customerSaved = customerRepository.save(customerToUpdate);
			customers.add(customerSaved);
		}
		return customerToCustomerDTOConverter.convert(customers);
	}

}
