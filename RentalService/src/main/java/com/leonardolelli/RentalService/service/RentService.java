package com.leonardolelli.RentalService.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.RentalService.exception.BookAlreadyReturnedException;
import com.leonardolelli.RentalService.exception.BookNotAvailableException;
import com.leonardolelli.RentalService.model.Rent;
import com.leonardolelli.RentalService.repository.RentRepository;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private RestTemplate restTemplate;

    public static final String CATALOG_SERVICE_URL = "http://localhost:8083";
    public static final String IS_IN_LIBRARY = "/api/catalog/isInLibrary";

    public Rent insert(Rent rent) {
	// check if the isbn is of a book in the library
	if (!restTemplate.getForObject(String.format("%s%s/%s", CATALOG_SERVICE_URL, IS_IN_LIBRARY, rent.getIsbn()),
		Boolean.class))
	    throw new NoSuchElementException("Book not in catalog");

	if (!isAvailable(rent.getIsbn()))
	    throw new BookNotAvailableException();
	return rentRepository.save(rent);
    }

    public boolean isAvailable(String isbn) {
	return rentRepository.findFirstByIsbnAndReturnDateIsNull(isbn).isEmpty();

    }

    public Rent returnABook(String isbn) {
	Optional<Rent> r = rentRepository.findFirstByIsbnAndReturnDateIsNull(isbn);
	if (r.isEmpty())
	    throw new BookAlreadyReturnedException();
	Rent res = r.get();
	res.setReturnDate(LocalDate.now());
	return rentRepository.save(res);
    }

    public Rent findById(Integer id) {
	return rentRepository.findById(id).orElseThrow();
    }
}