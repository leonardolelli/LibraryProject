package com.leonardolelli.RentalService.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "username")
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
