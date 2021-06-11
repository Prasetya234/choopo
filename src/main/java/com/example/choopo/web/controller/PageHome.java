package com.example.choopo.web.controller;

import com.example.choopo.web.service.PageHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageHome {

    @Autowired PageHomeService pageHomeService;

    @PreAuthorize("permitAll()")
    @GetMapping
    ResponseEntity<String> customHeader() {
        return pageHomeService.customHeader();
    }
}
