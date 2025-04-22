package com.vue.example.erpsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CsrfController {

    @GetMapping("/csrf-token")
    public ResponseEntity<Map<String, String>> getCsrfToken(CsrfToken token) {
        Map<String, String> csrfInfo = new HashMap<>();
        csrfInfo.put("headerName", token.getHeaderName());
        csrfInfo.put("token", token.getToken());
        //System.out.println("Generated CSRF Token Length: " + token.getToken().length());
        return ResponseEntity.ok(csrfInfo);
    }
}