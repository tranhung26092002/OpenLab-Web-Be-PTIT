package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.VideoBaiCamBienDTO;
import com.example.webtoeic.DTO.VideoBaiCamBienResponseDTO;
import com.example.webtoeic.DTO.VideoBaiDieuKhienDTO;
import com.example.webtoeic.DTO.VideoBaiDieuKhienResponseDTO;
import com.example.webtoeic.entity.*;
import com.example.webtoeic.entity.keys.VideoCamBienId;
import com.example.webtoeic.entity.keys.VideoDieuKhienId;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.*;
import com.example.webtoeic.service.StorageService;
import com.example.webtoeic.service.VideoBaiDieuKhienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoBaiDieuKhienServiceImpl implements VideoBaiDieuKhienService {
    @Autowired
    private StorageService storageService;

    @Autowired
    private VideoKhoiDieuKhienRepository videoKhoiDieuKhienRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private KhoiDieuKhienRepository khoiDieuKhienRepository;

    @Override
    @Transactional
    public BaseResponse createVideo(MultipartFile file,
                                    String title,
                                    String description,
                                    int baiDieuKhienId) {
        BaseResponse baseResponse = new BaseResponse();

        if(!storageService.isVideoFileWithTika(file)) {
            baseResponse.setStatus(400);
            baseResponse.setMessage("File is not a valid video type");
            return baseResponse;
        }

        String savedFilePath = storageService.saveFile(file);

        try{
            KhoiDieuKhienEntity khoiDieuKhienEntity = (khoiDieuKhienRepository.findById(baiDieuKhienId).orElseThrow(null));
            if(khoiDieuKhienEntity == null) {
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

            VideoDieuKhienId videoDieuKhienId = new VideoDieuKhienId(savedVideoEntity.getVideoId(),baiDieuKhienId);

            VideoDieuKhienEntity videoDieuKhienEntity= new VideoDieuKhienEntity(videoDieuKhienId, savedVideoEntity, khoiDieuKhienEntity);

            VideoDieuKhienEntity savedEntity = videoKhoiDieuKhienRepository.save(videoDieuKhienEntity);

            VideoBaiDieuKhienDTO videoBaiDieuKhienDTO = new VideoBaiDieuKhienDTO();
            videoBaiDieuKhienDTO.setVideoPath(savedEntity.getVideo().getVideoPath());
            videoBaiDieuKhienDTO.setTitle(savedEntity.getVideo().getTitle());
            videoBaiDieuKhienDTO.setDescription(savedEntity.getVideo().getDescription());
            videoBaiDieuKhienDTO.setUploadTime(savedEntity.getVideo().getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video uploaded and saved successfully");
            baseResponse.setData(videoBaiDieuKhienDTO);
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
                                    int videoId, int baiDieuKhienId) {
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
            VideoBaiDieuKhienDTO videoBaiDieuKhienDTO = new VideoBaiDieuKhienDTO();
            videoBaiDieuKhienDTO.setVideoPath(updatedVideoEntity.getVideoPath());
            videoBaiDieuKhienDTO.setTitle(updatedVideoEntity.getTitle());
            videoBaiDieuKhienDTO.setDescription(updatedVideoEntity.getDescription());
            videoBaiDieuKhienDTO.setUploadTime(updatedVideoEntity.getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video updated successfully");
            baseResponse.setData(videoBaiDieuKhienDTO);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to update the video");
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public BaseResponse deleteVideo(int videoId, int baiDieuKhienId) {
        BaseResponse baseResponse = new BaseResponse();
        VideoDieuKhienId videoDieuKhienId = new VideoDieuKhienId(videoId, baiDieuKhienId);
        if(!videoKhoiDieuKhienRepository.existsById(videoDieuKhienId)){
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
    public List<VideoBaiDieuKhienResponseDTO> findVideoByBaiDieuKhienId(int baiDieuKhienId) {
        List<VideoDieuKhienEntity> videoDieuKhienEntities = videoKhoiDieuKhienRepository.findByKhoiDieuKhienEntity_DieukhienId(baiDieuKhienId);
        List<VideoBaiDieuKhienResponseDTO> videoDTO = new ArrayList<>();
        for(VideoDieuKhienEntity videoDieuKhienEntity : videoDieuKhienEntities){
            VideoEntity video = videoDieuKhienEntity.getVideo();
            VideoBaiDieuKhienResponseDTO dto = new VideoBaiDieuKhienResponseDTO();
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
