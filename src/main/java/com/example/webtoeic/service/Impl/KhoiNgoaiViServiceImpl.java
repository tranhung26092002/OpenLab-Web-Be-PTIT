package com.example.webtoeic.service.Impl;

import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.entity.KhoiNgoaiViEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.KhoiDieuKhienRepository;
import com.example.webtoeic.repository.KhoiNgoaiViRepository;
import com.example.webtoeic.service.KhoiNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class KhoiNgoaiViServiceImpl implements KhoiNgoaiViService {
    @Autowired
    private KhoiNgoaiViRepository khoiNgoaiViRepository;

    @Override
    @Transactional
    public BaseResponse save(KhoiNgoaiViEntity khoiNgoaiVi) {
        try {
            khoiNgoaiVi = khoiNgoaiViRepository.save(khoiNgoaiVi);
            return new BaseResponse(200,"Saved successfully", khoiNgoaiVi);
        } catch(Exception e){
            return new BaseResponse(400,"Failed save", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse update(int id, KhoiNgoaiViEntity updatedKhoiNgoaiVi) {
        Optional<KhoiNgoaiViEntity> optionalKhoiNgoaiVi = khoiNgoaiViRepository.findById(id);
        if(optionalKhoiNgoaiVi.isPresent()){
            KhoiNgoaiViEntity existingKhoiNgoaiVi = optionalKhoiNgoaiVi.get();

            existingKhoiNgoaiVi.setTenNgoaiVi(updatedKhoiNgoaiVi.getTenNgoaiVi());
            existingKhoiNgoaiVi.setAnhNgoaiVi(updatedKhoiNgoaiVi.getAnhNgoaiVi());
            existingKhoiNgoaiVi.setContentHtml(updatedKhoiNgoaiVi.getContentHtml());

            KhoiNgoaiViEntity savedKhoiNgoaiVi = khoiNgoaiViRepository.save(existingKhoiNgoaiVi);
            return new BaseResponse(200,"Updated successfully", savedKhoiNgoaiVi);
        }
        return new BaseResponse(404, "KhoiCamBien not found", null);
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getKhoiNgoaiVi(int id) {
        Optional<KhoiNgoaiViEntity> optionalKhoiNgoaiVi = khoiNgoaiViRepository.findById(id);
        if (optionalKhoiNgoaiVi.isPresent()) {
            return new BaseResponse(200, "Retrieved successfully", optionalKhoiNgoaiVi.get());
        } else {
            return new BaseResponse(404, "KhoiCamBien not found", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getKhoiNgoaiVi(int page, int size) {
        try {
            Page<KhoiNgoaiViEntity> khoiDieuKhiens = khoiNgoaiViRepository.findAll(PageRequest.of(page, size));
            return new BaseResponse(200, "Retrieved successfully", khoiDieuKhiens);
        } catch (Exception e) {
            return new BaseResponse(500, "Error retrieving KhoiCamBiens", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getAllKhoiNgoaiVi() {
        try {
            List<KhoiNgoaiViEntity> khoiNgoaiViEntities = khoiNgoaiViRepository.findAll();
            return new BaseResponse(200, "Retrieved successfully", khoiNgoaiViEntities);
        } catch (Exception e) {
            return new BaseResponse(500, "Error retrieving all KhoiCamBiens", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse delete(int id) {
        try {
            khoiNgoaiViRepository.deleteById(id);
            return new BaseResponse(200, "Deleted successfully", null);
        } catch (Exception e) {
            return new BaseResponse(500, "Error deleting KhoiCamBien", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse searchListKhoiNgoaiVi(String search) {
        try {
            List<KhoiDieuKhienEntity> khoiDieuKhiens = khoiNgoaiViRepository.searchDieuKhien(search);
            if (khoiDieuKhiens.isEmpty()) {
                return new BaseResponse(404, "No results found for the search query", null);
            } else {
                return new BaseResponse(200, "Search results retrieved successfully", khoiDieuKhiens);
            }
        } catch (Exception e) {
            return new BaseResponse(500, "Error processing search", null);
        }
    }
}
