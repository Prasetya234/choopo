package com.example.choopo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageHome {

    @GetMapping("/")
    public String index() {
        return "Choopoo BackEnd";
    }
}
