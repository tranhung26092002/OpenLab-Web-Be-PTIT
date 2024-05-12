package com.example.webtoeic.controller.user;

import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.KhoiDieuKhienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dieukhien")
public class KhoiDieuKhienController {

    @Autowired
    private KhoiDieuKhienService khoiDieuKhienService;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllDieuKhien(){
        BaseResponse response = khoiDieuKhienService.getAllKhoiDieuKhien();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getDieuKhienPaginated(@RequestParam int page, @RequestParam int size){
        BaseResponse response = khoiDieuKhienService.getKhoiDieuKhien(page, size);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse> searchDieuKhien(@RequestParam String search){
        BaseResponse response = khoiDieuKhienService.searchListKhoiDieuKhien(search);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getDieuKhienId(@PathVariable int id){
        BaseResponse response = khoiDieuKhienService.getKhoiDieuKhien(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
