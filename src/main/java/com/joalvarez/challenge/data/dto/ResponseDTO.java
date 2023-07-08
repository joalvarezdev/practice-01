package com.joalvarez.challenge.data.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable {

	private int code;
	private String message;
	private List<String> details;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}
