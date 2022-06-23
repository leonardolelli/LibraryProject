package com.leonardolelli.CatalogService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leonardolelli.CatalogService.model.Book;
import com.leonardolelli.CatalogService.model.Book.Genre;

public interface CatalogRepository extends MongoRepository<Book, String> {

    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthor(String author);

    List<Book> findByGenreAndAuthor(Genre genre, String author);
}