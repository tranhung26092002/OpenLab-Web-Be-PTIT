package com.example.webtoeic.service;

import com.example.webtoeic.entity.StudentEntity;
import com.example.webtoeic.payload.response.BaseResponse;

public interface StudentService {
    BaseResponse save(StudentEntity Student);

}
