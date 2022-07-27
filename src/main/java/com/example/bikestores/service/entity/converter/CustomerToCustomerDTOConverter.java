package com.example.bikestores.service.entity.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.bikestores.model.Customer;
import com.example.bikestores.to.CustomerDTO;

@Component
public class CustomerToCustomerDTOConverter {
	public List<CustomerDTO> convert(List<Customer> customers) {

		List<CustomerDTO> listCustomerDTO = new ArrayList<>();
		for (Customer item : customers) {

			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCity(item.getCity());
			customerDTO.setCustomerId(item.getCustomerId().intValue());
			customerDTO.setEmail(item.getEmail());
			customerDTO.setFirstName(item.getFirstName());
			customerDTO.setLastName(item.getLastName());
			//customerDTO.setOrders(null);
			customerDTO.setPhone(item.getPhone());
			customerDTO.setState(item.getState());
			customerDTO.setStreet(item.getStreet());
			customerDTO.setZipCode(item.getZipCode());
			listCustomerDTO.add(customerDTO);
		}
		return listCustomerDTO;

	}

}
