package com.example.webtoeic.repository;

import com.example.webtoeic.entity.VideoBaiGrammarEntity;
import com.example.webtoeic.entity.VideoCamBienEntity;
import com.example.webtoeic.entity.keys.VideoBaiGrammarId;
import com.example.webtoeic.entity.keys.VideoCamBienId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoKhoiCamBienRepository extends JpaRepository<VideoCamBienEntity, Integer> {
    List<VideoCamBienEntity> findByKhoiCamBienEntity_CamBienId(int baiCamBienId);

    boolean existsById(VideoCamBienId videoCamBienId);
}
