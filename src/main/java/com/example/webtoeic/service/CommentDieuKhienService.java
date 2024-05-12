package com.example.webtoeic.service;


import com.example.webtoeic.DTO.CommentKhoiDieuKhienDTO;
import com.example.webtoeic.payload.response.BaseResponse;

public interface CommentDieuKhienService {
    BaseResponse getCommentByDieuKhienId(int dieuKhienId);
    BaseResponse addComment(CommentKhoiDieuKhienDTO commentKhoiDieuKhienDTO);
}
