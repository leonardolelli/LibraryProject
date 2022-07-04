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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.LibraryGateway.model.Book;
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

    @GetMapping("/user/{username}")
    public List<Review> listReviewsOf(@PathVariable(name = "username", required = true) String username) {
	return reviewGatewayService.getReviewsOf(username);
    }

    @PostMapping("/post")
    public Review post(@RequestBody Review r,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {
	return reviewGatewayService.insert(r, jwtToken);
    }

    @PutMapping
    public Review modify(@RequestBody Review r,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {
	return reviewGatewayService.update(r, jwtToken);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id", required = true) String id,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {
	reviewGatewayService.delete(id, jwtToken);
    }

    @GetMapping("/reviewed_books/{username}")
    public List<Book> listBooksReviewedBy(@PathVariable(name = "username", required = true) String username) {
	return reviewGatewayService.getBooksReviewedBy(username);
    }

}
