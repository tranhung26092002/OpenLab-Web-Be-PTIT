package com.example.webtoeic.service;

import com.example.webtoeic.DTO.CommentKhoiDieuKhienDTO;
import com.example.webtoeic.DTO.CommentKhoiNgoaiViDTO;
import com.example.webtoeic.payload.response.BaseResponse;

public interface CommentNgoaiViService {
    BaseResponse getCommentByNgoaiViId(int ngoaiViId);
    BaseResponse addComment(CommentKhoiNgoaiViDTO commentKhoiNgoaiViDTO);
}
