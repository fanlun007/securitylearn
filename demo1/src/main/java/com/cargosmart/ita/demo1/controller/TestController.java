package com.cargosmart.ita.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @GetMapping("/get_user/{username}")
    public String getUser(@PathVariable String username) {
        return "Hello, " + username;
    }
}
