package com.leonardolelli.RentalService.exception;

public class BookNotRentedException extends RuntimeException {

    public BookNotRentedException() {
	super("Book already returned");
    }

}
