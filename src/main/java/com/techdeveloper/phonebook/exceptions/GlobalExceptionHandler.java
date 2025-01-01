package com.techdeveloper.phonebook.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techdeveloper.phonebook.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<ResponseDto<Void>> handleDataAccessException(DataAccessException dataAccessException) {
		ResponseDto<Void> responseDto = new ResponseDto<Void>(null, dataAccessException.getMessage(), false,
				System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
	}

}
