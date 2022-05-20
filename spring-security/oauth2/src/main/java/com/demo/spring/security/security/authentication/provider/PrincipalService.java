package com.demo.spring.security.security.authentication.provider;

import com.demo.spring.security.domain.User;
import com.demo.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public Principal loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(s);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("Wrong User!");
        }
        log.info("user: {}", byUsername.get());
        return byUsername.map(Principal::new).get();
    }
}
