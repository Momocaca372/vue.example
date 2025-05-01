package com.vue.example.erpsystem.mapper;

import com.vue.example.erpsystem.dto.ProductDTO;
import com.vue.example.erpsystem.entity.Product;
import com.vue.example.erpsystem.entity.Tag;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        List<Long> tagIds = product.getTags() == null ? List.of() :
                product.getTags().stream().map(Tag::getId).collect(Collectors.toList());

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAmount(),
                product.getStatus(),
                product.getLastModified(),
                tagIds
        );
    }

	public Product toEntity(ProductDTO dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setAmount(dto.getAmount());
		product.setStatus(dto.getStatus());

		return product;
	}

 }
