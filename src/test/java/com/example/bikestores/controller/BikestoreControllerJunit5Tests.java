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

	private List<CustomerDTO> buildCustomerDTOList() {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTOList.add(customerDTO);
		return customerDTOList;
	}
}
