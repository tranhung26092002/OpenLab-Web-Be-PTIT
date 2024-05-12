package com.example.webtoeic.controller.user;

import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.KhoiCamBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cambien")
public class KhoiCamBienController {

    @Autowired
    private KhoiCamBienService khoiCamBienService;

    //Lấy danh sách tất cả cái cảm biến
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllCamBien(){
        BaseResponse response = khoiCamBienService.getAllKhoiCamBien();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Lấy danh sách các cảm biến với phân trang
    @GetMapping
    public ResponseEntity<BaseResponse> getCamBienPaginated(@RequestParam int page, @RequestParam int size){
        BaseResponse response = khoiCamBienService.getKhoiCamBien(page, size);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Tìm kiếm cảm biến theo từ khóa
    @GetMapping("/search")
    public ResponseEntity<BaseResponse> searchCamBien(@RequestParam String search){
        BaseResponse response = khoiCamBienService.searchListKhoiCamBien(search);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Lấy chi tiết một cảm biến dựa trên ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCamBienId(@PathVariable int id){
        BaseResponse response = khoiCamBienService.getKhoiCamBien(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
