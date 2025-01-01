package com.techdeveloper.phonebook.services;

import java.util.List;

import com.techdeveloper.phonebook.dto.PhonebookDto;
import com.techdeveloper.phonebook.dto.ResponseDto;
import com.techdeveloper.phonebook.form.PhonebookForm;

public interface PhonebookServices {

	public ResponseDto<Void> save(PhonebookForm phonebookForm);

	public ResponseDto<Void> update(Long id,PhonebookForm phonebookForm);
	
	public ResponseDto<PhonebookDto> get(Long id);
	
	public ResponseDto<List<PhonebookDto>> get(Integer limit,Integer offset);
	
	public ResponseDto<Void> delete(Long id);
	
}
