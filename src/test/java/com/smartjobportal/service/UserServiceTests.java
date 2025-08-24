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
}
