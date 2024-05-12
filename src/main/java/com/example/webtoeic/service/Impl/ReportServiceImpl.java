package com.example.webtoeic.service.Impl;

import com.example.webtoeic.entity.ReportEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.ReportRepository;
import com.example.webtoeic.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public BaseResponse save(ReportEntity Report) {
        try {
            Report = reportRepository.save(Report);
            return new BaseResponse(200,"Saved successfully", Report);
        } catch(Exception e){
            return new BaseResponse(400,"Failed save", null);
        }
    }
}
