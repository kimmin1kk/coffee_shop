package com.dnlab.coffeeshop.product.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.product.common.Category;
import com.dnlab.coffeeshop.product.common.ProductEditForm;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer price;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Recipe> recipeList;

    public void updateProduct(ProductEditForm productEditForm) {
        this.name = productEditForm.getName();
        this.price = productEditForm.getPrice();
        this.category = productEditForm.getCategory();
    }

//    public Product updateProduct(ProductEditForm productEditForm) {
//        return this.toBuilder()
//                .name(productEditForm.getName())
//                .price(productEditForm.getPrice())
//                .category(productEditForm.getCategory())
//                .build();
//    }

}
