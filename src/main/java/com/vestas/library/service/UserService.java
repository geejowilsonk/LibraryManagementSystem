package com.vestas.library.service;

import com.vestas.library.model.Users;
import com.vestas.library.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // Inject UserRepository through constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Loads a user by username from the database for authentication
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Fetch user details from the database
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Construct and return a UserDetails object used by Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().replace("ROLE_", "")) // removes "ROLE_" prefix as Spring Security adds it automatically
                .build();
    }
}
