package com.dnlab.coffeeshop.user.common;

import com.dnlab.coffeeshop.user.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setNickname(nickname);
        user.setPhoneNumber(phoneNumber);
        user.setEnabled(true);

        return user;
    }
}
