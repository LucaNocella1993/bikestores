package com.example.bikestores.validator;

import org.springframework.stereotype.Component;

import com.example.bikestores.exception.ParameterNotFoundException;
import com.example.bikestores.to.CustomerDTO;
import com.example.bikestores.util.ValidatorUtil;

@Component
public class ValidateCustomer {

	public void validate(CustomerDTO customerDTO) {
		if (!ValidatorUtil.exist(customerDTO.getFirstName())) {
			throw new ParameterNotFoundException("First name is empty or null.");
		}

		if (!ValidatorUtil.exist(customerDTO.getLastName())) {
			throw new ParameterNotFoundException("Last name is empty or null.");
		}
	}

}
