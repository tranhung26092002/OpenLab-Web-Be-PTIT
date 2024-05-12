package com.example.webtoeic.service.Impl;

import com.example.webtoeic.entity.KhoiCamBienEntity;
import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.KhoiDieuKhienRepository;
import com.example.webtoeic.service.KhoiDieuKhienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class KhoiDieuKhienServiceImpl implements KhoiDieuKhienService {
    @Autowired
    private KhoiDieuKhienRepository khoiDieuKhienRepository;

    @Override
    @Transactional
    public BaseResponse save(KhoiDieuKhienEntity khoiDieuKhien) {
        try {
            khoiDieuKhien = khoiDieuKhienRepository.save(khoiDieuKhien);
            return new BaseResponse(200,"Saved successfully", khoiDieuKhien);
        } catch(Exception e){
            return new BaseResponse(400,"Failed save", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse update(int id, KhoiDieuKhienEntity updatedKhoiDieuKhien) {
        Optional<KhoiDieuKhienEntity> optionalKhoiDieuKhien = khoiDieuKhienRepository.findById(id);
        if(optionalKhoiDieuKhien.isPresent()){
            KhoiDieuKhienEntity existingKhoiDieuKhien = optionalKhoiDieuKhien.get();

            existingKhoiDieuKhien.setTenDieuKhien(updatedKhoiDieuKhien.getTenDieuKhien());
            existingKhoiDieuKhien.setAnhDieuKhien(updatedKhoiDieuKhien.getAnhDieuKhien());
            existingKhoiDieuKhien.setContentHtml(updatedKhoiDieuKhien.getContentHtml());

            KhoiDieuKhienEntity savedKhoiDieuKhien = khoiDieuKhienRepository.save(existingKhoiDieuKhien);
            return new BaseResponse(200,"Updated successfully", savedKhoiDieuKhien);
        }
        return new BaseResponse(404, "KhoiCamBien not found", null);
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getKhoiDieuKhien(int id) {
        Optional<KhoiDieuKhienEntity> optionalKhoiDieuKhien = khoiDieuKhienRepository.findById(id);
        if (optionalKhoiDieuKhien.isPresent()) {
            return new BaseResponse(200, "Retrieved successfully", optionalKhoiDieuKhien.get());
        } else {
            return new BaseResponse(404, "KhoiCamBien not found", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getKhoiDieuKhien(int page, int size) {
        try {
            Page<KhoiDieuKhienEntity> khoiDieuKhiens = khoiDieuKhienRepository.findAll(PageRequest.of(page, size));
            return new BaseResponse(200, "Retrieved successfully", khoiDieuKhiens);
        } catch (Exception e) {
            return new BaseResponse(500, "Error retrieving KhoiCamBiens", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getAllKhoiDieuKhien() {
        try {
            List<KhoiDieuKhienEntity> khoiDieuKhienEntities = khoiDieuKhienRepository.findAll();
            return new BaseResponse(200, "Retrieved successfully", khoiDieuKhienEntities);
        } catch (Exception e) {
            return new BaseResponse(500, "Error retrieving all KhoiCamBiens", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse delete(int id) {
        try {
            khoiDieuKhienRepository.deleteById(id);
            return new BaseResponse(200, "Deleted successfully", null);
        } catch (Exception e) {
            return new BaseResponse(500, "Error deleting KhoiCamBien", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse searchListKhoiDieuKhien(String search) {
        try {
            List<KhoiDieuKhienEntity> khoiDieuKhiens = khoiDieuKhienRepository.searchDieuKhien(search);
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