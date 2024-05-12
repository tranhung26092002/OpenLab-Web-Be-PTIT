package com.example.webtoeic.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ReportDTO {
    private String title;
    private String group;
    private String nameClass;
    private Date date;
    private String instructor;
    private String practiceSession;
    private StudentDTO student;
}
