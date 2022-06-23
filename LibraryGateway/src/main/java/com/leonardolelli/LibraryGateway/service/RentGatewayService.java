package com.leonardolelli.LibraryGateway.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.LibraryGateway.model.Rent;

@Service
public class RentGatewayService {

    public static final String RENT_SERVICE_URL = "http://localhost:8084/api/rent";
    public static final String IS_AVAILABLE = "/isAvailable";
    public static final String PENDING_RENTS = "/pending_rents";
    public static final String COMPLETED_RENTS = "/completed_rents";

    @Autowired
    private RestTemplate restTemplate;

    public Rent rentABook(Rent rent) {
	return restTemplate.postForObject(RENT_SERVICE_URL, rent, Rent.class);
    }

    public boolean isAvailable(String isbn) {
	return restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, IS_AVAILABLE, isbn),
		Boolean.class);
    }

    public Rent returnABook(String isbn) {

	return restTemplate.patchForObject(
		String.format("%s/%s", RENT_SERVICE_URL, isbn),
		null,
		Rent.class);
    }

    public Rent find(Integer id) {
	return restTemplate.getForObject(
		String.format("%s/%s", RENT_SERVICE_URL, id),
		Rent.class);
    }

    public List<Rent> findAllPendingRentsFor(String username) {
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, PENDING_RENTS, username),
		Rent[].class));
    }

    public List<Rent> findAllCompletedRentsFor(String username) {
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", RENT_SERVICE_URL, COMPLETED_RENTS, username),
		Rent[].class));
    }

}
