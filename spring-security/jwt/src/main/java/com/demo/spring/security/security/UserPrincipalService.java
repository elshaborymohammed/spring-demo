package com.demo.spring.security.security;

import com.demo.spring.security.domain.Role;
import com.demo.spring.security.domain.User;
import com.demo.spring.security.repository.RoleRepository;
import com.demo.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(s);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("");
        }

        Set<Role> roles = roleRepository.findByUsername(s);
        log.info("username {}", byUsername.get());
        return byUsername.map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet())
        )).get();
    }
}
