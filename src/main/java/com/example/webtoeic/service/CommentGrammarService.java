package com.example.webtoeic.service;

import com.example.webtoeic.DTO.CommentGrammarDTO;
import com.example.webtoeic.entity.CommentGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.stereotype.Service;


public interface CommentGrammarService {
    BaseResponse getCommentByBaiGrammarId(int baigrammarid);
    BaseResponse addComment(CommentGrammarDTO commentGrammarDTO);
}
