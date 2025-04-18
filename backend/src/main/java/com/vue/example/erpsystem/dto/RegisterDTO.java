package com.vue.example.erpsystem.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class RegisterDTO {
	@NotBlank(message = "帳號不能為空")
    private String username;
	
	@NotBlank(message = "密碼不能為空")
    private String password;
}