package com.example.bikestores.service.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bikestores.config.resttemplate.RestTemplateClient;
import com.example.bikestores.exception.CustomerNotFoundException;
import com.example.bikestores.service.entity.converter.CustomerDTOToCustomerConverter;
import com.example.bikestores.service.entity.impl.BikestoresEntity;
import com.example.bikestores.to.CustomerDTO;
import com.example.bikestores.util.BikestoresConstants;
import com.example.bikestores.validator.ValidateCustomer;


//@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BikestoresBusiness.class})
class BikestoresBusinessJunit5Tests {

	@Autowired
	private BikestoresBusiness bikestoresBusiness;

	@MockBean
	private BikestoresEntity bikestoresEntity;

	@MockBean
	private RestTemplateClient restTemplateClient;

	@MockBean
	private CustomerDTOToCustomerConverter customerDTOToCustomerConverter;

	@MockBean
	private ValidateCustomer validateCustomer;

	@Test
	void readCustomersTest() {
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresEntity.readCustomers()).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.readCustomers();
		assertNotNull(customerDTOListResponse);
	}

	//	@Test
	//	void readCustomerConsumingRestTests() {
	//		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
	//		ResponseEntity<Object> response = new ResponseEntity(CustomerDTO[].class, HttpStatus.OK);
	//        Mockito.when(restTemplate.getForEntity(Mockito.any(), Mockito.any())).thenReturn(response);
	//        List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.readCustomerConsumingRest();
	//        assertNotNull(customerDTOListResponse);
	//	}

	@Test
	void readCustomersByIdTest() {	
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListOutput = bikestoresBusiness.readCustomersById("1570");
		assertNotNull(customerDTOListOutput);
		assertEquals(customerDTOList,customerDTOListOutput);
		assertSame(customerDTOList,customerDTOListOutput);
	}

	@Test
	void readCustomersByIdEmptyTest() {	
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<>());
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			bikestoresBusiness.readCustomersById("1570");
		});

		String expectedMessage = BikestoresConstants.CUSTOMER_NOT_FOUND;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	//	@Test
	//	void createCustomer(CustomerDTO customerDTO) {
	//		validateCustomer.validate(customerDTO);
	//		return bikestoresEntity.createCustomer(customerDTOToCustomerConverter.convert(customerDTO));
	//	}
	//
	//	@Test
	//	void deleteCustomer(String id) {
	//		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
	//		if (listCostumers.isEmpty()){
	//			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
	//		}
	//		bikestoresEntity.deleteCustomer(id);
	//	}
	//
	//	@Test
	//	void updateCustomer(String id, @Valid CustomerDTO customerDTO) {
	//		validateCustomer.validate(customerDTO);
	//		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
	//		if (listCostumers.isEmpty()){
	//			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
	//		}
	//		customerDTO.setCustomerId(Integer.valueOf(id));
	//		return bikestoresEntity.updateCustomer(id, customerDTOToCustomerConverter.convert(customerDTO));
	//	}
	//
	//	@Test
	//	void partialUpdateCustomer(String id, String name, String surname) {
	//		List<CustomerDTO> listCostumers = bikestoresEntity.readCustomersById(id);
	//		if (listCostumers.isEmpty()){
	//			throw new CustomerNotFoundException(BikestoresConstants.CUSTOMER_NOT_FOUND);
	//		}
	//		return bikestoresEntity.partialUpdateCustomer(id, name, surname);
	//
	//	}

	private List<CustomerDTO> buildCustomerDTOList() {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTOList.add(customerDTO);
		return customerDTOList;
	}
}
