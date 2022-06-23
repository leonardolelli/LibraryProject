package com.leonardolelli.ReviewService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.ReviewService.model.Review;
import com.leonardolelli.ReviewService.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public Review details(@PathVariable(name = "id", required = true) String id) {
	return reviewService.find(id);
    }

    @GetMapping("/book/{isbn}")
    public List<Review> listReviewsFor(@PathVariable(name = "isbn", required = true) String isbn) {
	return reviewService.getReviewsFor(isbn);
    }

    @PostMapping
    public Review post(@RequestBody Review r) {
	return reviewService.insert(r);
    }

    @PutMapping
    public Review modify(@RequestBody Review r) {
	return reviewService.update(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id", required = true) String id) {
	reviewService.delete(id);
    }

}
