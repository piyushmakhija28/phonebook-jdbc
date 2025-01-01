package com.techdeveloper.phonebook.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techdeveloper.phonebook.constants.ServiceMessageConstants;
import com.techdeveloper.phonebook.dto.PhonebookDto;
import com.techdeveloper.phonebook.dto.ResponseDto;
import com.techdeveloper.phonebook.form.PhonebookForm;
import com.techdeveloper.phonebook.repository.PhonebookRepository;
import com.techdeveloper.phonebook.services.PhonebookServices;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Service
@AllArgsConstructor
class PhonebookServicesImpl extends ServiceMessageConstants implements PhonebookServices {

	private final PhonebookRepository phonebookRepository;

	@Override
	@SneakyThrows(DataAccessException.class)
	public ResponseDto<Void> save(PhonebookForm phonebookForm) {
		phonebookRepository.save(phonebookForm);
		return new ResponseDto<>(null, PHONEBOOK_CONTACT_SAVED_SUCCESSFULLY, true, System.currentTimeMillis(),
				HttpStatus.CREATED.value());
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public ResponseDto<Void> update(Long id, PhonebookForm phonebookForm) {
		phonebookRepository.update(id, phonebookForm);
		return new ResponseDto<>(null, PHONEBOOK_CONTACT_UPDATED_SUCCESSFULLY, true, System.currentTimeMillis(),
				HttpStatus.OK.value());
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public ResponseDto<PhonebookDto> get(Long id) {
		PhonebookDto phonebookDto = phonebookRepository.get(id);
		return new ResponseDto<>(phonebookDto, PHONEBOOK_CONTACT_FOUND_SUCCESSFULLY, true, System.currentTimeMillis(),
				HttpStatus.FOUND.value());
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public ResponseDto<List<PhonebookDto>> get(Integer limit, Integer offset) {
		List<PhonebookDto> phonebookDtoList = phonebookRepository.get(limit, offset);
		return new ResponseDto<>(phonebookDtoList, PHONEBOOK_CONTACTS_FETCHED_SUCCESSFULLY, true, System.currentTimeMillis(),
				HttpStatus.OK.value());
	}

	@Override
	@SneakyThrows(DataAccessException.class)
	public ResponseDto<Void> delete(Long id) {
		phonebookRepository.delete(id);
		return new ResponseDto<>(null, PHONEBOOK_CONTACTS_DELETED_SUCCESSFULLY, true, System.currentTimeMillis(),
				HttpStatus.OK.value());
	}

}
