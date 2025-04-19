package com.vue.example.erpsystem.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class RegisterDTO {
	@NotBlank(message = "帳號不能為空")
    private String username;
	
	@NotBlank(message = "密碼不能為空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "密碼格式錯誤，需包含大小寫英數字且長度至少8位")
    private String password;
}