package com.joalvarez.challenge.data.enums;

public enum InternalCode {

	OPERATION_SUCCESS(1000, "Operation performed correctly"),

	// PRODUCTS
	PRODUCTS_EMPTY(1100, "No products available for display"),
	PRODUCT_DONT_EXISTS(1101, "Product does not exist"),
	PRODUCT_NAME_EMPTY(1102, "Product name is required"),
	PRODUCT_ALREADY_EXISTS(1103, "Product already exists"),
	INVALID_DATA(1104, "Invalid Format"),
	ATTRIBUTE_VALIDATION(1105, "Some fields are invalid, check details")
	;



	private int code;
	private String message;

	InternalCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}

	public void message(String message) {
		this.message = message;
	}
}
