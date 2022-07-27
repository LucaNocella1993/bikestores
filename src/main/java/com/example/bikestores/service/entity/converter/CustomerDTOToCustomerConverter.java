package com.example.bikestores.service.entity.converter;

import org.springframework.stereotype.Component;

import com.example.bikestores.model.Customer;
import com.example.bikestores.to.CustomerDTO;

@Component
public class CustomerDTOToCustomerConverter {

	public Customer convert(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setCity(customerDTO.getCity());
		if (customerDTO.getCustomerId() != null) {
			customer.setCustomerId(Long.valueOf(customerDTO.getCustomerId())); 
		}
		customer.setEmail(customerDTO.getEmail());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		//customer.setOrders(null);
		customer.setPhone(customerDTO.getPhone());
		customer.setState(customerDTO.getState());
		customer.setStreet(customerDTO.getStreet());
		customer.setZipCode(customerDTO.getZipCode());
		return customer;
	}

}
