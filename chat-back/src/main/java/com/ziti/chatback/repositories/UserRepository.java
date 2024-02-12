package com.ziti.chatback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ziti.chatback.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}