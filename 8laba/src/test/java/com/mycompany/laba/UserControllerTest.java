package com.mycompany.laba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.laba.controller.UserController;
import com.mycompany.laba.exception.ResourceNotFoundException;
import com.mycompany.laba.model.User;
import com.mycompany.laba.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_ReturnsCreated() throws Exception {
        User user = new User("alice", "alice@example.com");
        user.setId(1L);
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("alice"));
    }

    @Test
    void getUserById_ReturnsUser() throws Exception {
        User user = new User("bob", "bob@example.com");
        user.setId(2L);
        when(userService.getUserById(2L)).thenReturn(user);

        mockMvc.perform(get("/api/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("bob"));
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() throws Exception {
        User updated = new User("updatedUser", "updated@example.com");
        updated.setId(1L);
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updated);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "updatedUser",
                                    "email": "updated@example.com"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedUser"));
    }

    @Test
    void deleteUser_NotFound_Returns404() throws Exception {
        doThrow(new ResourceNotFoundException("User not found")).when(userService).deleteUser(99L);
        mockMvc.perform(delete("/api/users/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_InvalidData_Returns400() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "",
                                    "email": "wrong"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}