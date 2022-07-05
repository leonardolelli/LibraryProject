package com.leonardolelli.RentalService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardolelli.RentalService.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {

    Optional<Rent> findFirstByIsbnAndReturnDateIsNull(String isbn);

    Optional<Rent> findFirstByIsbnAndReturnDateIsNotNull(String isbn);

    boolean existsByIsbnAndReturnDateIsNull(String isbn);

    @Query("select r from Rent r where r.user.username = ?1 and r.returnDate is null")
    List<Rent> findByUserAndReturnDateIsNull(String username);

    @Query("select r from Rent r where r.user.username = ?1 and r.returnDate is not null")
    List<Rent> findByUserAndReturnDateIsNotNull(String username);

    @Query("select case " + "when count(r)>0 " + "then true " + "else false end " + "from Rent r "
	    + "where r.isbn=?1 and r.user.username=?2 and r.returnDate is not null")
    boolean existsByIsbnAndUserAndReturnDateIsNotNull(String isbn, String username);
}
