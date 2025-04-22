package com.vue.example.erpsystem.response;

import java.util.List;

import com.vue.example.erpsystem.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSyncResponse {
    private List<ProductDTO> updated;
    private List<Long> deleted;
}