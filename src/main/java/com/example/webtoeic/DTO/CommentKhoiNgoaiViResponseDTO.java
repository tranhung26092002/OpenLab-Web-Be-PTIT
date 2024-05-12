package com.example.webtoeic.DTO;

import java.time.LocalDateTime;

public class CommentKhoiNgoaiViResponseDTO {
    private int cmtNgoaiViId;
    private String cmtNgoaiViContent;
    private LocalDateTime time;
    private String email;

    public CommentKhoiNgoaiViResponseDTO() {
    }

    public CommentKhoiNgoaiViResponseDTO(int cmtNgoaiViId, String cmtNgoaiViContent, LocalDateTime time, String email) {
        this.cmtNgoaiViId = cmtNgoaiViId;
        this.cmtNgoaiViContent = cmtNgoaiViContent;
        this.time = time;
        this.email = email;
    }

    public int getCmtNgoaiViId() {
        return cmtNgoaiViId;
    }

    public void setCmtNgoaiViId(int cmtNgoaiViId) {
        this.cmtNgoaiViId = cmtNgoaiViId;
    }

    public String getCmtNgoaiViContent() {
        return cmtNgoaiViContent;
    }

    public void setCmtNgoaiViContent(String cmtNgoaiViContent) {
        this.cmtNgoaiViContent = cmtNgoaiViContent;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
