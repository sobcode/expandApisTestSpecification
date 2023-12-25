package com.app.expandapistesttask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String addUser() {
        logger.info("In test controller");
        return "Hello everyone!";
    }
}
