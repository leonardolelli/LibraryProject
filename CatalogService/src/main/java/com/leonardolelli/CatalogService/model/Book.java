package com.leonardolelli.CatalogService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "catalog")
public class Book {

	@Id
	private String id;
	private String title;
	private String author;
	private Integer year;
	private Genre genre;
	private boolean available;

	public Book() {
	}

	public Book(String id, String title, String author, Integer year, Genre genre, boolean available) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.genre = genre;
		this.available = available;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isAvailable() {
		return available;
	}

	public void setDisponibile(boolean available) {
		this.available = available;
	}

	public enum Genre {
		fantasy, scifi, thriller, horror, commedy, drama
	}

}
