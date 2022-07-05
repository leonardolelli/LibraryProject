package com.leonardolelli.LibraryGateway.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.LibraryGateway.model.Rent;
import com.leonardolelli.LibraryGateway.service.RentGatewayService;

@RestController
@RequestMapping("/api/rent")
@CrossOrigin
public class RentGatewayController {

    @Autowired
    private RentGatewayService rentGatewayService;

    @GetMapping("/isAvailable")
    public boolean isAvailable(@RequestParam(name = "isbn", required = true) String isbn) {
	return rentGatewayService.isAvailable(isbn);
    }

    @GetMapping("/{id}")
    public Rent details(@PathVariable(name = "id", required = true) Integer id) {
	return rentGatewayService.find(id);
    }

    @PostMapping("/rentABook")
    public Rent rentABook(@RequestBody Rent rent,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {
	return rentGatewayService.rentABook(rent, jwtToken);
    }

    @PatchMapping("/{isbn}")
    public Rent returnABook(@PathVariable(name = "isbn", required = true) String isbn,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {
	return rentGatewayService.returnABook(isbn, jwtToken);
    }

    @GetMapping("/pending_rents/{username}")
    public List<Rent> getPendingRentsForUser(@PathVariable(name = "username", required = true) String username,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {

	return rentGatewayService.findAllPendingRentsFor(username, jwtToken);
    }

    @GetMapping("/completed_rents/{username}")
    public List<Rent> getCompletedRentsForUser(@PathVariable(name = "username", required = true) String username,
	    @RequestHeader("x-jwt-assertion") String jwtToken) {
	return rentGatewayService.findAllCompletedRentsFor(username, jwtToken);
    }

    @GetMapping("/expected_return_date/{isbn}")
    public LocalDate getExpectedReturnDate(@PathVariable(name = "isbn", required = true) String isbn) {
	return rentGatewayService.getExpectedReturnDate(isbn);
    }

}
