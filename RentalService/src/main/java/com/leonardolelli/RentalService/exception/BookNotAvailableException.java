package com.leonardolelli.RentalService.exception;

public class BookNotAvailableException extends RuntimeException {

	public BookNotAvailableException() {
		super("This book is not available for renting");
	}

}
