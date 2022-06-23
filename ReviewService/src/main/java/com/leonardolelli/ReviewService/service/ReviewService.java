package com.leonardolelli.ReviewService.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.ReviewService.exception.BookNotReturnedByUserException;
import com.leonardolelli.ReviewService.model.Review;
import com.leonardolelli.ReviewService.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestTemplate restTemplate;

    public static final String RENT_SERVICE_URL = "http://localhost:8084";
    public static final String HAS_RETURNED = "/api/rent/has_returned";

    public List<Review> getReviewsFor(String isbn) {
	return reviewRepository.findByIsbn(isbn);
    }

    public Review insert(Review r) {
	if (!hasReturnedTheBook(r.getIsbn(), r.getUsername()))
	    throw new BookNotReturnedByUserException();
	return reviewRepository.save(r);
    }

    private boolean hasReturnedTheBook(String isbn, String username) {
	return restTemplate.getForObject(
		String.format("%s%s/%s?username=%s", RENT_SERVICE_URL, HAS_RETURNED, isbn, username), Boolean.class);
    }

    public Review update(Review r) {
	if (!reviewRepository.existsById(r.getId()))
	    throw new NoSuchElementException("The review doesn't exist");
	return reviewRepository.save(r);
    }

    public void delete(String id) {
	if (!reviewRepository.existsById(id))
	    throw new NoSuchElementException("The review doesn't exist");
	reviewRepository.deleteById(id);
    }

    public Review find(String id) {
	return reviewRepository.findById(id).orElseThrow();
    }

}
