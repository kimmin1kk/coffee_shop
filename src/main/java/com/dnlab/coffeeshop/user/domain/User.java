package com.dnlab.coffeeshop.user.domain;

import com.dnlab.coffeeshop.order.domain.Orders;
import com.dnlab.coffeeshop.security.domain.Authority;
import com.dnlab.coffeeshop.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
//@EntityListeners(AuditingEntityListener.class)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long seq;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "boolean default true")
    @Builder.Default
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Orders> ordersList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "seq=" + seq +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", enabled=" + enabled +
                ", addressList=" + addressList +
                ", authorities=" + authorities +
                '}';
    }
}

