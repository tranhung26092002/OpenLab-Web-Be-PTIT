package com.example.webtoeic.controller.user;

import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.BaiGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grammar")
//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class BaiGrammarControllerUser {
    @Autowired
    private BaiGrammarService baiGrammarService;

    //Lấy danh sách tất cả cái bài grammar
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllBaiGrammar(){
        BaseResponse response = baiGrammarService.getAllBaiGrammar();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Lấy danh sách các bài grammar với phân trang
    @GetMapping
    public ResponseEntity<BaseResponse> getBaiGrammarPaginated(@RequestParam int page, @RequestParam int size){
        BaseResponse response = baiGrammarService.getBaiGrammar(page, size);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Tìm kiếm bài grammar theo từ khóa
    @GetMapping("/search")
    public ResponseEntity<BaseResponse> searchBaiGrammar(@RequestParam String search){
        BaseResponse response = baiGrammarService.searchListBaiGrammar(search);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Lấy chi tiết môt bài grammar dựa trên ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBaiGrammarId(@PathVariable int id){
        BaseResponse response = baiGrammarService.getBaiGrammar(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
