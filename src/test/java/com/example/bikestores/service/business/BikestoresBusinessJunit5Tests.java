package com.example.bikestores.service.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.example.bikestores.config.resttemplate.RestTemplateClient;
import com.example.bikestores.exception.CustomerNotFoundException;
import com.example.bikestores.exception.ParameterNotFoundException;
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
	private RestTemplate restTemplate;
	
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

		@Test
		void readCustomerConsumingRestTests() {
			List<CustomerDTO> customerDTOList = buildCustomerDTOList();
			ResponseEntity<Object> response = new ResponseEntity(CustomerDTO[].class, HttpStatus.OK);
	        Mockito.when(restTemplate.getForEntity(Mockito.any(), Mockito.any())).thenReturn(response);
	        List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.readCustomerConsumingRest();
	        assertNotNull(customerDTOListResponse);
		}

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
	void readCustomersByIdEmptyDTOTest() {	
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<>());
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			bikestoresBusiness.readCustomersById("1570");
		});

		String expectedMessage = BikestoresConstants.CUSTOMER_NOT_FOUND;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void readCustomersByIdEmptyIdTest() {	
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<>());
		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			bikestoresBusiness.readCustomersById(null);
		});

		String expectedMessage = BikestoresConstants.CUSTOMER_ID_PARAMETER_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void createCustomerTest() {
		Mockito.doNothing().when(validateCustomer).validate(Mockito.any());
		Mockito.when(bikestoresEntity.createCustomer(Mockito.any())).thenReturn(new ArrayList<>());
		List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.createCustomer(buildCustomerDTO());
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void deleteCustomersByIdTest() {	
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(customerDTOList);
		assertDoesNotThrow(() -> bikestoresBusiness.deleteCustomer("1570"));		
	}

	@Test
	void deleteCustomersByIdEmptyDTOTest() {	
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<>());
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			bikestoresBusiness.deleteCustomer("1570");
		});

		String expectedMessage = BikestoresConstants.CUSTOMER_NOT_FOUND;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void deleteCustomersByIdEmptyIdTest() {	
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<>());
		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			bikestoresBusiness.deleteCustomer(null);
		});

		String expectedMessage = BikestoresConstants.CUSTOMER_ID_PARAMETER_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void updateCustomerTest() {
		Mockito.doNothing().when(validateCustomer).validate(Mockito.any());
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(buildCustomerDTOList());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());
		List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.updateCustomer("1", buildCustomerDTO());
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void updateCustomerEmptyListTest() {
		CustomerDTO customerDTO = buildCustomerDTO();
		Mockito.doNothing().when(validateCustomer).validate(Mockito.any());
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<CustomerDTO>());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());
		Exception exception = assertThrows(CustomerNotFoundException.class, () ->
		bikestoresBusiness.updateCustomer("1", customerDTO));

		String expectedMessage = BikestoresConstants.CUSTOMER_NOT_FOUND;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void updateCustomerParameterNotFoundTest() {
		CustomerDTO customerDTO = buildCustomerDTO();
		Mockito.doNothing().when(validateCustomer).validate(Mockito.any());
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(buildCustomerDTOList());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());

		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			bikestoresBusiness.updateCustomer(null, customerDTO);
		});

		String expectedMessage = BikestoresConstants.CUSTOMER_ID_PARAMETER_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void partialupdateCustomerTest() {
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(buildCustomerDTOList());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());
		List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.partialUpdateCustomer("1", "Luca", "Nocella");
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void partialupdateCustomerIdNullTest() {
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(buildCustomerDTOList());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());

		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			bikestoresBusiness.partialUpdateCustomer(null, "Luca", "Nocella");});

		String expectedMessage = BikestoresConstants.CUSTOMER_ID_PARAMETER_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void partialupdateCustomerFirstNameNullTest() {
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(buildCustomerDTOList());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());

		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			bikestoresBusiness.partialUpdateCustomer("1", null, "Nocella");});

		String expectedMessage = BikestoresConstants.FIRST_NAME_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void partialupdateCustomerLastNameNullTest() {
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(buildCustomerDTOList());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());

		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			bikestoresBusiness.partialUpdateCustomer("1", "Luca", null);});

		String expectedMessage = BikestoresConstants.LAST_NAME_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void partialupdateCustomerEmptyListTest() {
		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<CustomerDTO>());
		Mockito.when(bikestoresEntity.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(buildCustomerDTOList());

		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			bikestoresBusiness.partialUpdateCustomer("1", "Luca", "Nocella");});

		String expectedMessage = BikestoresConstants.CUSTOMER_NOT_FOUND;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	private List<CustomerDTO> buildCustomerDTOList() {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Luca");
		customerDTO.setLastName("Nocella");
		customerDTOList.add(customerDTO);
		return customerDTOList;
	}

	private CustomerDTO buildCustomerDTO() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Luca");
		customerDTO.setLastName("Nocella");
		return customerDTO;
	}
}
