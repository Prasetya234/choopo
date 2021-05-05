package com.example.choopo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageHome {

    @GetMapping("/")
    ResponseEntity<String> customHeader() {
        return ResponseEntity.ok().body("Choopoo BackEnd Developer");
    }
}
