package com.leonardolelli.CatalogService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.CatalogService.exception.GenreNotValidException;
import com.leonardolelli.CatalogService.model.Book;
import com.leonardolelli.CatalogService.model.Book.Genre;
import com.leonardolelli.CatalogService.service.CatalogService;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping("/index")
    public List<Book> getBooks() {
	return catalogService.findAll();
    }

    @GetMapping("/{isbn}")
    public Book details(@PathVariable(name = "isbn", required = true) String isbn) {
	return catalogService.find(isbn);
    }

    @GetMapping("/isInLibrary/{isbn}")
    public Boolean isInLibrary(@PathVariable(name = "isbn", required = true) String isbn) {
	return catalogService.isInLibrary(isbn);
    }

    @GetMapping("/findAllBy")
    public List<Book> findAllBy(@RequestParam(name = "genre", required = false) String genre,
	    @RequestParam(name = "author", required = false) String author) {
	if (genre == null)
	    return catalogService.findAllBy(null, author);

	try {
	    Genre g = Genre.valueOf(genre);
	    return catalogService.findAllBy(g, author);
	} catch (IllegalArgumentException e) {
	    throw new GenreNotValidException();
	}

    }

}
