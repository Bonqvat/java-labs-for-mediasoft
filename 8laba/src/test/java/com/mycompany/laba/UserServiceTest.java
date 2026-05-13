package com.mycompany.laba;

import com.mycompany.laba.exception.ResourceNotFoundException;
import com.mycompany.laba.exception.ValidationException;
import com.mycompany.laba.model.User;
import com.mycompany.laba.repository.UserRepository;
import com.mycompany.laba.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("john_doe", "john@example.com");
        user.setId(1L);
    }

    @Test
    void createUser_Success() {
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User created = userService.createUser(user);
        assertNotNull(created);
        assertEquals("john_doe", created.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_DuplicateUsername_ThrowsException() {
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        assertThrows(ValidationException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void getUserById_NotFound_ThrowsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99L));
    }

    @Test
    void deleteUser_NotFound_ThrowsException() {
        when(userRepository.existsById(99L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(99L));
    }
}