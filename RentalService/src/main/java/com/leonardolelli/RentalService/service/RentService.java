package com.leonardolelli.RentalService.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.RentalService.exception.BookNotAvailableException;
import com.leonardolelli.RentalService.exception.BookNotRentedException;
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

    public Rent rentABook(Rent rent) {
	if (!isAvailable(rent.getIsbn()))
	    throw new BookNotAvailableException();
	return rentRepository.save(rent);
    }

    public boolean isAvailable(String isbn) {
	return restTemplate.getForObject(
		String.format("%s%s/%s", CATALOG_SERVICE_URL, IS_IN_LIBRARY, isbn),
		Boolean.class)
		&& !rentRepository.existsByIsbnAndReturnDateIsNull(isbn);

    }

    public Rent returnABook(String isbn) {
	Optional<Rent> r = rentRepository.findFirstByIsbnAndReturnDateIsNull(isbn);
	if (r.isEmpty())
	    throw new BookNotRentedException();
	Rent res = r.get();
	res.setReturnDate(LocalDate.now());
	return rentRepository.save(res);
    }

    public Rent find(Integer id) {
	return rentRepository.findById(id).orElseThrow();
    }

    public List<Rent> findAllPendingRentsFor(String username) {
	return rentRepository.findByUserAndReturnDateIsNull(username);
    }

    public List<Rent> findAllCompletedRentsFor(String username) {
	return rentRepository.findByUserAndReturnDateIsNotNull(username);
    }

    public boolean hasReturnedTheBook(String isbn, String username) {
	return rentRepository.existsByIsbnAndUserAndReturnDateIsNotNull(isbn, username);
    }
}
