package com.leonardolelli.ReviewService.exception;

public class BookNotReturnedByUserException extends RuntimeException {

    public BookNotReturnedByUserException() {
	super("This user didn't read this book");
    }
}
