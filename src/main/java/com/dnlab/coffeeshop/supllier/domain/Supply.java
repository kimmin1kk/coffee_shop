package com.dnlab.coffeeshop.supllier.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Supply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "supplier_seq")
    private Supplier supplier;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplyContent> supplyContentList = new ArrayList<>();


}
