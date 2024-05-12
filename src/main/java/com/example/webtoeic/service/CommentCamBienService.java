package com.example.webtoeic.service;

import com.example.webtoeic.DTO.CommentKhoiCamBienDTO;
import com.example.webtoeic.payload.response.BaseResponse;

public interface CommentCamBienService {
    BaseResponse getCommentByCamBienId(int baiCamBienid);
    BaseResponse addComment(CommentKhoiCamBienDTO commentKhoiCamBienDTO);
}
