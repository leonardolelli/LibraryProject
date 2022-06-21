package com.leonardolelli.RentalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardolelli.RentalService.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
