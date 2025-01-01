package com.techdeveloper.phonebook.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ValidationRepository {

	@Transactional(rollbackFor = DataAccessException.class,propagation = Propagation.REQUIRED,readOnly = true)
	public boolean validateEmail(String email);
	
	@Transactional(rollbackFor = DataAccessException.class,propagation = Propagation.REQUIRED,readOnly = true)
	public boolean validateMobileNumber(String mobileNumber);
	
}
