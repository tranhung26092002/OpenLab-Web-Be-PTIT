package com.example.webtoeic.service.Impl;

import com.example.webtoeic.entity.StudentEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.StudentRepository;
import com.example.webtoeic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository  studentRepository;

    @Override
    public BaseResponse save(StudentEntity Student) {
        try {
            Student = studentRepository.save(Student);
            return new BaseResponse(200,"Saved successfully", Student);
        } catch(Exception e){
            return new BaseResponse(400,"Failed save", null);
        }
    }
}
