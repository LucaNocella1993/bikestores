package com.example.bikestores.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bikestores.service.business.BikestoresBusiness;
import com.example.bikestores.to.CustomerDTO;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BikestoresController.class})
class BikestoreControllerJunit5Tests {

	@Autowired
	private BikestoresController bikestoresController;

	@MockBean
	private BikestoresBusiness bikestoresBusiness;

	@Test
	void readCustomersTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.readCustomers()).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.readCustomers();
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	@Test
	void readCustomersByConsumingRestTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.readCustomerConsumingRest()).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.readCustomersConsumingRest();
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	@Test
	void readCustomersByIdTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.readCustomersById(Mockito.any())).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.readCustomerById("1");
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	@Test
	void readCustomersByIdWithParamsTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.readCustomersById(Mockito.any())).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.readCustomerByIdWithParams("1");
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	@Test
	void createCustomerTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.createCustomer(Mockito.any())).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.createCustomer(customerDTOList.get(0));
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	@Test
	void deleteCustomerTest() {
		bikestoresController.deleteCustomer("1");
		ResponseEntity<Void> response = bikestoresController.deleteCustomer("1");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void updateCustomerTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.updateCustomer("1", customerDTOList.get(0));
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	@Test
	void partialupdateCustomerTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresBusiness.partialUpdateCustomer(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(customerDTOList);
		ResponseEntity<List<CustomerDTO>> customerDTOListResponse = bikestoresController.updateNameCustomer("1", "Luca", "Nocella");
		assertNotNull(customerDTOListResponse);
		assertEquals(customerDTOList, customerDTOListResponse.getBody());
	}

	private List<CustomerDTO> buildCustomerDTOList() {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTOList.add(customerDTO);
		return customerDTOList;
	}
}
