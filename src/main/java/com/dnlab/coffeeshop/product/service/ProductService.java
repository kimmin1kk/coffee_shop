package com.dnlab.coffeeshop.product.service;

import com.dnlab.coffeeshop.product.common.Category;
import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.repository.ProductRepository;
import com.dnlab.coffeeshop.product.repository.SearchProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SearchProductRepository searchProductRepository;

    public Product findProductBySeq(Long seq) {
        return productRepository.findById(seq).orElseThrow(() -> new RuntimeException("존재하지 않는 제품입니다."));
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public List<Product> searchProductList(String keyword, Category category) {
        if (keyword != null && !keyword.isEmpty() && category != Category.ALL) {
            return searchProductRepository.findProductByNameContainingAndCategory(keyword, category);
        } else if (category != Category.ALL) {
            return searchProductRepository.findProductByCategory(category);
        } else if (keyword != null) {
            return searchProductRepository.findProductByNameContaining(keyword);
        } else {
            return productRepository.findAll();
        }
    }

}