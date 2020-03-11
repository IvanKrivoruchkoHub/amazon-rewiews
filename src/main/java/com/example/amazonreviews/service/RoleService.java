package com.example.amazonreviews.service;

import com.example.amazonreviews.entity.Role;

public interface RoleService {
    Role save(Role role);

    Role getByName(String name);
}
