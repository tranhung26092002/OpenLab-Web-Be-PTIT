package com.example.webtoeic.service;

import com.example.webtoeic.entity.KhoiCamBienEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface KhoiCamBienService {
    BaseResponse update(int id, KhoiCamBienEntity updatedKhoiCamBien);

    BaseResponse save(KhoiCamBienEntity khoiCamBien);

    BaseResponse getKhoiCamBien(int id);

    BaseResponse getKhoiCamBien(int page, int limit);

    BaseResponse getAllKhoiCamBien();

    BaseResponse delete(int id);

    BaseResponse searchListKhoiCamBien(String search);
}
