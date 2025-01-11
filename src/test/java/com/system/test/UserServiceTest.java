package com.system.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.system.configuration.JWTHandler;
import com.system.model.UserModel;
import com.system.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    private UserService userService;

    @Mock
    private JWTHandler jwtHandler; // Mockoljuk a JWTHandler-t

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializálja a mock objektumokat
        userService = new UserService(jwtHandler); // Injektáljuk a mock JWTHandler-t
    }

    @Test
    void testLogin_ShouldReturnJwtToken() {
        // Arrange
        UserModel user = new UserModel();
        user.setName("Marin");

        // Mocking: amikor a jwtHandler.generation() metódust hívják, adjon vissza egy fake JWT-t
        when(jwtHandler.generation(user)).thenReturn("fake-jwt-token");

        // Act
        String result = userService.login(user);

        // Assert
        assertEquals("fake-jwt-token", result);
        verify(jwtHandler, times(1)).generation(user); // Ellenőrizzük, hogy egyszer hívták a metódust
    }
}
