package com.techdeveloper.phonebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ResponseDto<T> {

	private final T data;
	
	private final String message;
	
	private final boolean success;
	
	private final long timestamp;
	
	private final int status;
	
}
