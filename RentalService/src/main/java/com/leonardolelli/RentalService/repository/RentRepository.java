package com.leonardolelli.RentalService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardolelli.RentalService.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {

    Optional<Rent> findFirstByIsbnAndReturnDateIsNull(String isbn);
}
