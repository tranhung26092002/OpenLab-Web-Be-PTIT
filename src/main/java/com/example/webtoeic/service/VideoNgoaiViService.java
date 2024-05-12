package com.example.webtoeic.service;

import com.example.webtoeic.DTO.VideoBaiDieuKhienResponseDTO;
import com.example.webtoeic.DTO.VideoBaiNgoaiViResponseDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoNgoaiViService {
    BaseResponse createVideo(MultipartFile file,
                             String title,
                             String description,
                             int baiNgoaiViId);

    BaseResponse updateVideo( MultipartFile file,
                              String title,
                              String description,
                              int videoId,
                              int baiNgoaiViId);

    BaseResponse deleteVideo(int videoId, int baiNgoaiViId);

    List<VideoBaiNgoaiViResponseDTO> findVideoByBaiNgoaiViId(int baiNgoaiViId);
}
