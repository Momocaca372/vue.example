package com.vue.example.erpsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.ignoringRequestMatchers("/csrf-token") // 忽略 /auth/** 的 CSRF 保護
		)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers( "/csrf-token","/auth/**", "/favicon.ico").permitAll()
            .anyRequest().authenticated()
        ).httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 設置允許跨域的 URL 和方法
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 前端地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}