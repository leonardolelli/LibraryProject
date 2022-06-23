package com.leonardolelli.LibraryGateway.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.LibraryGateway.model.Book;
import com.leonardolelli.LibraryGateway.model.Book.Genre;

@Service
public class CatalogGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    public static final String CATALOG_SERVICE_URL = "http://localhost:8083/api/catalog";
    public static final String INDEX = "/index";
    public static final String IS_IN_LIBRARY = "/isInLibrary";
    public static final String FIND_ALL_BY = "/findAllBy";

    public List<Book> findAll() {
	return Arrays
		.asList(restTemplate.getForObject(
			String.format("%s%s", CATALOG_SERVICE_URL, INDEX),
			Book[].class));
    }

    public Book find(String isbn) {
	return restTemplate.getForObject(
		String.format("%s/%s", CATALOG_SERVICE_URL, isbn),
		Book.class);

    }

    public List<Book> findAllBy(Genre genre, String author) {
	return Arrays
		.asList(restTemplate.getForObject(
			String.format("%s%s?genre=%s&author=%s", CATALOG_SERVICE_URL, FIND_ALL_BY, genre, author),
			Book[].class));
    }

}
