package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.VideoBaiGrammarDTO;
import com.example.webtoeic.DTO.VideoBaiGrammarResponseDTO;
import com.example.webtoeic.entity.BaiGrammar;
import com.example.webtoeic.entity.VideoBaiGrammarEntity;
import com.example.webtoeic.entity.VideoEntity;
import com.example.webtoeic.entity.keys.VideoBaiGrammarId;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.BaiGrammarRepository;
import com.example.webtoeic.repository.VideoBaiGrammarRepository;
import com.example.webtoeic.repository.VideoRepository;
import com.example.webtoeic.service.StorageService;
import com.example.webtoeic.service.VideoBaiGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoGrammarServiceImpl implements VideoBaiGrammarService {
    @Autowired
    private StorageService storageService;

    @Autowired
    private VideoBaiGrammarRepository videoBaiGrammarRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private BaiGrammarRepository baiGrammarRepository;

    @Override
    @Transactional
    public BaseResponse createVideo(MultipartFile file,
                                    String title,
                                    String description,
                                    int baiGrammarId
                                    ) {
        BaseResponse baseResponse = new BaseResponse();

        if(!storageService.isVideoFileWithTika(file)) {
            baseResponse.setStatus(400);
            baseResponse.setMessage("File is not a valid video type");
            return baseResponse;
        }

        String savedFilePath = storageService.saveFile(file);

        try{
            BaiGrammar baiGrammar = (baiGrammarRepository.findById(baiGrammarId).orElseThrow(null));
            if(baiGrammar == null) {
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

            VideoBaiGrammarId videoBaiGrammarId = new VideoBaiGrammarId(savedVideoEntity.getVideoId(),baiGrammarId);

            VideoBaiGrammarEntity videoBaiGrammarEntity = new VideoBaiGrammarEntity(videoBaiGrammarId, savedVideoEntity, baiGrammar);

            VideoBaiGrammarEntity savedEntity = videoBaiGrammarRepository.save(videoBaiGrammarEntity);

            VideoBaiGrammarDTO videoBaiGrammarDTO = new VideoBaiGrammarDTO();
            videoBaiGrammarDTO.setVideoPath(savedEntity.getVideo().getVideoPath());
            videoBaiGrammarDTO.setTitle(savedEntity.getVideo().getTitle());
            videoBaiGrammarDTO.setDescription(savedEntity.getVideo().getDescription());
            videoBaiGrammarDTO.setUploadTime(savedEntity.getVideo().getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video uploaded and saved successfully");
            baseResponse.setData(videoBaiGrammarDTO);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to upload the video");
        }
        return baseResponse;
    }

    @Override
    @Transactional
    public BaseResponse updateVideo(
            MultipartFile file,
            String title,
            String description,
            int videoId,
            int baiGrammarId){
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
            VideoBaiGrammarDTO videoBaiGrammarDTO = new VideoBaiGrammarDTO();
            videoBaiGrammarDTO.setVideoPath(updatedVideoEntity.getVideoPath());
            videoBaiGrammarDTO.setTitle(updatedVideoEntity.getTitle());
            videoBaiGrammarDTO.setDescription(updatedVideoEntity.getDescription());
            videoBaiGrammarDTO.setUploadTime(updatedVideoEntity.getUploadTime());

            baseResponse.setStatus(200);
            baseResponse.setMessage("Video updated successfully");
            baseResponse.setData(videoBaiGrammarDTO);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Failed to update the video");
        }
        return baseResponse;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResponse deleteVideo(int videoId, int baiGramamrId) {
        BaseResponse baseResponse = new BaseResponse();
        VideoBaiGrammarId videoBaiGrammarId = new VideoBaiGrammarId(videoId, baiGramamrId);
        if(!videoBaiGrammarRepository.existsById(videoBaiGrammarId)){
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
    public List<VideoBaiGrammarResponseDTO> findVideoByBaiGrammarId(int baiGrammarId){
        List<VideoBaiGrammarEntity> videoBaiGrammarEntities = videoBaiGrammarRepository.findByBaiGrammar_BaiGrammarId(baiGrammarId);
        List<VideoBaiGrammarResponseDTO> videoDTO = new ArrayList<>();
        for(VideoBaiGrammarEntity videoBaiGrammarEntity : videoBaiGrammarEntities){
            VideoEntity video = videoBaiGrammarEntity.getVideo();
            VideoBaiGrammarResponseDTO dto = new VideoBaiGrammarResponseDTO();
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
