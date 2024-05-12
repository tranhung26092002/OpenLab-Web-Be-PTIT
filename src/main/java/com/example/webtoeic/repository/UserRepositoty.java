package com.example.webtoeic.repository;

import com.example.webtoeic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoty extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
