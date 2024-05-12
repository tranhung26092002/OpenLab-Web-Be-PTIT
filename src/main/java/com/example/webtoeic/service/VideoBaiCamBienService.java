package com.example.webtoeic.service;

import com.example.webtoeic.DTO.VideoBaiCamBienResponseDTO;
import com.example.webtoeic.DTO.VideoBaiGrammarResponseDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoBaiCamBienService {
    BaseResponse createVideo(MultipartFile file,
                             String title,
                             String description,
                             int baiCamBienId);

    BaseResponse updateVideo( MultipartFile file,
                              String title,
                              String description,
                              int videoId,
                              int baiCamBienId);

    BaseResponse deleteVideo(int videoId, int baiCamBienId);

    List<VideoBaiCamBienResponseDTO> findVideoByBaiCamBienId(int baiCamBienId);
}
