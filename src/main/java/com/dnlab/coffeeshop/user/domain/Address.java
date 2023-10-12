package com.dnlab.coffeeshop.user.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(nullable = false)
    private String addressName;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String defaultAddress;
    @Column(nullable = false)
    private String detailAddress;
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;
    @ManyToOne
    private User user;


    public Address() {
    }

    public Address(String addressName, String postalCode, String defaultAddress, String detailAddress, User user) {
        this.addressName = addressName;
        this.postalCode = postalCode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
        this.user = user;
    }
}
