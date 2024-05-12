package com.example.webtoeic.DTO;

public class CommentKhoiNgoaiViDTO {
    private String cmtNgoaiViContent;
    private int userId;
    private int baiNgoaiVid;

    public CommentKhoiNgoaiViDTO() {
    }

    public CommentKhoiNgoaiViDTO(String cmtNgoaiViContent, int userId, int baiNgoaiVid) {
        this.cmtNgoaiViContent = cmtNgoaiViContent;
        this.userId = userId;
        this.baiNgoaiVid = baiNgoaiVid;
    }

    public String getCmtNgoaiViContent() {
        return cmtNgoaiViContent;
    }

    public void setCmtNgoaiViContent(String cmtNgoaiViContent) {
        this.cmtNgoaiViContent = cmtNgoaiViContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBaiNgoaiVid() {
        return baiNgoaiVid;
    }

    public void setBaiNgoaiVid(int baiNgoaiVid) {
        this.baiNgoaiVid = baiNgoaiVid;
    }
}
