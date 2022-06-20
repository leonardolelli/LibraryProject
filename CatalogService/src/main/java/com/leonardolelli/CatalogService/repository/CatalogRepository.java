package com.leonardolelli.CatalogService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.leonardolelli.CatalogService.model.Book;
import com.leonardolelli.CatalogService.model.Book.Genre;

public interface CatalogRepository extends MongoRepository<Book, String> {
	@Query("{genre : ?0}")
	List<Book> findAllByGenre(Genre genre);

	@Query("{author : ?0}")
	List<Book> findAllByAuthor(String author);

	@Query("{$and:[{genre: ?0},{author: ?1}]}")
	List<Book> findAllByGenreAndAuthor(Genre genre, String author);
}