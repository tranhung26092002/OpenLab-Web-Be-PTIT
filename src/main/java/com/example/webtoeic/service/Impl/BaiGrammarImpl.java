package com.example.webtoeic.service.Impl;

import com.example.webtoeic.entity.BaiGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.BaiGrammarRepository;
import com.example.webtoeic.service.BaiGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BaiGrammarImpl implements BaiGrammarService {
    @Autowired
    BaiGrammarRepository baiGrammarRepository;

    private BaseResponse createResponse(int status, String message, Object data){
        BaseResponse response = new BaseResponse();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
    @Override
    public BaseResponse save(BaiGrammar baiGrammar){
        try {
            baiGrammar = baiGrammarRepository.save(baiGrammar);
            return createResponse(200,"Saved successfully", baiGrammar);
        } catch(Exception e){
            return createResponse(400,"Failed save", null);
        }
    }

    @Override
    public BaseResponse update(int id, BaiGrammar updatedBaiGrammar){
        Optional<BaiGrammar> optionalBaiGrammar = baiGrammarRepository.findById(id);
        if(optionalBaiGrammar.isPresent()){
            BaiGrammar existingBaiGrammar = optionalBaiGrammar.get();

            existingBaiGrammar.setTenBaiGrammar(updatedBaiGrammar.getTenBaiGrammar());
            existingBaiGrammar.setAnhBaiGrammar(updatedBaiGrammar.getAnhBaiGrammar());
            existingBaiGrammar.setContentHTML(updatedBaiGrammar.getContentHTML());

            BaiGrammar savedBaiGrammar = baiGrammarRepository.save(existingBaiGrammar);
            return createResponse(200,"Updated Successfull", savedBaiGrammar);
        }
        return createResponse(404, "Grammar not found", null);
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse getBaiGrammar(int id){
        Optional<BaiGrammar> optionalBaiGrammar = baiGrammarRepository.findByBaiGrammarId(id);
        if (optionalBaiGrammar.isPresent()) {
            return createResponse(200, "Retrieved successfully", optionalBaiGrammar.get());
        } else {
            return createResponse(404, "Grammar not found", null);
        }
    }

    @Override
    public BaseResponse getBaiGrammar(int page, int size) {
        try {
            Page<BaiGrammar> baiGrammars = baiGrammarRepository.findAll(PageRequest.of(page, size));
            return createResponse(200, "Retrieved successfully", baiGrammars);
        } catch (Exception e) {
            return createResponse(500, "Error retrieving grammars", null);
        }
    }

    @Override
    public BaseResponse getAllBaiGrammar() {
        try {
            List<BaiGrammar> baiGrammarEntities = baiGrammarRepository.findAll();
            return createResponse(200, "Retrieved successfully", baiGrammarEntities);
        } catch (Exception e) {
            return createResponse(500, "Error retrieving all grammars", null);
        }
    }


    @Override
    public BaseResponse delete(int id){
        try {
            baiGrammarRepository.deleteById(id);
            return createResponse(200, "Deleted successfully", true);
        } catch (Exception e) {
            return createResponse(500, "Error deleting grammar", false);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResponse searchListBaiGrammar(String search){
        try {
            List<BaiGrammar> baiGrammarEntities = baiGrammarRepository.searchGrammar(search);
            if (baiGrammarEntities.isEmpty()) {
                return createResponse(404, "No results found for the search query", null);
            } else {
                return createResponse(200, "Search results retrieved successfully", baiGrammarEntities);
            }
        } catch (Exception e) {
            return createResponse(500, "Error processing search", null);
        }
    }

}

