package com.gaming.gamer;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(gamescontroller.class)
public class gamescontrollertest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private gamesservice service;

    @Test
    void shouldReturnAllgames() throws Exception {

        // Mocking gameservice response with valid games object
        when(service.findAll()).thenReturn(
                List.of(new games("1", "PUBG", 0.0, "Battle Royale game"))
        );

        // Simulate GET /games endpoint and check JSON response
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(250.00));
    }
}
