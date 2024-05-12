package com.example.webtoeic.service;

import com.example.webtoeic.DTO.VideoBaiDieuKhienResponseDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoBaiDieuKhienService {
    BaseResponse createVideo(MultipartFile file,
                             String title,
                             String description,
                             int baiDieuKhienId);

    BaseResponse updateVideo( MultipartFile file,
                              String title,
                              String description,
                              int videoId,
                              int baiDieuKhienId);

    BaseResponse deleteVideo(int videoId, int baiDieuKhienId);

    List<VideoBaiDieuKhienResponseDTO> findVideoByBaiDieuKhienId(int baiDieuKhienId);
}
