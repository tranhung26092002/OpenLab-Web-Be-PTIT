package com.example.webtoeic.repository;

import com.example.webtoeic.entity.VideoCamBienEntity;
import com.example.webtoeic.entity.VideoDieuKhienEntity;
import com.example.webtoeic.entity.keys.VideoCamBienId;
import com.example.webtoeic.entity.keys.VideoDieuKhienId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoKhoiDieuKhienRepository extends JpaRepository<VideoDieuKhienEntity, Integer> {
    List<VideoDieuKhienEntity> findByKhoiDieuKhienEntity_DieukhienId(int baiDieuKhienId);

    boolean existsById(VideoDieuKhienId videoDieuKhienId);
}
