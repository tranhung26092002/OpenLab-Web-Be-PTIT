package com.example.webtoeic.repository;

import com.example.webtoeic.entity.CommentKhoiDieuKhienEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface CommentDieuKhienRepository extends JpaRepository<CommentKhoiDieuKhienEntity,Integer> {
    List<CommentKhoiDieuKhienEntity> findByKhoiDieuKhienEntity_DieukhienId(int id);
}