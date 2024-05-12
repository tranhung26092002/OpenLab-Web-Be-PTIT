package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.CommentKhoiNgoaiViDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.CommentNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class CommentNgoaiViController {

    @Autowired
    private CommentNgoaiViService commentNgoaiViService;

    @GetMapping("/ngoaivi/{ngoaiViId}")
    public ResponseEntity<BaseResponse> getCommentByBaiNgoaiViId(@PathVariable int ngoaiViId) {
        BaseResponse response = commentNgoaiViService.getCommentByNgoaiViId(ngoaiViId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/ngoaivi")
    public ResponseEntity<BaseResponse> addComment(@RequestBody CommentKhoiNgoaiViDTO commentDTO) {
        BaseResponse response = commentNgoaiViService.addComment(commentDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
