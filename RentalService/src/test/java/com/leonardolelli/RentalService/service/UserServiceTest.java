package com.leonardolelli.RentalService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leonardolelli.RentalService.model.User;
import com.leonardolelli.RentalService.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void testFindAll() {

	List<User> userList = List.of(new User("user1", "luigi", "rossi", LocalDate.of(2000, Month.JULY, 1)),
		new User("user2", "luigi", "rossi", LocalDate.of(1990, Month.JULY, 1)));

	Mockito.when(userRepository.findAll()).thenReturn(userList);
	List<User> result = userService.findAll();
	assertEquals(result, userList);
    }

    @Test
    void testFindById() {
	String user = "user1";
	User testUser = new User("user1", "luigi", "rossi", LocalDate.of(2000, Month.JULY, 1));

	Mockito.when(userRepository.findById(user)).thenReturn(Optional.of(testUser));
	User result = userService.findById(user);
	assertEquals(result, testUser);
    }

}
