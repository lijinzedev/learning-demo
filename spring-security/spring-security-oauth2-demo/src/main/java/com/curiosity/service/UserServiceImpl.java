package com.curiosity.service;

import com.curiosity.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: Curiosity
 * @Date: 2021/4/7 22:06
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("admin", encoder.encode("123456"), AuthorityUtils.createAuthorityList("admin"));
    }
}
