package com.leonardolelli.LibraryGateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String isbn;
    private String title;
    private String author;
    private Integer year;
    private Genre genre;

    public enum Genre {
	fantasy, scifi, thriller, horror, commedy, drama
    }

}
