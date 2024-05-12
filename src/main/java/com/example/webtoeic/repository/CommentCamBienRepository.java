package com.example.webtoeic.repository;

import com.example.webtoeic.entity.CommentCamBienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentCamBienRepository extends JpaRepository<CommentCamBienEntity, Integer> {
    List<CommentCamBienEntity> findByKhoiCamBienEntity_CamBienId(int camBienId);
}
