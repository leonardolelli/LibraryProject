package com.leonardolelli.RentalService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.leonardolelli.RentalService.exception.BookNotAvailableException;
import com.leonardolelli.RentalService.exception.BookNotRentedException;
import com.leonardolelli.RentalService.model.Rent;
import com.leonardolelli.RentalService.model.User;
import com.leonardolelli.RentalService.repository.RentRepository;

@ExtendWith(MockitoExtension.class)
class RentServiceTest {

    @InjectMocks
    private RentService rentService;
    @Mock
    private RentRepository rentRepository;
    @Mock
    private RestTemplate restTemplate;

    @Test
    void testRentABook() {
	Integer id = 1;
	String isbn = "isbn1";
	Rent r = new Rent(id, isbn, LocalDate.of(2020, Month.JULY, 1),
		new User("user", "luigi", "rossi", LocalDate.of(2000, Month.JULY, 1)));
	Mockito.when(rentRepository.findFirstByIsbnAndReturnDateIsNull(isbn)).thenReturn(Optional.empty());
	Mockito.when(rentRepository.save(r)).thenReturn(r);
	Mockito.when(restTemplate.getForObject(anyString(), eq(Boolean.class))).thenReturn(true);
	Rent res = rentService.rentABook(r);
	assertEquals(r, res);
    }

    @Test
    void testIsAvailable() {
	Integer id = 1;
	String isbn = "isbn1";
	Rent r = new Rent(id, isbn, LocalDate.of(2020, Month.JULY, 1),
		new User("user", "luigi", "rossi", LocalDate.of(2000, Month.JULY, 1)));
	r.setReturnDate(LocalDate.now());
	Mockito.when(rentRepository.findFirstByIsbnAndReturnDateIsNull(isbn)).thenReturn(Optional.of(r));
	Mockito.when(restTemplate.getForObject(anyString(), eq(Boolean.class))).thenReturn(true);
	assertFalse(rentService.isAvailable(isbn));
    }

    @Test
    void testReturnABook() {
	Integer id = 1;
	String isbn = "isbn1";
	Rent r = new Rent(id, isbn, LocalDate.of(2020, Month.JULY, 1),
		new User("user", "luigi", "rossi", LocalDate.of(2000, Month.JULY, 1)));
	Mockito.when(rentRepository.findFirstByIsbnAndReturnDateIsNull(isbn)).thenReturn(Optional.of(r));
	Mockito.when(rentRepository.save(r)).thenReturn(r);
	Rent res = rentService.returnABook(isbn);
	assertTrue(res.getReturnDate().isEqual(LocalDate.now()));
    }

    @Test
    void testFindById() {
	Integer id = 1;
	Rent r = new Rent(id, "isbn1", LocalDate.of(2020, Month.JULY, 1),
		new User("user", "luigi", "rossi", LocalDate.of(2000, Month.JULY, 1)));
	Mockito.when(rentRepository.findById(id)).thenReturn(Optional.of(r));
	Rent res = rentService.find(id);
	assertEquals(res, r);
    }

    @Test
    void testBookNotAvailableException() {
	Mockito.when(restTemplate.getForObject(anyString(), eq(Boolean.class))).thenReturn(false);
	assertThrows(BookNotAvailableException.class, () -> rentService.rentABook(new Rent()));
    }

    @Test
    void testBookNotRentedException() {
	Mockito.when(rentRepository.findFirstByIsbnAndReturnDateIsNull(anyString())).thenReturn(Optional.empty());
	assertThrows(BookNotRentedException.class, () -> rentService.returnABook(""));
    }

}
