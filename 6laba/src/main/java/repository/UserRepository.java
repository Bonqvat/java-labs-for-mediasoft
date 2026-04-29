package com.example.restaurantapi.repository;

import com.example.restaurantapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}