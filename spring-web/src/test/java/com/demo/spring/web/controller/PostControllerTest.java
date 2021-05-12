package com.demo.spring.web.controller;

import com.demo.spring.web.domain.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void post() throws Exception {
        Post post = Post.builder().content("25\7").manufacturer("ff\7xx\2x").build();
//        Post post = Post.builder().content("content").manufacturer("manufacturer").build();
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(post))
        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void get() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andDo(print()).andExpect(status().isOk());
    }
}