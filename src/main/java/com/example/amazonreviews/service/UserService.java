package com.example.amazonreviews.service;

import com.example.amazonreviews.entity.User;

public interface UserService {
    User save(User user);
    User getByLogin(String login);
}
