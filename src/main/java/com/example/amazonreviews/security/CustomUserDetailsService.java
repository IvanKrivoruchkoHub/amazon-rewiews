package com.example.amazonreviews.security;


import com.example.amazonreviews.entity.Role;
import com.example.amazonreviews.entity.User;
import com.example.amazonreviews.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByLogin(login);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.password(user.getPassword());
            builder.roles(user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
