package com.demo.spring.web.security;

import com.demo.spring.web.domain.Role;
import com.demo.spring.web.domain.User;
import com.demo.spring.web.repository.RoleRepository;
import com.demo.spring.web.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserPrincipalService(UserRepository repository, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
    }

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
