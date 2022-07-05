package com.leonardolelli.RentalService.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.RentalService.model.Rent;
import com.leonardolelli.RentalService.service.RentService;

@RestController
@RequestMapping("/api/rent")
@CrossOrigin
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping("/isAvailable")
    public boolean isAvailable(@RequestParam(name = "isbn", required = true) String isbn) {
	return rentService.isAvailable(isbn);
    }

    @GetMapping("/{id}")
    public Rent details(@PathVariable(name = "id", required = true) Integer id) {
	return rentService.find(id);
    }

    @GetMapping("/isbn/{isbn}")
    public Rent findByIsbnNotReturnedYet(@PathVariable(name = "isbn", required = true) String isbn) {
	return rentService.findByIsbnNotReturnedYet(isbn);
    }

    @PostMapping
    public Rent rentABook(@RequestBody Rent rent) {
	return rentService.rentABook(rent);
    }

    @PatchMapping("/{isbn}")
    public Rent returnABook(@RequestBody(required = false) Rent rent,
	    @PathVariable(name = "isbn", required = true) String isbn) {
	return rentService.returnABook(isbn);
    }

    @GetMapping("/pending_rents/{username}")
    public List<Rent> getPendingRentsForUser(@PathVariable(name = "username", required = true) String username) {
	return rentService.findAllPendingRentsFor(username);
    }

    @GetMapping("/completed_rents/{username}")
    public List<Rent> getCompletedRentsForUser(@PathVariable(name = "username", required = true) String username) {
	return rentService.findAllCompletedRentsFor(username);
    }

    @GetMapping("/has_returned/{isbn}")
    public boolean hasReturnedTheBook(@PathVariable(name = "isbn", required = true) String isbn,
	    @RequestParam(name = "username", required = true) String username) {
	return rentService.hasReturnedTheBook(isbn, username);
    }

    @GetMapping("/expected_return_date/{isbn}")
    public LocalDate getExpectedReturnDate(@PathVariable(name = "isbn", required = true) String isbn) {
	return rentService.getExpectedReturnDate(isbn);
    }

}
