package com.example.webtoeic.service;

import com.example.webtoeic.DTO.VideoBaiGrammarDTO;
import com.example.webtoeic.DTO.VideoBaiGrammarResponseDTO;
import com.example.webtoeic.entity.VideoBaiGrammarEntity;
import com.example.webtoeic.entity.VideoEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoBaiGrammarService {
    BaseResponse createVideo(MultipartFile file,
                             String title,
                             String description,
                             int baiGrammarId);

    BaseResponse updateVideo( MultipartFile file,
                              String title,
                              String description,
                              int videoId,
                              int baiGrammarId);

    BaseResponse deleteVideo(int videoId, int baiGramamrId);

    List<VideoBaiGrammarResponseDTO> findVideoByBaiGrammarId(int baiGrammarId);
}
