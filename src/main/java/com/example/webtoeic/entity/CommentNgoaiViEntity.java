package com.example.webtoeic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment_ngoai_vi")
public class CommentNgoaiViEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_ngoai_vi_id")
    private int cmtNgoaiViId;

    @Column(name = "cmt_ngoai_vi_content")
    private String cmtNgoaiViContent;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "ngoai_vi_id")
    private KhoiNgoaiViEntity khoiNgoaiViEntity;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public CommentNgoaiViEntity() {
    }

    public CommentNgoaiViEntity(int cmtNgoaiViId, String cmtNgoaiViContent, LocalDateTime time, KhoiNgoaiViEntity khoiNgoaiViEntity, User user) {
        this.cmtNgoaiViId = cmtNgoaiViId;
        this.cmtNgoaiViContent = cmtNgoaiViContent;
        this.time = time;
        this.khoiNgoaiViEntity = khoiNgoaiViEntity;
        this.user = user;
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

    public KhoiNgoaiViEntity getKhoiNgoaiViEntity() {
        return khoiNgoaiViEntity;
    }

    public void setKhoiNgoaiViEntity(KhoiNgoaiViEntity khoiNgoaiViEntity) {
        this.khoiNgoaiViEntity = khoiNgoaiViEntity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
