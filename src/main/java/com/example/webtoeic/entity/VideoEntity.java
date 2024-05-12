package com.example.webtoeic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
@Entity
@Table(name = "video")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private int videoId;

    @Column(name = "video_path")
    private String videoPath;

    @Column(name = "title")
    private String title;

    @Column(name = "desciption")
    private String description;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    @JsonIgnore
    @OneToMany(mappedBy = "video")
    private List<VideoBaiGrammarEntity> videoBaiGrammarEntities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "video")
    private List<VideoCamBienEntity> videoCamBienEntities = new ArrayList<>();

    public VideoEntity() {
    }

    public VideoEntity(int videoId, String videoPath, String title, String description, LocalDateTime uploadTime, List<VideoBaiGrammarEntity> videoBaiGrammarEntities, List<VideoCamBienEntity> videoCamBienEntities) {
        this.videoId = videoId;
        this.videoPath = videoPath;
        this.title = title;
        this.description = description;
        this.uploadTime = uploadTime;
        this.videoBaiGrammarEntities = videoBaiGrammarEntities;
        this.videoCamBienEntities = videoCamBienEntities;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
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

    public List<VideoBaiGrammarEntity> getVideoBaiGrammarEntities() {
        return videoBaiGrammarEntities;
    }

    public void setVideoBaiGrammarEntities(List<VideoBaiGrammarEntity> videoBaiGrammarEntities) {
        this.videoBaiGrammarEntities = videoBaiGrammarEntities;
    }

    public List<VideoCamBienEntity> getVideoCamBienEntities() {
        return videoCamBienEntities;
    }

    public void setVideoCamBienEntities(List<VideoCamBienEntity> videoCamBienEntities) {
        this.videoCamBienEntities = videoCamBienEntities;
    }
}
