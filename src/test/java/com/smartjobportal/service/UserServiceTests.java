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
    @Test
    public void testtoupdate(){
        User oldUser = new User();
        oldUser.setId(1L);
        oldUser.setName("MAHI");
        oldUser.setEmail("old@email.com");
        oldUser.setRole(User.Role.JOB_SEEKER);


        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("BHOLA");
        updatedUser.setEmail("new@email.com");
        updatedUser.setRole(User.Role.EMPLOYER);

        when(userRepository.findById(1L)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result =userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("BHOLA", result.getName());
        assertEquals("new@email.com", result.getEmail());
        assertEquals(User.Role.EMPLOYER, result.getRole());

    }
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("RAHUL");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertEquals(1L, savedUser.getId());
        assertEquals("RAHUL", savedUser.getName());
    }



}
