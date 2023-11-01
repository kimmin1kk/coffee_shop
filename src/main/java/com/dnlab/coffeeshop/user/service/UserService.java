package com.dnlab.coffeeshop.user.service;

import com.dnlab.coffeeshop.security.domain.Authority;
import com.dnlab.coffeeshop.security.repository.AuthorityRepository;
import com.dnlab.coffeeshop.user.common.RegistrationForm;
import com.dnlab.coffeeshop.user.common.Role;
import com.dnlab.coffeeshop.user.common.UserInformationDto;
import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Transactional
    public void processRegistration(RegistrationForm form) {
        log.info("UserService -> processRegistration : OK");
        var auth = new Authority();
        auth.setRole(Role.ROLE_USER);

        var newUser = form.toUser();
        auth.setUser(newUser);

        var savedUser = userRepository.save(newUser);
        savedUser.getAuthorities().add(auth);

        authorityRepository.save(auth);
    }



    public UserInformationDto findUserInformationByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return UserInformationDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .createdDate(user.getCreatedDate())
                .build();
    }

}
