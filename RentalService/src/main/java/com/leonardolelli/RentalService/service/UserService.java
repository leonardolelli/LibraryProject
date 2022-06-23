package com.leonardolelli.RentalService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardolelli.RentalService.model.User;
import com.leonardolelli.RentalService.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
	return userRepository.findAll();
    }

    public User findById(String username) {
	return userRepository.findById(username).orElseThrow();
    }
}
