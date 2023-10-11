package com.dnlab.coffeeshop.user.common;

import com.dnlab.coffeeshop.user.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String nickname;
    private String name;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setName(name);
        user.setMileage(0);
        user.setEnabled(true);

        return user;
    }
}
