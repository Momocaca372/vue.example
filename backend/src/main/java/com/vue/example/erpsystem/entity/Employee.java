package com.vue.example.erpsystem.entity;

//這個 jakarta.persistence 是從 Java EE 過渡到 Jakarta EE 的命名空間，之前是 javax.persistence，Spring Boot 3+ 會預設用新的 jakarta 命名。
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
