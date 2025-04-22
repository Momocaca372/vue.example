package com.vue.example.erpsystem.controller;

import com.vue.example.erpsystem.entity.Tag;
import com.vue.example.erpsystem.entity.Product;
import com.vue.example.erpsystem.repository.TagRepository;

import jakarta.persistence.EntityNotFoundException;

import com.vue.example.erpsystem.repository.ProductRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tag")
class TagController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @PostMapping("/create")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag saved = tagRepository.save(tag);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Tag> modifyTag(@PathVariable Long id, @RequestBody Tag updatedTag) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));
    	List<Product> products = tag.getProducts();
        for (Product p : products) {
            p.setLastModified(LocalDateTime.now());
        }
        tag.setName(updatedTag.getName());
        Tag saved = tagRepository.save(tag);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
    	
    	if (!tagRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
    	Tag tag = tagRepository.findById(id).orElseThrow();
    	
    	List<Product> products = tag.getProducts();
        for (Product p : products) {
        	p.getTags().remove(tag);  // 移除關聯
            p.setLastModified(LocalDateTime.now());
        }
        productRepository.saveAll(products);
        return ResponseEntity.noContent().build();
    }

}