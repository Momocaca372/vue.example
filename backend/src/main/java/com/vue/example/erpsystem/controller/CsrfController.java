package com.vue.example.erpsystem.controller;

import jakarta.servlet.http.HttpServletResponse; // 引入 HttpServletResponse
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CsrfController {

    @GetMapping("/csrf-token")
    public ResponseEntity<Map<String, String>> getCsrfToken(CsrfToken token, HttpServletResponse response) {
        Map<String, String> csrfInfo = new HashMap<>();
        csrfInfo.put("headerName", token.getHeaderName());
        csrfInfo.put("token", token.getToken());
        System.out.println("Generated CSRF Header Token: " + token.getToken());

        // 將新的 CSRF Token 值設置到 Cookie 中
        response.setHeader("Set-Cookie", "XSRF-TOKEN=" + token.getToken() + "; Path=/; HttpOnly=false; SameSite=Lax");
        // ^^^^ 根據你的需求調整 Path、HttpOnly 和 SameSite 屬性

        return ResponseEntity.ok(csrfInfo);
    }
}