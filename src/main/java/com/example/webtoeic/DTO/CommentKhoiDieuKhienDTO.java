package com.example.webtoeic.DTO;

public class CommentKhoiDieuKhienDTO {
    private String cmtDieuKhienContent;
    private int userId;
    private int baiDieuKhienId;

    public CommentKhoiDieuKhienDTO() {
    }

    public CommentKhoiDieuKhienDTO(String cmtDieuKhienContent, int userId, int baiDieuKhienId) {
        this.cmtDieuKhienContent = cmtDieuKhienContent;
        this.userId = userId;
        this.baiDieuKhienId = baiDieuKhienId;
    }

    public String getCmtDieuKhienContent() {
        return cmtDieuKhienContent;
    }

    public void setCmtDieuKhienContent(String cmtDieuKhienContent) {
        this.cmtDieuKhienContent = cmtDieuKhienContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBaiDieuKhienId() {
        return baiDieuKhienId;
    }

    public void setBaiDieuKhienId(int baiDieuKhienId) {
        this.baiDieuKhienId = baiDieuKhienId;
    }
}
