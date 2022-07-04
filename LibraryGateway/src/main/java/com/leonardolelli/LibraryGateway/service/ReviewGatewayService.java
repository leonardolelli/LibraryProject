package com.leonardolelli.LibraryGateway.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.LibraryGateway.exception.InvalidOperationException;
import com.leonardolelli.LibraryGateway.model.Book;
import com.leonardolelli.LibraryGateway.model.Review;
import com.leonardolelli.LibraryGateway.security.JwtHelper;

@Service
public class ReviewGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    public static final String REVIEW_SERVICE_URL = "http://host.docker.internal:8085/api/review";

    public static final String LIST_REVIEW = "/book";
    public static final String LIST_REVIEW_USER = "/user";

    public List<Review> getReviewsFor(String isbn) {
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", REVIEW_SERVICE_URL, LIST_REVIEW, isbn),
		Review[].class));
    }

    public Review insert(Review r, String jwtToken) {
	if (!r.getUsername().equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	return restTemplate.postForObject(REVIEW_SERVICE_URL, r, Review.class);
    }

    public Review update(Review r, String jwtToken) {
	if (!r.getUsername().equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	restTemplate.put(REVIEW_SERVICE_URL, r);
	return find(r.getId());
    }

    public void delete(String id, String jwtToken) {
	Review r = find(id);
	if (!r.getUsername().equals(JwtHelper.getUserFromJwtToken(jwtToken)))
	    throw new InvalidOperationException();
	restTemplate.delete(String.format("%s/%s", REVIEW_SERVICE_URL, id));
    }

    public Review find(String id) {
	return restTemplate.getForObject(
		String.format("%s/%s", REVIEW_SERVICE_URL, id),
		Review.class);
    }

    public List<Review> getReviewsOf(String username) {
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", REVIEW_SERVICE_URL, LIST_REVIEW_USER, username),
		Review[].class));
    }

    public List<Book> getBooksReviewedBy(String username) {
	return null;
    }

}
