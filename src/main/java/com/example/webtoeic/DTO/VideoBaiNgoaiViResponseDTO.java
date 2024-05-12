package com.example.webtoeic.DTO;

import java.time.LocalDateTime;

public class VideoBaiNgoaiViResponseDTO {
    private int id;
    private String videoPath;
    private String title;
    private String description;
    private LocalDateTime uploadTime;

    public VideoBaiNgoaiViResponseDTO() {
    }

    public VideoBaiNgoaiViResponseDTO(int id, String videoPath, String title, String description, LocalDateTime uploadTime) {
        this.id = id;
        this.videoPath = videoPath;
        this.title = title;
        this.description = description;
        this.uploadTime = uploadTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}
