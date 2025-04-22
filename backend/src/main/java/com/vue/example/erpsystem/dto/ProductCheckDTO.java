package com.vue.example.erpsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCheckDTO {
    private Long id;
    private LocalDateTime lastModified;
}