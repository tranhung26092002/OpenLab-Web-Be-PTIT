package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.VideoBaiDieuKhienDTO;
import com.example.webtoeic.DTO.VideoBaiDieuKhienResponseDTO;
import com.example.webtoeic.DTO.VideoBaiNgoaiViDTO;
import com.example.webtoeic.DTO.VideoBaiNgoaiViResponseDTO;
import com.example.webtoeic.entity.*;
import com.example.webtoeic.entity.keys.VideoDieuKhienId;
import com.example.webtoeic.entity.keys.VideoNgoaiViId;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.*;
import com.example.webtoeic.service.StorageService;
import com.example.webtoeic.service.VideoNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoNgoaiViServiceImpl implements VideoNgoaiViService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private VideoKhoiNgoaiViRepository videoKhoiNgoaiViRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private KhoiNgoaiViRepository khoiNgoaiViRepository;

    @Override
    @Transactional
    public BaseResponse createVideo(MultipartFile file,
                                    String title,
                                    String description,
                                    int baiNgoaiViId) {
        BaseResponse baseResponse = new BaseResponse();

        if(!storageService.isVideoFileWithTika(file)) {
            baseResponse.setStatus(400);
            baseResponse.setMessage("File is not a valid video type");
            return baseResponse;
        }

        String savedFilePath = storageService.saveFile(file);

        try{
            KhoiNgoaiViEntity khoiNgoaiViEntity = (khoiNgoaiViRepository.findById(baiNgoaiViId).orElseThrow(null));
            if(khoiNgoaiViEntity == null) {
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

            VideoNgoaiViId videoNgoaiViId = new VideoNgoaiViId(savedVideoEntity.getVideoId(),baiNgoaiViId);

            VideoNgoaiViEntity videoNgoaiViEntity= new VideoNgoaiViEntity(videoNgoaiViId, savedVideoEntity, khoiNgoaiViEntity);

            VideoNgoaiViEntity savedEntity = videoKhoiNgoaiViRepository.save(videoNgoaiViEntity);

            VideoBaiNgoaiViDTO videoBaiNgoaiViDTO = new VideoBaiNgoaiViDTO();
            videoBaiNgoaiViDTO.setVideoPath(savedEntity.getVideo().getVideoPath());
            videoBaiNgoaiViDTO.setTitle(savedEntity.getVideo().getTitle());
            videoBaiNgoaiViDTO.setDescription(savedEntity.getVideo().getDescription());
            videoBaiNgoaiViDTO.setUploadTime(savedEntity.getVideo().getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video uploaded and saved successfully");
            baseResponse.setData(videoBaiNgoaiViDTO);
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
                                    int baiNgoaiViId) {
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
            VideoBaiNgoaiViDTO videoBaiNgoaiViDTO = new VideoBaiNgoaiViDTO();
            videoBaiNgoaiViDTO.setVideoPath(updatedVideoEntity.getVideoPath());
            videoBaiNgoaiViDTO.setTitle(updatedVideoEntity.getTitle());
            videoBaiNgoaiViDTO.setDescription(updatedVideoEntity.getDescription());
            videoBaiNgoaiViDTO.setUploadTime(updatedVideoEntity.getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video updated successfully");
            baseResponse.setData(videoBaiNgoaiViDTO);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to update the video");
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public BaseResponse deleteVideo(int videoId, int baiNgoaiViId) {
        BaseResponse baseResponse = new BaseResponse();
        VideoNgoaiViId videoNgoaiViId = new VideoNgoaiViId(videoId, baiNgoaiViId);
        if(!videoKhoiNgoaiViRepository.existsById(videoNgoaiViId)){
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
    public List<VideoBaiNgoaiViResponseDTO> findVideoByBaiNgoaiViId(int baiNgoaiViId) {
        List<VideoNgoaiViEntity> videoNgoaiViEntities = videoKhoiNgoaiViRepository.findByKhoiNgoaiViEntity_NgoaiViId(baiNgoaiViId);
        List<VideoBaiNgoaiViResponseDTO> videoDTO = new ArrayList<>();
        for(VideoNgoaiViEntity videoNgoaiViEntity : videoNgoaiViEntities){
            VideoEntity video = videoNgoaiViEntity.getVideo();
            VideoBaiNgoaiViResponseDTO dto = new VideoBaiNgoaiViResponseDTO();
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
