package com.leonardolelli.LibraryGateway.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.LibraryGateway.exception.InvalidOperationException;
import com.leonardolelli.LibraryGateway.model.Rent;
import com.leonardolelli.LibraryGateway.security.JwtHelper;

@Service
public class RentGatewayService {

    public static final String RENT_SERVICE_URL = "http://host.docker.internal:8084/api/rent";
    public static final String IS_AVAILABLE = "/isAvailable";
    public static final String PENDING_RENTS = "/pending_rents";
    public static final String COMPLETED_RENTS = "/completed_rents";
    public static final String EXP_RET_DATE = "/expected_return_date";
    public static final String ISBN = "/isbn";

    @Autowired
    private RestTemplate restTemplate;

    public Rent rentABook(Rent rent, String jwtToken) {
	if (!rent.getUser().getUsername().equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	return restTemplate.postForObject(RENT_SERVICE_URL, rent, Rent.class);
    }

    public boolean isAvailable(String isbn) {
	return restTemplate.getForObject(
		String.format("%s%s?isbn=%s", RENT_SERVICE_URL, IS_AVAILABLE, isbn),
		Boolean.class);
    }

    public Rent returnABook(String isbn, String jwtToken) {
	Rent r = findByIsbn(isbn);
	if (!r.getUser().getUsername().equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	return restTemplate.patchForObject(
		String.format("%s/%s", RENT_SERVICE_URL, isbn),
		null,
		Rent.class);
    }

    public Rent findByIsbn(String isbn) {
	return restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, ISBN, isbn),
		Rent.class);
    }

    public Rent find(Integer id) {
	return restTemplate.getForObject(
		String.format("%s/%s", RENT_SERVICE_URL, id),
		Rent.class);
    }

    public List<Rent> findAllPendingRentsFor(String username, String jwtToken) {
	if (!username.equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, PENDING_RENTS, username),
		Rent[].class));
    }

    public List<Rent> findAllCompletedRentsFor(String username, String jwtToken) {
	if (!username.equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, COMPLETED_RENTS, username),
		Rent[].class));
    }

    public LocalDate getExpectedReturnDate(String isbn) {
	return restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, EXP_RET_DATE, isbn),
		LocalDate.class);
    }

}
