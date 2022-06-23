package com.leonardolelli.LibraryGateway.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.LibraryGateway.model.Review;

@Service
public class ReviewGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    public static final String REVIEW_SERVICE_URL = "http://localhost:8085/api/review";

    public static final String LIST_REVIEW = "/book";

    public List<Review> getReviewsFor(String isbn) {
	return Arrays.asList(restTemplate.getForObject(
		String.format("%s%s/%s", REVIEW_SERVICE_URL, LIST_REVIEW, isbn),
		Review[].class));
    }

    public Review insert(Review r) {
	return restTemplate.postForObject(REVIEW_SERVICE_URL, r, Review.class);
    }

    public Review update(Review r) {
	restTemplate.put(REVIEW_SERVICE_URL, r);
	return find(r.getId());
    }

    public void delete(String id) {
	restTemplate.delete(String.format("%s/%s", REVIEW_SERVICE_URL, id));
    }

    public Review find(String id) {
	return restTemplate.getForObject(
		String.format("%s/%s", REVIEW_SERVICE_URL, id),
		Review.class);
    }

}
