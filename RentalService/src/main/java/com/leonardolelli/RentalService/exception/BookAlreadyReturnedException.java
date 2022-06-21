package com.leonardolelli.RentalService.exception;

public class BookAlreadyReturnedException extends RuntimeException {

    public BookAlreadyReturnedException() {
	super("Book already returned");
    }

}
