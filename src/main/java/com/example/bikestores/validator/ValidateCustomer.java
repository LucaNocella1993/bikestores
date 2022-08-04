package com.example.bikestores.validator;

import org.springframework.stereotype.Component;

import com.example.bikestores.exception.ParameterNotFoundException;
import com.example.bikestores.to.CustomerDTO;
import com.example.bikestores.util.BikestoresConstants;
import com.example.bikestores.util.ValidatorUtil;

@Component
public class ValidateCustomer {

	public void validate(CustomerDTO customerDTO) {
		if (Boolean.FALSE.equals(ValidatorUtil.exist(customerDTO.getFirstName()))) {
			throw new ParameterNotFoundException(BikestoresConstants.FIRST_NAME_IS_EMPTY_OR_NULL);
		}

		if (Boolean.FALSE.equals(ValidatorUtil.exist(customerDTO.getLastName()))) {
			throw new ParameterNotFoundException(BikestoresConstants.LAST_NAME_IS_EMPTY_OR_NULL);
		}
	}
}
