package com.smartjobportal.service;

import com.smartjobportal.model.User;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("JOGINDER");
        user.setEmail("joginder@EMAIL.COM");
        user.setRole(User.Role.EMPLOYER);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        // Assert
        assertNotNull(foundUser);
        assertEquals("JOGINDER", foundUser.getName());
        assertEquals("joginder@EMAIL.COM", foundUser.getEmail());
        assertEquals(User.Role.EMPLOYER, foundUser.getRole());
    }
    @Test
    public void getAllUsers() {
        User u1 = new User(1L, "Pritam", "pritam@gmail.com", User.Role.EMPLOYER);
        User u2 = new User(2L, "Raj", "raj@gmail.com", User.Role.EMPLOYER);

        when(userRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<User> users = userService.getAllUsers();


        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Pritam", users.get(0).getName());

    }


}
