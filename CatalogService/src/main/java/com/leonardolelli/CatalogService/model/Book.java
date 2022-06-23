package com.leonardolelli.CatalogService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "catalog")
public class Book {

    @Id
    private String isbn;
    private String title;
    private String author;
    private Integer year;
    private Genre genre;

    public enum Genre {
	fantasy, scifi, thriller, horror, commedy, drama
    }

}
