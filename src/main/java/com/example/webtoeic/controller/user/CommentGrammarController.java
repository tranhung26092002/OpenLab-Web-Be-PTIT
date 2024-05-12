package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.CommentGrammarDTO;
import com.example.webtoeic.entity.CommentGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.CommentGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class CommentGrammarController {
    @Autowired
    private CommentGrammarService commentGrammarService;

    @GetMapping("/grammar/{baigrammarid}")
    public ResponseEntity<BaseResponse> getCommentByBaiGrammarId(@PathVariable int baigrammarid){
        BaseResponse response = commentGrammarService.getCommentByBaiGrammarId(baigrammarid);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addComment(@RequestBody CommentGrammarDTO commentGrammarDTO){
        BaseResponse response = commentGrammarService.addComment(commentGrammarDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
