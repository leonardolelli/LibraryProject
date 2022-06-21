package com.leonardolelli.CatalogService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "catalog")
public class Book {

	@Id
	private String isbn;
	private String title;
	private String author;
	private Integer year;
	private Genre genre;

	public Book() {
	}

	public Book(String isbn, String title, String author, Integer year, Genre genre) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String id) {
		this.isbn = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGenre() {
		return genre.toString();
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public enum Genre {
		fantasy, scifi, thriller, horror, commedy, drama
	}

}
