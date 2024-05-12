package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.CommentKhoiDieuKhienDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.CommentDieuKhienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class CommentDieuKhienController {

    @Autowired
    private CommentDieuKhienService commentDieuKhienService;

    @GetMapping("/dieukhien/{dieuKhienId}")
    public ResponseEntity<BaseResponse> getCommentByDieuKhienId(@PathVariable int dieuKhienId){
        BaseResponse response = commentDieuKhienService.getCommentByDieuKhienId(dieuKhienId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/dieukhien")
    public ResponseEntity<BaseResponse> addComment(@RequestBody CommentKhoiDieuKhienDTO commentDTO){
        BaseResponse response = commentDieuKhienService.addComment(commentDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
