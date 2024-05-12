package com.example.webtoeic.repository;

import com.example.webtoeic.entity.CommentNgoaiViEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public  interface CommentNgoaiViRepository extends JpaRepository<CommentNgoaiViEntity, Integer> {
    List<CommentNgoaiViEntity> findByKhoiNgoaiViEntity_NgoaiViId(int id);
}
