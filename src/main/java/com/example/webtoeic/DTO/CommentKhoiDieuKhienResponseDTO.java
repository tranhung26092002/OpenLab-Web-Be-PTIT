package com.example.webtoeic.DTO;

import java.time.LocalDateTime;

public class CommentKhoiDieuKhienResponseDTO {
    private int cmtDieuKhienId;
    private String cmtDieuKhienContent;
    private LocalDateTime time;
    private String email;

    public CommentKhoiDieuKhienResponseDTO() {
    }

    public CommentKhoiDieuKhienResponseDTO(int cmtDieuKhienId, String cmtDieuKhienContent, LocalDateTime time, String email) {
        this.cmtDieuKhienId = cmtDieuKhienId;
        this.cmtDieuKhienContent = cmtDieuKhienContent;
        this.time = time;
        this.email = email;
    }

    public int getCmtDieuKhienId() {
        return cmtDieuKhienId;
    }

    public void setCmtDieuKhienId(int cmtDieuKhienId) {
        this.cmtDieuKhienId = cmtDieuKhienId;
    }

    public String getCmtDieuKhienContent() {
        return cmtDieuKhienContent;
    }

    public void setCmtDieuKhienContent(String cmtDieuKhienContent) {
        this.cmtDieuKhienContent = cmtDieuKhienContent;
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
