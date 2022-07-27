package com.example.bikestores.service.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bikestores.config.resttemplate.RestTemplateClient;
import com.example.bikestores.exception.CustomerNotFoundException;
import com.example.bikestores.service.entity.converter.CustomerDTOToCustomerConverter;
import com.example.bikestores.service.entity.impl.BikestoresEntity;
import com.example.bikestores.to.CustomerDTO;
import com.example.bikestores.util.BikestoresConstants;
import com.example.bikestores.validator.ValidateCustomer;

@Service
public class BikestoresBusiness {

	@Autowired
	private BikestoresEntity bikestoresEntity;

	@Autowired
	private CustomerDTOToCustomerConverter customerDTOToCustomerConverter;

	@Autowired
	private ValidateCustomer validateCustomer;

	@Autowired
	private RestTemplateClient restTemplateClient;


	Logger logger = LoggerFactory.getLogger(BikestoresBusiness.class);

	public List<CustomerDTO> readCustomers() {
		return bikestoresEntity.readCustomers();
	}

	public List<CustomerDTO> readCustomerConsumingRest() {
		RestTemplate restTemplate = restTemplateClient.restTemplate();
		ResponseEntity<CustomerDTO[]> customerConsumedArrayResponse =  restTemplate.getForEntity(
				"http://localhost:8080/bikestores/test/customers", CustomerDTO[].class);
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		CustomerDTO[] customerConsumedArray = customerConsumedArrayResponse.getBody();
		customerDTOList.addAll(Arrays.asList(customerConsumedArray));
		if (customerConsumedArray != null) {			
			logger.info(customerConsumedArray[0].getCity());
		}
		return customerDTOList;
	}

	public List<CustomerDTO> readCustomersById(String id) {
		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
		if (listCostumers.isEmpty()){
			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
		}
		return listCostumers;
	}

	public List<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
		validateCustomer.validate(customerDTO);
		return bikestoresEntity.createCustomer(customerDTOToCustomerConverter.convert(customerDTO));
	}

	public void deleteCustomer(String id) {
		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
		if (listCostumers.isEmpty()){
			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
		}
		bikestoresEntity.deleteCustomer(id);
	}

	public List<CustomerDTO> updateCustomer(String id, @Valid CustomerDTO customerDTO) {
		validateCustomer.validate(customerDTO);
		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
		if (listCostumers.isEmpty()){
			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
		}
		customerDTO.setCustomerId(Integer.valueOf(id));
		return bikestoresEntity.updateCustomer(id, customerDTOToCustomerConverter.convert(customerDTO));
	}

	public List<CustomerDTO> partialUpdateCustomer(String id, String name, String surname) {
		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
		if (listCostumers.isEmpty()){
			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
		}
		return bikestoresEntity.partialUpdateCustomer(id, name, surname);

	}

}
