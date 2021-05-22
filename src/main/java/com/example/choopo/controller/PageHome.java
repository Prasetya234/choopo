package com.example.choopo.controller;

import com.example.choopo.service.PageHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageHome {

    @Autowired PageHomeService pageHomeService;

    @GetMapping
    ResponseEntity<String> customHeader() {
        return pageHomeService.customHeader();
    }
}
