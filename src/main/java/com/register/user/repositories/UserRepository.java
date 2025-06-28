package com.register.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.user.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    
} 
