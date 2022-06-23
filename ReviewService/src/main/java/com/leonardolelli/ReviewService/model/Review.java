package com.leonardolelli.ReviewService.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "reviews")

@Getter
@Setter
@AllArgsConstructor
public class Review {
    @Id
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
