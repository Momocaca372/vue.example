package com.vue.example.erpsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
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
   	
        Map<String, String> response = new HashMap<>();
        response.put("headerName", token.getHeaderName()); // 通常是 X-CSRF-TOKEN
        response.put("token", token.getToken());
        return ResponseEntity.ok(response);
    }
}
