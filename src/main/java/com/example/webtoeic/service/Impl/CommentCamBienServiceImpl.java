package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.CommentKhoiCamBienDTO;
import com.example.webtoeic.DTO.CommentKhoiCamBienResponseDTO;
import com.example.webtoeic.entity.CommentCamBienEntity;
import com.example.webtoeic.entity.KhoiCamBienEntity;
import com.example.webtoeic.entity.User;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.CommentCamBienRepository;
import com.example.webtoeic.repository.KhoiCamBienRepository;
import com.example.webtoeic.repository.UserRepositoty;
import com.example.webtoeic.service.CommentCamBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentCamBienServiceImpl implements CommentCamBienService {

    @Autowired
    private CommentCamBienRepository commentCamBienRepository;

    @Autowired
    private KhoiCamBienRepository khoiCamBienRepository;

    @Autowired
    private UserRepositoty userRepositoty;
    @Override
    public BaseResponse getCommentByCamBienId(int baiCamBienid) {
        try {
            List<CommentCamBienEntity> comments = commentCamBienRepository.findByKhoiCamBienEntity_CamBienId(baiCamBienid);
            return new BaseResponse(200,"Fetched comment successfully", comments);
        } catch(Exception e){
            return new BaseResponse(400, "Fetched comment failed", null);
        }
    }

    @Override
    public BaseResponse addComment(CommentKhoiCamBienDTO commentDTO) {
        try {
            CommentCamBienEntity comment = new CommentCamBienEntity();
            comment.setCmtCamBienContent(commentDTO.getCmtCamBienContent());
            comment.setTime(LocalDateTime.now());

            KhoiCamBienEntity khoiCamBien = khoiCamBienRepository.findById(commentDTO.getBaiCamBienId())
                    .orElseThrow(() -> new RuntimeException("KhoiCamBien not found"));
            comment.setKhoiCamBienEntity(khoiCamBien);
            User user = userRepositoty.findById(commentDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            comment.setUser(user);
            CommentCamBienEntity savedComment = commentCamBienRepository.save(comment);

            //Chuyển đổi entity sang DTO
            CommentKhoiCamBienResponseDTO responseDTO = convertToDTO(savedComment);

            return new BaseResponse(200, "Comment added successfully", responseDTO);
        } catch (Exception e) {
            return new BaseResponse(400, "Comment add failed", null);
        }
    }


    public CommentKhoiCamBienResponseDTO convertToDTO(CommentCamBienEntity entity) {
        CommentKhoiCamBienResponseDTO dto = new CommentKhoiCamBienResponseDTO();
        dto.setCmtCamBienId(entity.getCmtCamBienId());
        dto.setCmtCamBienContent(entity.getCmtCamBienContent());
        dto.setTime(entity.getTime());
        if(entity.getUser() != null) {
            dto.setEmail(entity.getUser().getEmail());
        }
        return dto;
    }

}
