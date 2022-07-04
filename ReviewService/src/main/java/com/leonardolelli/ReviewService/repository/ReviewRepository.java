package com.leonardolelli.ReviewService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leonardolelli.ReviewService.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

    public List<Review> findByIsbn(String isbn);

    public List<Review> findByUsername(String username);
}
