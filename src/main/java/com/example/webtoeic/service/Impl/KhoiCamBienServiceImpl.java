package com.example.webtoeic.service.Impl;

import com.example.webtoeic.entity.KhoiCamBienEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.KhoiCamBienRepository;
import com.example.webtoeic.service.KhoiCamBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class KhoiCamBienServiceImpl implements KhoiCamBienService {

    @Autowired
    private KhoiCamBienRepository khoiCamBienRepository;

    @Override
    @Transactional
    public BaseResponse save(KhoiCamBienEntity khoiCamBien) {
        try {
            khoiCamBien = khoiCamBienRepository.save(khoiCamBien);
            return new BaseResponse(200,"Saved successfully", khoiCamBien);
        } catch(Exception e){
            return new BaseResponse(400,"Failed save", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse update(int id, KhoiCamBienEntity updatedKhoiCamBien) {
        Optional<KhoiCamBienEntity> optionalKhoiCamBien = khoiCamBienRepository.findById(id);
        if(optionalKhoiCamBien.isPresent()){
            KhoiCamBienEntity existingKhoiCamBien = optionalKhoiCamBien.get();

            existingKhoiCamBien.setTenCamBien(updatedKhoiCamBien.getTenCamBien());
            existingKhoiCamBien.setAnhCamBien(updatedKhoiCamBien.getAnhCamBien());
            existingKhoiCamBien.setContentHtml(updatedKhoiCamBien.getContentHtml());

            KhoiCamBienEntity savedKhoiCamBien = khoiCamBienRepository.save(existingKhoiCamBien);
            return new BaseResponse(200,"Updated successfully", savedKhoiCamBien);
        }
        return new BaseResponse(404, "KhoiCamBien not found", null);
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getKhoiCamBien(int id) {
        Optional<KhoiCamBienEntity> optionalKhoiCamBien = khoiCamBienRepository.findById(id);
        if (optionalKhoiCamBien.isPresent()) {
            return new BaseResponse(200, "Retrieved successfully", optionalKhoiCamBien.get());
        } else {
            return new BaseResponse(404, "KhoiCamBien not found", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getKhoiCamBien(int page, int size) {
        try {
            Page<KhoiCamBienEntity> khoiCamBiens = khoiCamBienRepository.findAll(PageRequest.of(page, size));
            return new BaseResponse(200, "Retrieved successfully", khoiCamBiens);
        } catch (Exception e) {
            return new BaseResponse(500, "Error retrieving KhoiCamBiens", null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getAllKhoiCamBien() {
        try {
            List<KhoiCamBienEntity> khoiCamBienEntities = khoiCamBienRepository.findAll();
            return new BaseResponse(200, "Retrieved successfully", khoiCamBienEntities);
        } catch (Exception e) {
            return new BaseResponse(500, "Error retrieving all KhoiCamBiens", null);
        }
    }

    @Override
//    @Transactional(readOnly = true)
    public BaseResponse delete(int id) {
        try {
            khoiCamBienRepository.deleteById(id);
            return new BaseResponse(200, "Deleted successfully", null);
        } catch (Exception e) {
            return new BaseResponse(500, "Error deleting KhoiCamBien", null);
        }
    }

    @Override
    @Transactional
    public BaseResponse searchListKhoiCamBien(String search) {
        try {
            List<KhoiCamBienEntity> khoiCamBiens = khoiCamBienRepository.searchCamBien(search);
            if (khoiCamBiens.isEmpty()) {
                return new BaseResponse(404, "No results found for the search query", null);
            } else {
                return new BaseResponse(200, "Search results retrieved successfully", khoiCamBiens);
            }
        } catch (Exception e) {
            return new BaseResponse(500, "Error processing search", null);
        }
    }
}