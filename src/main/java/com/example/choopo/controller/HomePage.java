package com.example.choopo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePage {

    @RequestMapping("/")
    String home() {
        return "Choopoo BacEnd Developer !";
    }
}
