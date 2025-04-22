package com.vue.example.erpsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vue.example.erpsystem.entity.Tag;



public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name); // 可用於新增時避免重複
}