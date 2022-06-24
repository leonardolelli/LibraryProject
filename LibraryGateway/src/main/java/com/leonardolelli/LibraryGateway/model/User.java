package com.leonardolelli.LibraryGateway.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    public User(String username) {
	this.username = username;
    }

}
