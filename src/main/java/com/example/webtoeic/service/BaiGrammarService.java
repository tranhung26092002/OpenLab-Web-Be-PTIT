package com.example.webtoeic.service;

import com.example.webtoeic.entity.BaiGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface BaiGrammarService {
    BaseResponse update(int id, BaiGrammar updatedBaiGrammar);

    BaseResponse save(BaiGrammar baiGrammar);

    BaseResponse getBaiGrammar(int id);

    BaseResponse getBaiGrammar(int page, int limit);

    BaseResponse getAllBaiGrammar();

    BaseResponse delete(int id);

    BaseResponse searchListBaiGrammar(String search);
}
