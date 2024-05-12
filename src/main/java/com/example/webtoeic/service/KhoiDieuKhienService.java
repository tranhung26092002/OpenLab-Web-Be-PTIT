package com.example.webtoeic.service;

import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface KhoiDieuKhienService {
    BaseResponse update(int id, KhoiDieuKhienEntity updatedKhoiDieuKhien);

    BaseResponse save(KhoiDieuKhienEntity khoiDieuKhien);

    BaseResponse getKhoiDieuKhien(int id);

    BaseResponse getKhoiDieuKhien(int page, int limit);

    BaseResponse getAllKhoiDieuKhien();

    BaseResponse delete(int id);

    BaseResponse searchListKhoiDieuKhien(String search);
}
