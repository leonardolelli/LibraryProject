package com.leonardolelli.RentalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.RentalService.model.Rent;
import com.leonardolelli.RentalService.service.RentService;

@RestController
@RequestMapping("/api/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping("/isAvailable")
    public boolean isAvailable(@RequestParam(name = "isbn", required = true) String isbn) {
	return rentService.isAvailable(isbn);
    }

    @GetMapping("/{id}")
    public Rent details(@PathVariable(name = "id", required = true) Integer id) {
	return rentService.findById(id);
    }

    @PostMapping
    public Rent rentABook(@RequestBody Rent rent) {
	return rentService.insert(rent);
    }

    @PutMapping("/{isbn}")
    public Rent returnABook(@PathVariable(name = "isbn", required = true) String isbn) {
	return rentService.returnABook(isbn);
    }

}
