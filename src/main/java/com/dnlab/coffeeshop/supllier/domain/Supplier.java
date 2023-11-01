package com.dnlab.coffeeshop.supllier.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Supplier extends BaseTimeEntity {
    //공급업체
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;
}
