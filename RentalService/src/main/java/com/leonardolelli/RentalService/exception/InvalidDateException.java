package com.leonardolelli.RentalService.exception;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException() {
	super("The expected return date is not valid");
    }

}
