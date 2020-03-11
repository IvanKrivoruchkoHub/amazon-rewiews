package com.example.amazonreviews.service.impl;

import com.example.amazonreviews.entity.Role;
import com.example.amazonreviews.repository.RoleRepository;
import com.example.amazonreviews.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(name);
    }
}
