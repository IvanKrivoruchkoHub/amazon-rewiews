package com.example.amazonreviews.service.impl;

import com.example.amazonreviews.entity.User;
import com.example.amazonreviews.repository.UserRepository;
import com.example.amazonreviews.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }
}
