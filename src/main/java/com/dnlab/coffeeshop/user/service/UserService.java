package com.dnlab.coffeeshop.user.service;

import com.dnlab.coffeeshop.auth.domain.Authority;
import com.dnlab.coffeeshop.auth.repository.AuthorityRepository;
import com.dnlab.coffeeshop.user.common.AddAddressForm;
import com.dnlab.coffeeshop.user.common.RegistrationForm;
import com.dnlab.coffeeshop.user.common.Role;
import com.dnlab.coffeeshop.user.common.UserInformationDto;
import com.dnlab.coffeeshop.user.domain.Address;
import com.dnlab.coffeeshop.user.domain.User;
import com.dnlab.coffeeshop.user.repository.AddressRepository;
import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final AddressRepository addressRepository;

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

    @Transactional
    public void addAddressToUser(AddAddressForm addressForm, String username) {
        User user = userRepository.findByUsername(username);
        Address address = new Address(
                addressForm.getAddressName(),
                addressForm.getPostalCode(),
                addressForm.getDefaultAddress(),
                addressForm.getDetailAddress(),
                user
        );
        addressRepository.save(address);
    }


    public UserInformationDto findUserInformationByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return UserInformationDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .mileage(user.getMileage())
                .createdDate(user.getCreatedDate())
                .build();
    }

    public List<Address> findUserAddressListByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user.getAddressList().stream().toList();
    }

    public void deleteAddressBySeq(long seq) {
        addressRepository.deleteById(seq);
    }
}
