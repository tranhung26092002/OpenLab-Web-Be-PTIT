package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.CommentGrammarDTO;
import com.example.webtoeic.DTO.UserDTO;
import com.example.webtoeic.entity.CommentGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.CommentGrammarRepository;
import com.example.webtoeic.service.CommentGrammarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentGrammarServiceImpl implements CommentGrammarService {
    @Autowired
    private CommentGrammarRepository commentGrammarRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getCommentByBaiGrammarId(int baigrammarid){
        try {
            List<CommentGrammar> comments = commentGrammarRepository.findByBaiGrammar_BaiGrammarId(baigrammarid);
            return new BaseResponse(200,"Fetched comment successfully", comments);
        } catch(Exception e){
            return new BaseResponse(400, "Fetched comment failed", null);
        }
    }

    @Override
    public BaseResponse addComment(CommentGrammarDTO commentGrammarDTO){
        CommentGrammar commentGrammar = converToEntity(commentGrammarDTO);
        commentGrammar.setTime(LocalDateTime.now());
        try {
            CommentGrammar savedComment = commentGrammarRepository.save(commentGrammar);
            return new BaseResponse(200, "Comment add successfully", savedComment);
        }catch(Exception e){
            return new BaseResponse(400, "Comment add failed", null);
        }
    }

    private CommentGrammar converToEntity(CommentGrammarDTO commentGrammarDTO) {
        return modelMapper.map(commentGrammarDTO, CommentGrammar.class);
    }
}
