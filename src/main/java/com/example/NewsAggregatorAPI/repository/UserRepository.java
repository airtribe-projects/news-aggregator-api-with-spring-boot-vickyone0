package com.example.NewsAggregatorAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NewsAggregatorAPI.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public User findByUsername(String username);
    
}
