package com.dnlab.coffeeshop.supllier.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
    private Timestamp dueDate;

    private Timestamp actualDueDate;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "supplier_seq")
    private Supplier supplier;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supply", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SupplyContent> supplyContentList;


}
