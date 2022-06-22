package com.leonardolelli.RentalService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardolelli.RentalService.model.User;
import com.leonardolelli.RentalService.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public List<User> getUsers() {
	return userService.findAll();
    }

    @GetMapping("/{username}")
    public User details(@PathVariable(name = "username", required = true) String username) {
	return userService.findById(username);
    }

}
