package com.leonardolelli.LibraryGateway.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rent {

    private Integer id;
    private String isbn;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate returnDate;
    private User user;

    public Rent() {
	this.fromDate = LocalDate.now();
	this.returnDate = null;
    }

    public Rent(Integer id, String isbn, LocalDate toDate, User user) {
	this.id = id;
	this.isbn = isbn;
	this.toDate = toDate;
	this.user = user;
	this.fromDate = LocalDate.now();
	this.returnDate = null;
    }

}
