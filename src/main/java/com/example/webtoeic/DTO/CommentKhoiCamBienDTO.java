package com.example.webtoeic.DTO;

public class CommentKhoiCamBienDTO {
    private String cmtCamBienContent;
    private int userId;
    private int baiCamBienId;

    public CommentKhoiCamBienDTO() {

    }

    public CommentKhoiCamBienDTO(String cmtCamBienContent, int userId, int baiCamBienId) {
        this.cmtCamBienContent = cmtCamBienContent;
        this.userId = userId;
        this.baiCamBienId = baiCamBienId;
    }

    public String getCmtCamBienContent() {
        return cmtCamBienContent;
    }

    public void setCmtCamBienContent(String cmtCamBienContent) {
        this.cmtCamBienContent = cmtCamBienContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBaiCamBienId() {
        return baiCamBienId;
    }

    public void setBaiCamBienId(int baiCamBienId) {
        this.baiCamBienId = baiCamBienId;
    }
}
