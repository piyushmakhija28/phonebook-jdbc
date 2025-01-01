package com.techdeveloper.phonebook.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.techdeveloper.phonebook.dto.PhonebookDto;
import com.techdeveloper.phonebook.form.PhonebookForm;

public interface PhonebookRepository {

	@Transactional(rollbackFor = DataAccessException.class,propagation = Propagation.REQUIRES_NEW)
	public void save(PhonebookForm phonebookForm);
	
	public void update(Long id,PhonebookForm phonebookForm);
	
	public PhonebookDto get(Long id);
	
	public List<PhonebookDto> get(Integer limit,Integer offset);
	
	public void delete(Long id);
	
}
