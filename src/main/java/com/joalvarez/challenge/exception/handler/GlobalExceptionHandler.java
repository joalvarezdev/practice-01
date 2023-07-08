package com.joalvarez.challenge.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.joalvarez.challenge.data.dto.ResponseDTO;
import com.joalvarez.challenge.data.enums.InternalCode;
import com.joalvarez.challenge.exception.GenericException;
import com.joalvarez.challenge.exception.HasLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler implements HasLogger {

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ResponseDTO> handler(GenericException ex) {
		ResponseDTO dto = new ResponseDTO();

		dto.setCode(ex.getCode());
		dto.setMessage(ex.getMessage());
		dto.setDetails(ex.getDetails());

		return new ResponseEntity<>(dto, ex.getStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handler(MethodArgumentNotValidException ex) {
		ResponseDTO dto = new ResponseDTO();

		dto.setCode(InternalCode.ATTRIBUTE_VALIDATION.code());
		dto.setMessage(InternalCode.ATTRIBUTE_VALIDATION.message());
		List<String> errors =
			ex.getBindingResult().getAllErrors().stream().filter(error -> error instanceof FieldError).map(error -> (FieldError) error)
				.map((error) -> "The field {" + error.getField() + "} is invalid for the value {" + error.getRejectedValue() +
					"} with the following cause: " + error.getDefaultMessage()).collect(Collectors.toList());
		dto.setDetails(errors);

		return ResponseEntity.badRequest().body(dto);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ResponseDTO> handler(InvalidFormatException ex) {
		ResponseDTO dto = new ResponseDTO();

		dto.setCode(InternalCode.INVALID_DATA.code());
		dto.setMessage(InternalCode.INVALID_DATA.message());
		dto.setDetails(List.of(ex.getOriginalMessage()));

		return ResponseEntity.badRequest().body(dto);
	}
}
