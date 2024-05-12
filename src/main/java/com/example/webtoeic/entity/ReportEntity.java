package com.example.webtoeic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "Report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "title")
    private String title;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "date")
    private Date date;

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "practice_session")
    private String practiceSession;

    @OneToOne
    @JoinColumn(name = "student_id") // Assuming there's a column named student_id in the Report table
    private StudentEntity student;

    @Column(name = "grade")
    private Integer grade;
}
