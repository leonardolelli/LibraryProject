package com.leonardolelli.LibraryGateway.controller;

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

    @PostMapping
    public Rent rentABook(@RequestBody Rent rent) {
	return rentGatewayService.rentABook(rent);
    }

    @PatchMapping("/{isbn}")
    public Rent returnABook(@PathVariable(name = "isbn", required = true) String isbn) {
	return rentGatewayService.returnABook(isbn);
    }

    @GetMapping("/pending_rents/{username}")
    public List<Rent> getPendingRentsForUser(@PathVariable(name = "username", required = true) String username) {
	return rentGatewayService.findAllPendingRentsFor(username);
    }

    @GetMapping("/completed_rents/{username}")
    public List<Rent> getCompletedRentsForUser(@PathVariable(name = "username", required = true) String username) {
	return rentGatewayService.findAllCompletedRentsFor(username);
    }

}
