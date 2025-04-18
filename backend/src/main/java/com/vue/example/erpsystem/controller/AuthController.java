package com.vue.example.erpsystem.controller;

import com.vue.example.erpsystem.entity.Employee;
import com.vue.example.erpsystem.repository.EmployeeRepository;
import com.vue.example.erpsystem.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import jakarta.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmployeeRepository repository;
    
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO dto, BindingResult result) {

    	if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new RegisterResponse(result.getFieldError().getDefaultMessage()) );
        }
        
    	if (repository.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.ok().body(new RegisterResponse("帳號已存在"));
        }

        Employee employee = new Employee();
        employee.setUsername(dto.getUsername());
        employee.setPassword(dto.getPassword());
        repository.save(employee);
        return ResponseEntity.ok().body(new RegisterResponse("註冊成功"));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RegisterDTO dto) {
        Optional<Employee> optional = repository.findByUsername(dto.getUsername());
        if (dto.getUsername()!= "" && dto.getUsername()!= null && optional.isPresent() && optional.get().getPassword().equals(dto.getPassword())) {
            return ResponseEntity.ok().body(new RegisterResponse("登入成功"));
        }
        return ResponseEntity.ok().body(new RegisterResponse("帳號或密碼錯誤"));
    }
    // 定義註冊結果的回應物件
    public static class RegisterResponse {
        private String message;

        // 構造函數
        public RegisterResponse(String message) {
            this.message = message;
        }

        // Getter 和 Setter
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
