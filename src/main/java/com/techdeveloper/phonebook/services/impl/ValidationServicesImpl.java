package com.techdeveloper.phonebook.services.impl;

import org.springframework.stereotype.Service;

import com.techdeveloper.phonebook.repository.ValidationRepository;
import com.techdeveloper.phonebook.services.ValidationServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class ValidationServicesImpl implements ValidationServices {

	private final ValidationRepository validationRepository;
	
	@Override
	public boolean validateEmail(String email) {
		return validationRepository.validateEmail(email);
	}

	@Override
	public boolean validateMobileNumber(String mobileNumber) {
		return validationRepository.validateMobileNumber(mobileNumber);
	}

}
