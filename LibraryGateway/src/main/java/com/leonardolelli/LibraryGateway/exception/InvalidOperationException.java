package com.leonardolelli.LibraryGateway.exception;

public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException() {
	super("You cannot perform this action");
    }

}
