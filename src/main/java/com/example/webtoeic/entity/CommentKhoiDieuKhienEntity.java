package com.example.webtoeic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment_dieu_khien")
public class CommentKhoiDieuKhienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_dieu_khien_id")
    private Integer cmtDieuKhienId;

    @Column(name = "cmt_dieu_khien_content")
    private String cmtDieuKhienContent;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "dieu_khien_id")
    private KhoiDieuKhienEntity khoiDieuKhienEntity;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public CommentKhoiDieuKhienEntity() {
    }

    public CommentKhoiDieuKhienEntity(Integer cmtDieuKhienId, String cmtDieuKhienContent, LocalDateTime time, KhoiDieuKhienEntity khoiDieuKhienEntity, User user) {
        this.cmtDieuKhienId = cmtDieuKhienId;
        this.cmtDieuKhienContent = cmtDieuKhienContent;
        this.time = time;
        this.khoiDieuKhienEntity = khoiDieuKhienEntity;
        this.user = user;
    }

    public Integer getCmtDieuKhienId() {
        return cmtDieuKhienId;
    }

    public void setCmtDieuKhienId(Integer cmtDieuKhienId) {
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

    public KhoiDieuKhienEntity getKhoiDieuKhienEntity() {
        return khoiDieuKhienEntity;
    }

    public void setKhoiDieuKhienEntity(KhoiDieuKhienEntity khoiDieuKhienEntity) {
        this.khoiDieuKhienEntity = khoiDieuKhienEntity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
