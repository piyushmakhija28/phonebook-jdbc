package com.techdeveloper.phonebook.annotations;

import org.springframework.stereotype.Component;

import com.techdeveloper.phonebook.services.ValidationServices;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ValidateMobileNumberImpl implements ConstraintValidator<ValidateMobileNumber, String> {
	
	private final ValidationServices validationServices;
	
	@Override
	public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
		return validationServices.validateMobileNumber(mobileNumber);
	}

}
