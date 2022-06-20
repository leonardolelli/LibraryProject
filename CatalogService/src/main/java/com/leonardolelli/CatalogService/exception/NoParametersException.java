package com.leonardolelli.CatalogService.exception;

public class NoParametersException extends RuntimeException {

	public NoParametersException() {
		super("No parameters specified");
	}

}
