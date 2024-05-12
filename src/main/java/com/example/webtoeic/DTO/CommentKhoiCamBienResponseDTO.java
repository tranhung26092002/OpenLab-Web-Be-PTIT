package com.example.webtoeic.DTO;

import java.time.LocalDateTime;

public class CommentKhoiCamBienResponseDTO {
    private int cmtCamBienId;
    private String cmtCamBienContent;
    private LocalDateTime time;
    private String email;

    public CommentKhoiCamBienResponseDTO() {
    }

    public CommentKhoiCamBienResponseDTO(int cmtCamBienId, String cmtCamBienContent, LocalDateTime time, String email) {
        this.cmtCamBienId = cmtCamBienId;
        this.cmtCamBienContent = cmtCamBienContent;
        this.time = time;
        this.email = email;
    }

    public int getCmtCamBienId() {
        return cmtCamBienId;
    }

    public void setCmtCamBienId(int cmtCamBienId) {
        this.cmtCamBienId = cmtCamBienId;
    }

    public String getCmtCamBienContent() {
        return cmtCamBienContent;
    }

    public void setCmtCamBienContent(String cmtCamBienContent) {
        this.cmtCamBienContent = cmtCamBienContent;
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
