package com.example.webtoeic.service;

import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.entity.KhoiNgoaiViEntity;
import com.example.webtoeic.payload.response.BaseResponse;

public interface KhoiNgoaiViService {
    BaseResponse update(int id, KhoiNgoaiViEntity updatedKhoiNgoaiVi);

    BaseResponse save(KhoiNgoaiViEntity khoiNgoaiVi);

    BaseResponse getKhoiNgoaiVi(int id);

    BaseResponse getKhoiNgoaiVi(int page, int limit);

    BaseResponse getAllKhoiNgoaiVi();

    BaseResponse delete(int id);

    BaseResponse searchListKhoiNgoaiVi(String search);
}
