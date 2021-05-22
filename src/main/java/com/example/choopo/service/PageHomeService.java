package com.example.choopo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PageHomeService {

    public ResponseEntity<String> customHeader() {
        return ResponseEntity.ok().body("Choopoo BackEnd Developer");
    }
}
