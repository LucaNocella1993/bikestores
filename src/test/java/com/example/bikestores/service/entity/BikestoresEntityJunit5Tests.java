package com.example.bikestores.service.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.bikestores.model.Customer;
import com.example.bikestores.repository.CustomerRepository;
import com.example.bikestores.service.entity.converter.CustomerToCustomerDTOConverter;
import com.example.bikestores.service.entity.impl.BikestoresEntity;
import com.example.bikestores.to.CustomerDTO;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BikestoresEntity.class})
@TestPropertySource(properties = { "value.firstName=Luca", "value.lastName=Nocella", "value.city=Napoli",}) 

class BikestoresEntityJunit5Tests {

	@Autowired
	private BikestoresEntity bikestoresEntity;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private CustomerToCustomerDTOConverter customerToCustomerDTOConverter;

	@Test
	void readCustomersTest() {
		List<Customer> customerList = buildCustomerList();
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.readCustomers();
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void readCustomers2Test() {
		List<Customer> customerList = buildCustomerList();
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		ReflectionTestUtils.setField(bikestoresEntity, "firstName", "LLL");
		ReflectionTestUtils.setField(bikestoresEntity, "lastName", "NNN");
		ReflectionTestUtils.setField(bikestoresEntity, "city", "Naples");
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.readCustomers();
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void readCustomerByIdTest() {
		Customer customer = buildCustomer();
		Optional<Customer> optionalCustomer = Optional.ofNullable(customer);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.readCustomersById("1");
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void readCustomerByIdEmptyTest() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.readCustomersById("1");
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void createCustomerTest() {
		Customer customer = buildCustomer();
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.createCustomer(customer);
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void deleteCustomerByIdTest() {
		Customer customer = buildCustomer();
		Optional<Customer> optionalCustomer = Optional.ofNullable(customer);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		assertDoesNotThrow(() -> bikestoresEntity.deleteCustomer("1"));
	}

	@Test
	void deleteCustomerByIdEmptyTest() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		assertDoesNotThrow(() -> bikestoresEntity.deleteCustomer("1"));
	}

	@Test
	void updateCustomerTest() {
		Customer customer = buildCustomer();
		Optional<Customer> optionalCustomer = Optional.ofNullable(customer);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.updateCustomer("1", customer);
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void updateCustomerEmptyTest() {
		Customer customer = buildCustomer();
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.updateCustomer("1", customer);
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void partialUpdateCustomerTest() {
		Customer customer = buildCustomer();
		Optional<Customer> optionalCustomer = Optional.ofNullable(customer);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.partialUpdateCustomer("1", "Luca", "Nocella");
		assertNotNull(customerDTOListResponse);
	}

	@Test
	void partialUpdateCustomerEmptyTest() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		Mockito.when(customerToCustomerDTOConverter.convert(Mockito.any())).thenReturn(customerDTOList);
		List<CustomerDTO> customerDTOListResponse = bikestoresEntity.partialUpdateCustomer("1", "Luca", "Nocella");
		assertNotNull(customerDTOListResponse);
	}

	private Customer buildCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Luca");
		customer.setLastName("Nocella");
		return customer;
	}

	private List<Customer> buildCustomerList() {
		Customer customer = buildCustomer();
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		return customerList;
	}

	private List<CustomerDTO> buildCustomerDTOList() {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTOList.add(customerDTO);
		return customerDTOList;
	}
}
