package com.techdeveloper.phonebook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdeveloper.phonebook.annotations.sequence.ValidationSequence;
import com.techdeveloper.phonebook.dto.PhonebookDto;
import com.techdeveloper.phonebook.dto.ResponseDto;
import com.techdeveloper.phonebook.form.PhonebookForm;
import com.techdeveloper.phonebook.services.PhonebookServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/contacts")
@AllArgsConstructor
public class PhonebookRestController {

	private final PhonebookServices phonebookServices;

	@PostMapping
	public ResponseEntity<ResponseDto<Void>> save(@Validated(value = ValidationSequence.class) @RequestBody PhonebookForm phonebookForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			ResponseDto<Void> responseDto = new ResponseDto<>(null, message, false, System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
		}
		ResponseDto<Void> responseDto = phonebookServices.save(phonebookForm);
		return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseDto<Void>> update(@PathVariable Long id,@RequestBody PhonebookForm phonebookForm) {
		ResponseDto<Void> responseDto = phonebookServices.update(id,phonebookForm);
		return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseDto<PhonebookDto>> get(@PathVariable Long id) {
		ResponseDto<PhonebookDto> responseDto = phonebookServices.get(id);
		return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
	}
	
	@GetMapping(value = "/{limit}/{offset}")
	public ResponseEntity<ResponseDto<List<PhonebookDto>>> get(@PathVariable Integer limit,@PathVariable Integer offset) {
		ResponseDto<List<PhonebookDto>> responseDto = phonebookServices.get(limit,offset);
		return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseDto<Void>> delete(@PathVariable Long id) {
		ResponseDto<Void> responseDto = phonebookServices.delete(id);
		return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
	}

}
