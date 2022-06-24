package com.leonardolelli.LibraryGateway.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private String id;
    private String isbn;
    private String username;
    private String text;
    private Integer score;
    private LocalDateTime lastUpdate;

    public Review() {
	this.lastUpdate = LocalDateTime.now();
    }

}
