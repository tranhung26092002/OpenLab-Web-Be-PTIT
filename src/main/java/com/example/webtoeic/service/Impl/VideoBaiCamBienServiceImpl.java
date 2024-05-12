package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.VideoBaiCamBienDTO;
import com.example.webtoeic.DTO.VideoBaiCamBienResponseDTO;
import com.example.webtoeic.entity.*;
import com.example.webtoeic.entity.keys.VideoCamBienId;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.KhoiCamBienRepository;
import com.example.webtoeic.repository.VideoKhoiCamBienRepository;
import com.example.webtoeic.repository.VideoRepository;
import com.example.webtoeic.service.StorageService;
import com.example.webtoeic.service.VideoBaiCamBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoBaiCamBienServiceImpl implements VideoBaiCamBienService {
    @Autowired
    private StorageService storageService;

    @Autowired
    private VideoKhoiCamBienRepository videoKhoiCamBienRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private KhoiCamBienRepository khoiCamBienRepository;

    @Override
    @Transactional
    public BaseResponse createVideo(MultipartFile file,
                                    String title,
                                    String description,
                                    int baiCamBienId) {
        BaseResponse baseResponse = new BaseResponse();

        if(!storageService.isVideoFileWithTika(file)) {
            baseResponse.setStatus(400);
            baseResponse.setMessage("File is not a valid video type");
            return baseResponse;
        }

        String savedFilePath = storageService.saveFile(file);

        try{
            KhoiCamBienEntity khoiCamBienEntity = (khoiCamBienRepository.findById(baiCamBienId).orElseThrow(null));
            if(khoiCamBienEntity == null) {
                baseResponse.setStatus(404);
                baseResponse.setMessage("BaiGrammar not found");
                return baseResponse;
            }

            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setVideoPath(savedFilePath);
            videoEntity.setTitle(title);
            videoEntity.setDescription(description);
            videoEntity.setUploadTime(LocalDateTime.now());

            VideoEntity savedVideoEntity = videoRepository.save(videoEntity);

            VideoCamBienId videoBaiCamBienId = new VideoCamBienId(savedVideoEntity.getVideoId(),baiCamBienId);

            VideoCamBienEntity videoCamBienEntity= new VideoCamBienEntity(videoBaiCamBienId, savedVideoEntity, khoiCamBienEntity);

            VideoCamBienEntity savedEntity = videoKhoiCamBienRepository.save(videoCamBienEntity);

            VideoBaiCamBienDTO videoBaiCamBienDTO = new VideoBaiCamBienDTO();
            videoBaiCamBienDTO.setVideoPath(savedEntity.getVideo().getVideoPath());
            videoBaiCamBienDTO.setTitle(savedEntity.getVideo().getTitle());
            videoBaiCamBienDTO.setDescription(savedEntity.getVideo().getDescription());
            videoBaiCamBienDTO.setUploadTime(savedEntity.getVideo().getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video uploaded and saved successfully");
            baseResponse.setData(videoBaiCamBienDTO);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to upload the video");
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public BaseResponse updateVideo(MultipartFile file,
                                    String title,
                                    String description,
                                    int videoId,
                                    int baiCamBienId) {
        BaseResponse baseResponse = new BaseResponse();

        VideoEntity videoEntity = videoRepository.findById(videoId).orElse(null);
        if(videoEntity == null){
            baseResponse.setStatus(404);
            baseResponse.setMessage("Video not found");
            return baseResponse;
        }

        if(file != null){
            if(!storageService.isVideoFileWithTika(file)){
                baseResponse.setStatus(400);
                baseResponse.setMessage("File is not a valid video type");
                return baseResponse;
            }

            String savedFilePath = storageService.saveFile(file);
            videoEntity.setVideoPath(savedFilePath);
        }

        videoEntity.setTitle(title);
        videoEntity.setDescription(description);

        try {
            VideoEntity updatedVideoEntity = videoRepository.save(videoEntity);
            VideoBaiCamBienDTO videoBaiCamBienDTO = new VideoBaiCamBienDTO();
            videoBaiCamBienDTO.setVideoPath(updatedVideoEntity.getVideoPath());
            videoBaiCamBienDTO.setTitle(updatedVideoEntity.getTitle());
            videoBaiCamBienDTO.setDescription(updatedVideoEntity.getDescription());
            videoBaiCamBienDTO.setUploadTime(updatedVideoEntity.getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video updated successfully");
            baseResponse.setData(videoBaiCamBienDTO);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to update the video");
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public BaseResponse deleteVideo(int videoId, int baiCamBienId) {
        BaseResponse baseResponse = new BaseResponse();
        VideoCamBienId videoCamBienId = new VideoCamBienId(videoId, baiCamBienId);
        if(!videoKhoiCamBienRepository.existsById(videoCamBienId)){
            baseResponse.setStatus(404);
            baseResponse.setMessage("Video not found for the given BaiGrammarId");
            return baseResponse;
        }
        if(!videoRepository.existsById(videoId)){
            baseResponse.setStatus(404);
            baseResponse.setMessage("Video not found");
            return baseResponse;
        }

        try {
            videoRepository.deleteById(videoId);
            baseResponse.setStatus(200);
            baseResponse.setMessage("Video deleted successfully");
        } catch(Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to delete the video");
        }
        return baseResponse;
    }

    @Override
    public List<VideoBaiCamBienResponseDTO> findVideoByBaiCamBienId(int baiCamBienId) {
        List<VideoCamBienEntity> videoCamBienEntities = videoKhoiCamBienRepository.findByKhoiCamBienEntity_CamBienId(baiCamBienId);
        List<VideoBaiCamBienResponseDTO> videoDTO = new ArrayList<>();
        for(VideoCamBienEntity videoCamBienEntity : videoCamBienEntities){
            VideoEntity video = videoCamBienEntity.getVideo();
            VideoBaiCamBienResponseDTO dto = new VideoBaiCamBienResponseDTO();
            dto.setId(video.getVideoId());
            dto.setVideoPath(video.getVideoPath());
            dto.setTitle(video.getTitle());
            dto.setDescription(video.getDescription());
            dto.setUploadTime(video.getUploadTime());
            videoDTO.add(dto);
        }
        return videoDTO;
    }
}
