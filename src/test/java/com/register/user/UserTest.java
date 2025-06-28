package com.register.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.register.user.controllers.UserController;
import com.register.user.models.User;
import com.register.user.services.UserService;

@SpringBootTest
public class UserTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    List<User> usersList = new ArrayList<>();

    @Test
    @BeforeEach
    void test_insert() {
        User newUser = new User(null, "UsuarioA", "usuariorA@email.com");

        when(userService.save(newUser)).thenReturn(newUser);

        User result = userController.insert(newUser);

        assertEquals(newUser, result);

        usersList.add(newUser);
    }

    @Test
    void test_getAll() {
        when(userService.findAll()).thenReturn(usersList);

        List<User> response = userController.getAll();

        assertEquals(1, response.size());
        verify(userService, times(1)).findAll();
    }

    @Test
    void test_update() {

        User existingUser = usersList.get(0);
        Long userID = existingUser.getId();

        User updatedUser = new User(userID, "UsuarioB", "usuarioB@example.com");

        when(userService.findById(userID)).thenReturn(Optional.of(existingUser));
        when(userService.update(userID, updatedUser)).thenReturn(Optional.of(updatedUser));

        ResponseEntity<User> response = userController.update(userID, updatedUser);

        assertEquals(ResponseEntity.ok(updatedUser), response);
        verify(userService, times(1)).update(userID, updatedUser);
    }



    @Test
    void test_delete() {
        
        User existingUser = usersList.get(0);
        Long userID = existingUser.getId();

        when(userService.findById(userID)).thenReturn(Optional.of(existingUser));
        doNothing().when(userService).deleteById(userID);

        ResponseEntity<Void> response = userController.delete(userID);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(userService, times(1)).deleteById(userID);
    }

}
