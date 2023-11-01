package com.dnlab.coffeeshop.user.common;

import com.dnlab.coffeeshop.user.domain.User;
import lombok.Data;

import java.util.HashSet;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String address;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .address(address)
                .enabled(true)
                .authorities(new HashSet<>())
                .build();
    }
}
