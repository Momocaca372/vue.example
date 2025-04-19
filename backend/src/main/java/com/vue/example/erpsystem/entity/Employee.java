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
    
}
