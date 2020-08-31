package com.dev.springdemo.auth.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "users")
@Log4j2
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Cacheable(unless = "#result == null")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Fetching user details for {}", username);
        Optional<User> userByEmail = userRepository.findUserByEmail(username);
        return userByEmail.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
