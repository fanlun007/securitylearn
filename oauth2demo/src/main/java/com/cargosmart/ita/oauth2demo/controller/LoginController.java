package com.cargosmart.ita.oauth2demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @GetMapping("/get_user/{username}")
    public String getUser(@PathVariable String username) {
        return "Hello" + username;
    }
}
