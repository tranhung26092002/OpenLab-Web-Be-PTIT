package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.CommentKhoiNgoaiViDTO;
import com.example.webtoeic.DTO.CommentKhoiNgoaiViResponseDTO;
import com.example.webtoeic.entity.*;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.CommentNgoaiViRepository;
import com.example.webtoeic.repository.KhoiNgoaiViRepository;
import com.example.webtoeic.repository.UserRepositoty;
import com.example.webtoeic.service.CommentNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentNgoaiViServiceImpl implements CommentNgoaiViService {
    @Autowired
    private CommentNgoaiViRepository commentNgoaiViRepository;

    @Autowired
    private KhoiNgoaiViRepository khoiNgoaiViRepository;

    @Autowired
    private UserRepositoty userRepositoty;

    @Override
    public BaseResponse getCommentByNgoaiViId(int ngoaiViId) {
        try {
            List<CommentNgoaiViEntity> comments = commentNgoaiViRepository.findByKhoiNgoaiViEntity_NgoaiViId(ngoaiViId);
            return new BaseResponse(200,"Fetched comment successfully", comments);
        } catch(Exception e){
            return new BaseResponse(400, "Fetched comment failed", null);
        }
    }

    @Override
    public BaseResponse addComment(CommentKhoiNgoaiViDTO commentDTO) {
        try {
            CommentNgoaiViEntity comment = new CommentNgoaiViEntity();
            comment.setCmtNgoaiViContent(commentDTO.getCmtNgoaiViContent());
            comment.setTime(LocalDateTime.now());

            KhoiNgoaiViEntity khoiNgoaiVi = khoiNgoaiViRepository.findById(commentDTO.getBaiNgoaiVid())
                    .orElseThrow(() -> new RuntimeException("KhoiDieuKhien not found"));
            comment.setKhoiNgoaiViEntity(khoiNgoaiVi);

            User user = userRepositoty.findById(commentDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            comment.setUser(user);

            CommentNgoaiViEntity savedComment = commentNgoaiViRepository.save(comment);

            CommentKhoiNgoaiViResponseDTO responseDTO = convertToDTO(savedComment);
            return new BaseResponse(200, "Comment added successfully", responseDTO);
        } catch (Exception e) {
            return new BaseResponse(400, "Comment add failed", null);
        }
    }

    public CommentKhoiNgoaiViResponseDTO convertToDTO(CommentNgoaiViEntity entity) {
        CommentKhoiNgoaiViResponseDTO dto = new CommentKhoiNgoaiViResponseDTO();
        dto.setCmtNgoaiViId(entity.getCmtNgoaiViId());
        dto.setCmtNgoaiViContent(entity.getCmtNgoaiViContent());
        dto.setTime(entity.getTime());
        if(entity.getUser() != null) {
            dto.setEmail(entity.getUser().getEmail());
        }
        return dto;
    }
}
