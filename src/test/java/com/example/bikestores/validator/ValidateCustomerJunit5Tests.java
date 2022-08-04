package com.example.bikestores.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bikestores.exception.ParameterNotFoundException;
import com.example.bikestores.to.CustomerDTO;
import com.example.bikestores.util.BikestoresConstants;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ValidateCustomer.class})
class ValidateCustomerJunit5Tests {

	@Autowired
	private ValidateCustomer validateCustomer;

	@Test
	void validateCustomerTest() {
		CustomerDTO customerDTO = buildCustomerDTO();
		validateCustomer.validate(customerDTO);
	}

	@Test
	void validateCustomerNameNullTest() {
		CustomerDTO customerDTO = buildCustomerDTO();
		customerDTO.setFirstName(null);

		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			validateCustomer.validate(customerDTO);
		});

		String expectedMessage = BikestoresConstants.FIRST_NAME_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void validateCustomerSurnameNullTest() {
		CustomerDTO customerDTO = buildCustomerDTO();
		customerDTO.setLastName(null);
		Exception exception = assertThrows(ParameterNotFoundException.class, () -> {
			validateCustomer.validate(customerDTO);
		});

		String expectedMessage = BikestoresConstants.LAST_NAME_IS_EMPTY_OR_NULL;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	private CustomerDTO buildCustomerDTO() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Luca");
		customerDTO.setLastName("Nocella");
		return customerDTO;
	}
}
