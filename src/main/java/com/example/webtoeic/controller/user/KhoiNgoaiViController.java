package com.example.webtoeic.controller.user;

import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.KhoiNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ngoaivi")
public class KhoiNgoaiViController {

    @Autowired
    private KhoiNgoaiViService khoiNgoaiViService;

    // Lấy danh sách tất cả các ngoại vị
    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllNgoaiVi() {
        BaseResponse response = khoiNgoaiViService.getAllKhoiNgoaiVi();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Lấy danh sách các ngoại vị với phân trang
    @GetMapping
    public ResponseEntity<BaseResponse> getNgoaiViPaginated(@RequestParam int page, @RequestParam int size) {
        BaseResponse response = khoiNgoaiViService.getKhoiNgoaiVi(page, size);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Tìm kiếm ngoại vị theo từ khóa
    @GetMapping("/search")
    public ResponseEntity<BaseResponse> searchNgoaiVi(@RequestParam String search) {
        BaseResponse response = khoiNgoaiViService.searchListKhoiNgoaiVi(search);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Lấy chi tiết một ngoại vị dựa trên ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getNgoaiViId(@PathVariable int id) {
        BaseResponse response = khoiNgoaiViService.getKhoiNgoaiVi(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
