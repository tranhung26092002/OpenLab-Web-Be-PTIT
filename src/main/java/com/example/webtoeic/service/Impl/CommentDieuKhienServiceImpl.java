package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.CommentKhoiDieuKhienDTO;
import com.example.webtoeic.DTO.CommentKhoiDieuKhienResponseDTO;
import com.example.webtoeic.entity.CommentKhoiDieuKhienEntity;
import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.entity.User;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.CommentDieuKhienRepository;
import com.example.webtoeic.repository.KhoiDieuKhienRepository;
import com.example.webtoeic.repository.UserRepositoty;
import com.example.webtoeic.service.CommentDieuKhienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentDieuKhienServiceImpl implements CommentDieuKhienService {

    @Autowired
    private CommentDieuKhienRepository commentDieuKhienRepository;

    @Autowired
    private KhoiDieuKhienRepository khoiDieuKhienRepository;

    @Autowired
    private UserRepositoty userRepositoty;

    @Override
    public BaseResponse getCommentByDieuKhienId(int dieuKhienId) {
        try {
            List<CommentKhoiDieuKhienEntity> comments = commentDieuKhienRepository.findByKhoiDieuKhienEntity_DieukhienId(dieuKhienId);
            return new BaseResponse(200,"Fetched comment successfully", comments);
        } catch(Exception e){
            return new BaseResponse(400, "Fetched comment failed", null);
        }
    }

    @Override
    public BaseResponse addComment(CommentKhoiDieuKhienDTO commentDTO) {
        try {
            CommentKhoiDieuKhienEntity comment = new CommentKhoiDieuKhienEntity();
            comment.setCmtDieuKhienContent(commentDTO.getCmtDieuKhienContent());
            comment.setTime(LocalDateTime.now());

            KhoiDieuKhienEntity khoiDieuKhien = khoiDieuKhienRepository.findById(commentDTO.getBaiDieuKhienId())
                    .orElseThrow(() -> new RuntimeException("KhoiDieuKhien not found"));
            comment.setKhoiDieuKhienEntity(khoiDieuKhien);

            User user = userRepositoty.findById(commentDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            comment.setUser(user);

            CommentKhoiDieuKhienEntity savedComment = commentDieuKhienRepository.save(comment);

            CommentKhoiDieuKhienResponseDTO responseDTO = convertToDTO(savedComment);
            return new BaseResponse(200, "Comment added successfully", responseDTO);
        } catch (Exception e) {
            return new BaseResponse(400, "Comment add failed", null);
        }
    }

    public CommentKhoiDieuKhienResponseDTO convertToDTO(CommentKhoiDieuKhienEntity entity) {
        CommentKhoiDieuKhienResponseDTO dto = new CommentKhoiDieuKhienResponseDTO();
        dto.setCmtDieuKhienId(entity.getCmtDieuKhienId());
        dto.setCmtDieuKhienContent(entity.getCmtDieuKhienContent());
        dto.setTime(entity.getTime());
        if(entity.getUser() != null) {
            dto.setEmail(entity.getUser().getEmail());
        }
        return dto;
    }
}
