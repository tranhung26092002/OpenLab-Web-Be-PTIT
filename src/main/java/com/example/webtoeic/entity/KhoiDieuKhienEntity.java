package com.example.webtoeic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khoi_dieu_khien")
public class KhoiDieuKhienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dieu_khien_id")
    private int dieukhienId;

    @Column(name = "anh_dieu_khien")
    private String anhDieuKhien;

    @Column(columnDefinition = "MEDIUMTEXT", name = "content_html")
    private String contentHtml;

    @Column(columnDefinition = "LONGTEXT", name = "content_mark_down")
    private String contentMarkDown;

    @Column(name = "ten_dieu_khien")
    private String tenDieuKhien;

    @JsonIgnore
    @OneToMany(mappedBy = "khoiDieuKhienEntity")
    private List<VideoDieuKhienEntity> dieuKhienEntities = new ArrayList<>();

    public KhoiDieuKhienEntity() {
    }

    public KhoiDieuKhienEntity(int dieukhienId, String anhDieuKhien, String contentHtml, String contentMarkDown, String tenDieuKhien, List<VideoDieuKhienEntity> dieuKhienEntities) {
        this.dieukhienId = dieukhienId;
        this.anhDieuKhien = anhDieuKhien;
        this.contentHtml = contentHtml;
        this.contentMarkDown = contentMarkDown;
        this.tenDieuKhien = tenDieuKhien;
        this.dieuKhienEntities = dieuKhienEntities;
    }

    public int getDieukhienId() {
        return dieukhienId;
    }

    public void setDieukhienId(int dieukhienId) {
        this.dieukhienId = dieukhienId;
    }

    public String getAnhDieuKhien() {
        return anhDieuKhien;
    }

    public void setAnhDieuKhien(String anhDieuKhien) {
        this.anhDieuKhien = anhDieuKhien;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentMarkDown() {
        return contentMarkDown;
    }

    public void setContentMarkDown(String contentMarkDown) {
        this.contentMarkDown = contentMarkDown;
    }

    public String getTenDieuKhien() {
        return tenDieuKhien;
    }

    public void setTenDieuKhien(String tenDieuKhien) {
        this.tenDieuKhien = tenDieuKhien;
    }

    public List<VideoDieuKhienEntity> getDieuKhienEntities() {
        return dieuKhienEntities;
    }

    public void setDieuKhienEntities(List<VideoDieuKhienEntity> dieuKhienEntities) {
        this.dieuKhienEntities = dieuKhienEntities;
    }
}
