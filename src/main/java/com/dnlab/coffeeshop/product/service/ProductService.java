package com.dnlab.coffeeshop.product.service;

import com.dnlab.coffeeshop.product.common.Category;
import com.dnlab.coffeeshop.product.common.ProductAddForm;
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

    public void processingAddProduct(ProductAddForm productAddForm) {

        // 음식 담을 때 레시피 바로 추가할 수 있게 하려면 여기에 구현하면 될 듯

        productRepository.save(productAddForm.addProduct());
    }

    public Product findProductBySeq(Long seq) {
        return productRepository.findById(seq).orElseThrow(() -> new RuntimeException("존재하지 않는 제품입니다."));
    }

    public List<Product> getProductList() {
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