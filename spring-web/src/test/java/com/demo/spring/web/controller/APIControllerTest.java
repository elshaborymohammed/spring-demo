package com.demo.spring.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void post() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void put() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void patch() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}