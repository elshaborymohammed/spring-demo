package com.demo.spring.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = APIController.class)
class APIControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void post() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void put() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void patch() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void get() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}