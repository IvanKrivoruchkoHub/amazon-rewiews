package com.example.amazonreviews.repository;

import com.example.amazonreviews.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);
}
