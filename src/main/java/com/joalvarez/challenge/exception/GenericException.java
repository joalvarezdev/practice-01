package com.joalvarez.challenge.exception;

import com.joalvarez.challenge.data.enums.InternalCode;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GenericException extends RuntimeException{

	private final int code;
	private final HttpStatus status;
	private final List<String> details;

	public GenericException (InternalCode internalCode, String message, HttpStatus status) {
		super(message);
		this.code = internalCode.code();
		this.details = List.of(internalCode.message());
		this.status = status;
	}

	public GenericException(InternalCode internalCode, String message, HttpStatus status, List<String> details) {
		super(message);
		this.code = internalCode.code();
		this.details = details;
		this.status = status;
	}


	public int getCode() {
		return this.code;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public List<String> getDetails() {
		return this.details;
	}
}
