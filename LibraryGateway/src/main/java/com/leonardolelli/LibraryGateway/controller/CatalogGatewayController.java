package com.leonardolelli.LibraryGateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.LibraryGateway.model.Book;
import com.leonardolelli.LibraryGateway.model.Book.Genre;
import com.leonardolelli.LibraryGateway.service.CatalogGatewayService;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin
public class CatalogGatewayController {

    @Autowired
    CatalogGatewayService catalogGatewayService;

    @GetMapping("/index")
    public List<Book> getBooks() {
	return catalogGatewayService.findAll();
    }

    @GetMapping("/{isbn}")
    public Book details(@PathVariable(name = "isbn", required = true) String isbn) {
	return catalogGatewayService.find(isbn);
    }

    @GetMapping("/findAllBy")
    public List<Book> findAllBy(@RequestParam(name = "genre", required = false) Genre genre,
	    @RequestParam(name = "author", required = false) String author) {
	return catalogGatewayService.findAllBy(genre, author);

    }

}
