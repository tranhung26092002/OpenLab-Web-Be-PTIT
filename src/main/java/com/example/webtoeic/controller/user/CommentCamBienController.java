package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.CommentKhoiCamBienDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.CommentCamBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class CommentCamBienController {

    @Autowired
    private CommentCamBienService commentCamBienService;

    @GetMapping("/cambien/{baiCamBienid}")
    public ResponseEntity<BaseResponse> getCommentByBaiCamBienId(@PathVariable int baiCamBienid){
        BaseResponse response = commentCamBienService.getCommentByCamBienId(baiCamBienid);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/cambien")
    public ResponseEntity<BaseResponse> addComment(@RequestBody CommentKhoiCamBienDTO commentDTO){
        BaseResponse response = commentCamBienService.addComment(commentDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
