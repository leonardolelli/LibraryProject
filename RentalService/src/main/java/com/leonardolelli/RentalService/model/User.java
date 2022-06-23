package com.leonardolelli.RentalService.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String name;
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public User(String username) {
	this.username = username;
    }

}
