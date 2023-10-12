package com.dnlab.coffeeshop.user.service;

import com.dnlab.coffeeshop.user.domain.User;
import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsServiceImpl -> loadUserByUsername : OK");
        User user = userRepository.findByUsername(username);
        log.info("user is " + user.toString());
        return new UserDetailsImpl(user, user.getAuthorities());
    }
}
