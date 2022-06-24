package com.leonardolelli.LibraryGateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.LibraryGateway.model.Review;
import com.leonardolelli.LibraryGateway.service.ReviewGatewayService;

@RestController
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewGatewayController {

    @Autowired
    private ReviewGatewayService reviewGatewayService;

    @GetMapping("/{id}")
    public Review details(@PathVariable(name = "id", required = true) String id) {
	return reviewGatewayService.find(id);
    }

    @GetMapping("/book/{isbn}")
    public List<Review> listReviewsFor(@PathVariable(name = "isbn", required = true) String isbn) {
	return reviewGatewayService.getReviewsFor(isbn);
    }

    @PostMapping
    public Review post(@RequestBody Review r) {
	return reviewGatewayService.insert(r);
    }

    @PutMapping
    public Review modify(@RequestBody Review r) {
	return reviewGatewayService.update(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id", required = true) String id) {
	reviewGatewayService.delete(id);
    }

}
