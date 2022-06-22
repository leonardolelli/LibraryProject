package com.leonardolelli.RentalService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardolelli.RentalService.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {

    Optional<Rent> findFirstByIsbnAndReturnDateIsNull(String isbn);

    @Query("select r from Rent r where r.user.username = ?1 and r.returnDate is null")
    List<Rent> findByUserAndReturnDateIsNull(String username);

    @Query("select r from Rent r where r.user.username = ?1 and r.returnDate is not null")
    List<Rent> findByUserAndReturnDateIsNotNull(String username);
}
