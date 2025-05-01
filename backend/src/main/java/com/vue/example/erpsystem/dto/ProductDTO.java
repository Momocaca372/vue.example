
package com.vue.example.erpsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.math.BigDecimal;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private String status;
    private LocalDateTime lastModified;
    private List<Long> tagIds;
}