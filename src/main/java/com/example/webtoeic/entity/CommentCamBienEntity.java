package com.example.webtoeic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment_cam_bien")
public class CommentCamBienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_cam_bien_id")
    private int cmtCamBienId;

    @Column(name = "cmt_cam_bien_content")
    private String cmtCamBienContent;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "cam_bien_id")
    private KhoiCamBienEntity khoiCamBienEntity;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public CommentCamBienEntity() {

    }

    public CommentCamBienEntity(int cmtCamBienId, String cmtCamBienContent, LocalDateTime time, KhoiCamBienEntity khoiCamBienEntity, User user) {
        this.cmtCamBienId = cmtCamBienId;
        this.cmtCamBienContent = cmtCamBienContent;
        this.time = time;
        this.khoiCamBienEntity = khoiCamBienEntity;
        this.user = user;
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

    public KhoiCamBienEntity getKhoiCamBienEntity() {
        return khoiCamBienEntity;
    }

    public void setKhoiCamBienEntity(KhoiCamBienEntity khoiCamBienEntity) {
        this.khoiCamBienEntity = khoiCamBienEntity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
