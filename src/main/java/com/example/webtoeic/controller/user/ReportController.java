package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.ReportDTO;
import com.example.webtoeic.DTO.StudentDTO;
import com.example.webtoeic.entity.ReportEntity;
import com.example.webtoeic.entity.StudentEntity;
import com.example.webtoeic.repository.ReportRepository;
import com.example.webtoeic.service.ReportService;
import com.example.webtoeic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/IoT/report")
public class ReportController {

    @Autowired
    private ReportService ReportService;
    @Autowired
    private StudentService StudentService;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllSensorReport(){
        List<ReportEntity> reports = reportRepository.findAll();
        return ResponseEntity.ok(reports);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createSensorReport(@RequestBody ReportDTO sensorReportDTO) {
        try {
            ReportEntity reportEntity = convertDTOToEntity(sensorReportDTO);
            ReportService.save(reportEntity);

            return ResponseEntity.ok(reportEntity);
        } catch (Exception e) {
            String errorMessage = "Error creating product: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    private ReportEntity convertDTOToEntity(ReportDTO sensorReportDTO) {
        ReportEntity reportEntity = new ReportEntity();

        reportEntity.setTitle(sensorReportDTO.getTitle());
        reportEntity.setGroupName(sensorReportDTO.getGroup());
        reportEntity.setClassName(sensorReportDTO.getNameClass());
        reportEntity.setDate(new Date());
        reportEntity.setInstructor(sensorReportDTO.getInstructor());
        reportEntity.setPracticeSession(sensorReportDTO.getPracticeSession());

        StudentDTO studentDTO = sensorReportDTO.getStudent();

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(studentDTO.getName());
        studentEntity.setStudentId(studentDTO.getId());
        StudentService.save(studentEntity);

        reportEntity.setStudent(studentEntity);

        return reportEntity;
    }

    @PutMapping("/updateGrade/{reportId}")
    public ResponseEntity<Object> updateGrade(@PathVariable Long reportId, @RequestParam int newGrade) {
        try {
            Optional<ReportEntity> optionalReport = reportRepository.findById(reportId);
            if (optionalReport.isPresent()) {
                ReportEntity reportEntity = optionalReport.get();
                reportEntity.setGrade(newGrade);
                reportRepository.save(reportEntity);
                return ResponseEntity.ok("Grade updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Report not found");
            }
        } catch (Exception e) {
            String errorMessage = "Error updating grade: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/deleteReport/{reportId}")
    public ResponseEntity<Object> deleteReport(@PathVariable Long reportId) {
        try {
            Optional<ReportEntity> optionalReport = reportRepository.findById(reportId);
            if (optionalReport.isPresent()) {
                ReportEntity reportEntity = optionalReport.get();
                reportRepository.delete(reportEntity);
                return ResponseEntity.ok("Report deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Report not found");
            }
        } catch (Exception e) {
            String errorMessage = "Error deleting report: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}

