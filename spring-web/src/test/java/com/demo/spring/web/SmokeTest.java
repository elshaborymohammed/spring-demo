package com.demo.spring.web;

import com.demo.spring.web.controller.PostController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class SmokeTest {

    @Autowired
    PostController controller;

    @Test
    void test() {
        assert controller != null;
    }
}
