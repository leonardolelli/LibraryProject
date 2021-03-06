package com.leonardolelli.CatalogService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardolelli.CatalogService.exception.NoParametersException;
import com.leonardolelli.CatalogService.model.Book;
import com.leonardolelli.CatalogService.model.Book.Genre;
import com.leonardolelli.CatalogService.repository.CatalogRepository;

@Service
public class CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    public List<Book> findAll() {
	return catalogRepository.findAll();
    }

    public Book find(String isbn) {
	return catalogRepository.findById(isbn).orElseThrow();
    }

    public List<Book> findAllBy(Genre genre, String author) {
	List<Book> ret = new ArrayList<>();

	if (genre == null && isNullOrBlank(author))
	    throw new NoParametersException();

	else if (!(genre == null)) {
	    ret = isNullOrBlank(author) ? catalogRepository.findByGenre(genre)
		    : catalogRepository.findByGenreAndAuthor(genre, author);
	} else if (!isNullOrBlank(author))
	    ret = catalogRepository.findByAuthor(author);

	return ret;
    }

    public Boolean isInLibrary(String isbn) {
	return catalogRepository.findById(isbn).isPresent();
    }

    private boolean isNullOrBlank(String testo) {
	return testo == null || testo.isBlank() || testo.equals("null");
    }

}
