package com.example.webtoeic.repository;

import com.example.webtoeic.entity.VideoDieuKhienEntity;
import com.example.webtoeic.entity.VideoNgoaiViEntity;
import com.example.webtoeic.entity.keys.VideoDieuKhienId;
import com.example.webtoeic.entity.keys.VideoNgoaiViId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoKhoiNgoaiViRepository extends JpaRepository<VideoNgoaiViEntity,Integer> {
    List<VideoNgoaiViEntity> findByKhoiNgoaiViEntity_NgoaiViId(int baiNgoaiViId);

    boolean existsById(VideoNgoaiViId videoNgoaiViId);
}
