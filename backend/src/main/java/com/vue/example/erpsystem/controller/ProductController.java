package com.vue.example.erpsystem.controller;

import com.vue.example.erpsystem.mapper.ProductMapper;
import com.vue.example.erpsystem.entity.Product;
import com.vue.example.erpsystem.entity.Tag;
import com.vue.example.erpsystem.repository.ProductRepository;
import com.vue.example.erpsystem.repository.TagRepository;
import com.vue.example.erpsystem.dto.ProductDTO;
import com.vue.example.erpsystem.dto.ProductCheckDTO;
import com.vue.example.erpsystem.response.ProductSyncResponse;

import jakarta.persistence.OptimisticLockException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private TagRepository tagRepository;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        
        if (dto.getTagIds() != null && !dto.getTagIds().isEmpty()) {
            List<Tag> tags = tagRepository.findAllById(dto.getTagIds());
            product.setTags(Set.copyOf(tags));
        }

        Product saved = productRepository.save(product);
        return ResponseEntity.ok(productMapper.toDto(saved));
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<ProductDTO> modifyProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                attempt++;

                Product existingProduct = productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                // 更新名稱
                if (dto.getName() != null) {
                    existingProduct.setName(dto.getName());
                }

                // 更新價格
                if (dto.getPrice() != null) {
                    existingProduct.setPrice(dto.getPrice());
                }

                // 更新數量
                if (dto.getAmount() != null) {
                    existingProduct.setAmount(dto.getAmount());
                }

                // 更新狀態
                if (dto.getStatus() != null) {
                    existingProduct.setStatus(dto.getStatus());
                }

                // 更新 Tags（如果有提供）
                if (dto.getTagIds() != null) {
                    Set<Tag> tags = Set.copyOf(tagRepository.findAllById(dto.getTagIds()));
                    existingProduct.setTags(tags);
                }

                // 更新資料
                Product updatedProduct = productRepository.save(existingProduct);
                return ResponseEntity.ok(productMapper.toDto(updatedProduct));

            } catch (OptimisticLockException e) {
                System.err.println("OptimisticLockException 發生，嘗試次數: " + attempt);

                if (attempt >= maxRetries) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 無法處理更新
                }

                try {
                    Thread.sleep(200); // 暫停再試
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // 回復中斷狀態
                    break;
                }
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 保底
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * 同步產品資料的 API。
     * <p>
     * - 傳入空陣列或 null：回傳所有產品資料。<br>
     * - 傳入 id + lastModified：比對資料庫是否有更新，回傳異動的項目。
     *
     * @param checkList 前端傳入的產品狀態清單
     * @return 有異動的產品 DTO 清單，或全部產品
     * @throws InterruptedException 
     */
    @PostMapping("/sync")
    public ResponseEntity<ProductSyncResponse> syncProducts(@RequestBody(required = false) List<ProductCheckDTO> checkList) throws InterruptedException {

        if (checkList == null || checkList.isEmpty()) {
            // 第一次請求，回傳全部資料
            List<ProductDTO> all = productRepository.findAll().stream()
                    .map(productMapper::toDto)
                    .toList();
            return ResponseEntity.ok(new ProductSyncResponse(all, List.of()));
        }

        // 比對更新資料
        List<Long> ids = checkList.stream().map(ProductCheckDTO::getId).toList();
        List<Product> dbProducts = productRepository.findAllById(ids);

        // 找出已刪除
        Set<Long> existingIds = dbProducts.stream().map(Product::getId).collect(Collectors.toSet());
        List<Long> deleted = ids.stream()
                .filter(id -> !existingIds.contains(id))
                .toList();

        // 找出異動項目
        List<ProductDTO> updated = dbProducts.stream()
                .filter(p -> {
                    ProductCheckDTO match = checkList.stream()
                            .filter(c -> c.getId().equals(p.getId()))
                            .findFirst().orElse(null);
                    return match == null || !p.getLastModified().equals(match.getLastModified());
                })
                .map(productMapper::toDto)
                .toList();

        return ResponseEntity.ok(new ProductSyncResponse(updated, deleted));

    }
    }
