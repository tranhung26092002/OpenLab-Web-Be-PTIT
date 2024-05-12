package com.example.webtoeic.service;

import com.example.webtoeic.entity.ReportEntity;
import com.example.webtoeic.payload.response.BaseResponse;

public interface ReportService {
    BaseResponse save(ReportEntity Report);
}
