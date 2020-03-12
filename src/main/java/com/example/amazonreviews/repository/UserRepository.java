package com.example.amazonreviews.repository;

import com.example.amazonreviews.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.roles where u.login = ?1")
    User getByLogin(String login);
}
